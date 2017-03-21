package com.gmail.sobol.i.stanislav.trendtest.view.main;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.gmail.sobol.i.stanislav.trendtest.R;
import com.gmail.sobol.i.stanislav.trendtest.dto.MinPriceDTO;
import com.gmail.sobol.i.stanislav.trendtest.dto.RecDTO;
import com.gmail.sobol.i.stanislav.trendtest.dto.SubwayDTO;
import com.gmail.sobol.i.stanislav.trendtest.utils.PicassoLoader;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by VZ on 17.03.2017.
 */
class MainActivityListAdapter extends RecyclerView.Adapter<MainActivityListAdapter.Holder> {

    private static int TYPE_REGULAR = 0;
    private static int TYPE_BUTTON = 1;

    private final MainActivity activity;
    private final MainFragment fragment;
    private List<MainActivityListAdapterItem> items = new ArrayList<>();
    private LoadButton loadButton;

    MainActivityListAdapter(MainFragment fragment) {
        this.fragment = fragment;
        activity = (MainActivity) fragment.getActivity();
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        @LayoutRes final int lr = (viewType == TYPE_REGULAR) ?
                R.layout.main_item : R.layout.main_item_button;

        return new Holder(LayoutInflater.from(parent.getContext())
                .inflate(lr, parent, false));
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        holder.setItem(items.get(position));
    }

    @Override
    public int getItemViewType(int position) {
        if (items.get(position) == null) {
            return -1;
        }
        return (items.get(position) instanceof RecDTO) ? TYPE_REGULAR : TYPE_BUTTON;
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    void addItem(RecDTO recDTO) {
        final int lastIndex = items.size() - 1;
        if (lastIndex > -1 && items.get(lastIndex) instanceof LoadButton) {
            items.remove(lastIndex);
            notifyItemRemoved(lastIndex);
        }

        items.add(recDTO);
        notifyItemInserted(items.size() - 1);
    }

    void clearItems() {
        items.clear();
        notifyDataSetChanged();
    }

    void addLoadButton() {
        items.add(new LoadButton());
        notifyItemInserted(items.size() - 1);
    }

    private static class LoadButton implements MainActivityListAdapterItem {

    }

    class Holder extends RecyclerView.ViewHolder {
        @Nullable
        @Bind(R.id.main_item_name_text_view)
        TextView nameTextView;
        @Nullable
        @Bind(R.id.main_item_deadline_text_view)
        TextView deadLineTextView;
        @Nullable
        @Bind(R.id.main_item_image_view)
        ImageView imageView;
        @Nullable
        @Bind(R.id.main_item_min_prices_layout)
        ViewGroup minPricesContainer;
        @Nullable
        @Bind(R.id.main_item_subways_layout)
        ViewGroup subwaaysContainer;

        @Nullable
        @Bind(R.id.main_item_load_button)
        Button loadButton;
        @Nullable
        @Bind(R.id.main_item_progress_bar)
        ProgressBar loadProgressBar;

        Holder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void setItem(MainActivityListAdapterItem itemView) {
            if (itemView instanceof RecDTO) {
                setRegularItem((RecDTO) itemView);
            } else if (itemView instanceof LoadButton) {
                setButtonItem();
            }
        }

        private void setButtonItem() {
            if (loadButton != null) {
                if (loadProgressBar != null) {
                    loadProgressBar.setVisibility(View.GONE);
                }
                loadButton.setVisibility(View.VISIBLE);

                loadButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (loadProgressBar != null) {
                            loadProgressBar.setVisibility(View.VISIBLE);
                        }
                        loadButton.setVisibility(View.GONE);

                        fragment.getRequestDTO().setOffset(
                                fragment.getRequestDTO().getOffset() + 10
                        );
                        activity.loadMoreData();
                    }
                });
            }
        }

        private void setRegularItem(RecDTO recDTO) {
            if (nameTextView != null) {
                nameTextView.setText(recDTO.getName());
            }

            final String s = "Срок сдачи: " + recDTO.getDeadline();
            if (deadLineTextView != null) {
                deadLineTextView.setText(s);
            }

            PicassoLoader.load(activity, recDTO.getImageUrl(), imageView);

            if (minPricesContainer != null) {
                minPricesContainer.removeAllViews();
            }

            for (final MinPriceDTO minPriceDTO : recDTO.getMinPriceDTOs()) {
                final View inflatedView = activity.getLayoutInflater().inflate(R.layout.min_price_subitem, null);

                final TextView roomTextView = (TextView) inflatedView.findViewById(R.id.room_subitem_room_text_view);
                roomTextView.setText(minPriceDTO.getName());

                final TextView priceTextView = (TextView) inflatedView.findViewById(R.id.price_subitem_room_text_view);
                final String tmp = "от " + String.valueOf((int) minPriceDTO.getPrice()) + " руб.";
                priceTextView.setText(tmp);

                minPricesContainer.addView(inflatedView);
            }

            if (subwaaysContainer != null) {
                subwaaysContainer.removeAllViews();
            }

            for (final SubwayDTO subwayDTO : recDTO.getSubwayDTOs()) {
                final View inflatedView = activity.getLayoutInflater().inflate(R.layout.subway_subitem, null);

                final TextView nameTextView = (TextView) inflatedView.findViewById(R.id.subway_subitem_name_text_view);
                nameTextView.setText(subwayDTO.getName());

                final TextView distTimingTextView = (TextView) inflatedView.findViewById(R.id.subway_subitem_dist_timing_text_view);
                final String tmp = String.valueOf((int) subwayDTO.getDistTiming()) + " мин.";
                distTimingTextView.setText(tmp);

                subwaaysContainer.addView(inflatedView);
            }
        }
    }
}
