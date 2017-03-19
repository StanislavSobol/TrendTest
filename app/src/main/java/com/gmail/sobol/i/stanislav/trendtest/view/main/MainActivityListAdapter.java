package com.gmail.sobol.i.stanislav.trendtest.view.main;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
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

    MainActivityListAdapter(MainActivityMainView activity) {
        Log.d("SSS", "MainActivityListAdapter " + this);
//        Log.d("SSS", "MainActivityListAdapter sizeoff = " + items.size());
//        items.clear();
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
//        Log.d("SSS", "onCreateViewHolder = ");
        return new Holder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.main_item, parent, false));
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
//        Log.d("SSS", "bind pos = " + position);
        holder.setItem(items.get(position));
    }

    @Override
    public int getItemCount() {
        Log.d("SSS", "getItemCount= " + items.size());
        return items.size();
    }

    void addItem(RecDTO recDTO) {
        items.add(recDTO);
        notifyItemInserted(items.size()-1);
        Log.d("SSS", "addItem sizeo = " + items.size());
        //notifyDataSetChanged();
    }

    void clearItems() {
        items.clear();
    }

    static class Holder extends RecyclerView.ViewHolder {

        @Bind(R.id.main_item_name_text_view)
        TextView nameTextView;

        Holder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void setItem(RecDTO recDTO) {
            nameTextView.setText(recDTO.getName());
        }
    }
}
