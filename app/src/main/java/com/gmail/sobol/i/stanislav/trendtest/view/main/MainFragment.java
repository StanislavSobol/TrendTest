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

import java.util.ArrayList;
import java.util.List;

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

        recyclerView.setAdapter(new MainActivityListAdapter(null));
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));

        // load buffered incoming records
        if (getRecyclerViewAdapter() != null) {
            for (final RecDTO item : bufferRecDTO) {
                getRecyclerViewAdapter().addItem(item);
            }
            bufferRecDTO.clear();
        }

        return view;
    }

    private MainActivityListAdapter getRecyclerViewAdapter() {
        return recyclerView == null ? null : (MainActivityListAdapter) recyclerView.getAdapter();
    }

    final private List<RecDTO> bufferRecDTO = new ArrayList<>();

    // In case if the recycler view doesn't exist yet must buffer the incoming records
    public void addItem(RecDTO recDTO) {
        final MainActivityListAdapter adapter = getRecyclerViewAdapter();
        if (adapter == null) {
            bufferRecDTO.add(recDTO);
        } else {
            adapter.addItem(recDTO);
        }
    }
}
