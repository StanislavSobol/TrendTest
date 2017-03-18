package com.gmail.sobol.i.stanislav.trendtest.view.main;


import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gmail.sobol.i.stanislav.trendtest.R;
import com.gmail.sobol.i.stanislav.trendtest.dto.RecDTO;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by VZ on 17.03.2017.
 */

public class MainFragment extends Fragment {

    @Bind(R.id.main_recycler_view)
    RecyclerView recyclerView;

    public static MainFragment newInstance() {
        Log.d("SSS", "newInstance");
        return new MainFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d("SSS", "onCreateView");

        final View view = inflater.inflate(R.layout.fragment_main, container, false);

        ButterKnife.bind(this, view);

        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        recyclerView.setAdapter(new MainActivityListAdapter(this));

        return view;
    }

    private MainActivityListAdapter getAdapter() {
        return (MainActivityListAdapter) recyclerView.getAdapter();
    }

    public void addItem(RecDTO recDTO) {
        getAdapter().addItem(recDTO);
    }
}
