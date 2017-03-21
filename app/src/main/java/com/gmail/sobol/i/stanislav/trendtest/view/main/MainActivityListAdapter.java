package com.gmail.sobol.i.stanislav.trendtest.view.main;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
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

    private final MainActivity activity;
    private List<RecDTO> items = new ArrayList<>();

    MainActivityListAdapter(MainActivity activity) {
        this.activity = activity;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new Holder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.main_item, parent, false));
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        holder.setItem(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    void addItem(RecDTO recDTO) {
        items.add(recDTO);
        notifyItemInserted(items.size() - 1);
    }

    void clearItems() {
        items.clear();
        notifyDataSetChanged();
    }

    class Holder extends RecyclerView.ViewHolder {
        @Bind(R.id.main_item_name_text_view)
        TextView nameTextView;
        @Bind(R.id.main_item_deadline_text_view)
        TextView deadLineTextView;
        @Bind(R.id.main_item_image_view)
        ImageView imageView;
        @Bind(R.id.main_item_min_prices_layout)
        ViewGroup minPricesContainer;
        @Bind(R.id.main_item_subways_layout)
        ViewGroup subwaaysContainer;

        Holder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void setItem(RecDTO recDTO) {
            nameTextView.setText(recDTO.getName());

            final String s = "Срок сдачи: " + recDTO.getDeadline();
            deadLineTextView.setText(s);

            PicassoLoader.load(activity, recDTO.getImageUrl(), imageView);

            minPricesContainer.removeAllViews();

            for (final MinPriceDTO minPriceDTO : recDTO.getMinPriceDTOs()) {
                final View inflatedView = activity.getLayoutInflater().inflate(R.layout.min_price_subitem, null);

                final TextView roomTextView = (TextView) inflatedView.findViewById(R.id.room_subitem_room_text_view);
                roomTextView.setText(minPriceDTO.getName());

                final TextView priceTextView = (TextView) inflatedView.findViewById(R.id.price_subitem_room_text_view);
                final String tmp = "от " + String.valueOf((int) minPriceDTO.getPrice()) + " руб.";
                priceTextView.setText(tmp);

                minPricesContainer.addView(inflatedView);
            }

            subwaaysContainer.removeAllViews();

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
