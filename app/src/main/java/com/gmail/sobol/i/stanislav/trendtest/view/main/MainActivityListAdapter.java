package com.gmail.sobol.i.stanislav.trendtest.view.main;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gmail.sobol.i.stanislav.trendtest.R;
import com.gmail.sobol.i.stanislav.trendtest.dto.RecDTO;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by VZ on 17.03.2017.
 */
class MainActivityListAdapter extends RecyclerView.Adapter<MainActivityListAdapter.Holder>  {

    private List<RecDTO> items = new ArrayList<>();

    public MainActivityListAdapter(MainFragment mainFragment) {

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
        notifyItemInserted(items.size()-1);
    }

    class Holder extends RecyclerView.ViewHolder {

        @Bind(R.id.main_item_name_text_view)
        TextView nameTextView;

        public Holder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void setItem(RecDTO recDTO) {
            nameTextView.setText(recDTO.getName());
        }
    }
}
