package com.gmail.sobol.i.stanislav.trendtest.view.main;


import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.Spinner;

import com.gmail.sobol.i.stanislav.trendtest.R;
import com.gmail.sobol.i.stanislav.trendtest.dto.RecDTO;
import com.gmail.sobol.i.stanislav.trendtest.dto.RequestDTO;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import lombok.Getter;

/**
 * Created by VZ on 17.03.2017.
 */

public class MainFragment extends Fragment {

    private static final int BEG_SUM = 1000000;
    private static final int STEP_SUM = 500000;

    @Bind(R.id.main_from_spinner)
    Spinner fromSpinner;
    @Bind(R.id.main_to_spinner)
    Spinner toSpinner;
    @Bind(R.id.main_recycler_view)
    RecyclerView recyclerView;
    @Bind(R.id.full_progress_bar)
    ProgressBar fullProgressBar;
    @Bind(R.id.main_swipe_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;

    @Getter
    final private RequestDTO requestDTO = new RequestDTO();

    final private List<RecDTO> bufferRecDTO = new ArrayList<>();

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_main, container, false);

        ButterKnife.bind(this, view);

        initFromSpinner();

        recyclerView.setAdapter(new MainActivityListAdapter(this));
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));

        // load buffered incoming records
        if (getRecyclerViewAdapter() != null) {
            for (final RecDTO item : bufferRecDTO) {
                getRecyclerViewAdapter().addItem(item);
            }
            bufferRecDTO.clear();
        }

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getCastedActivity().loadData(false);
            }
        });

        startLoading();
        return view;
    }

    private MainActivity getCastedActivity() {
        return (MainActivity) getActivity();
    }

    private void initFromSpinner() {
        final List<String> strings = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            int c = i * STEP_SUM + BEG_SUM;
            strings.add(String.valueOf(c));
        }

        final ArrayAdapter<String> adapter =
                new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, strings);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        fromSpinner.setAdapter(adapter);

        fromSpinner.setPrompt(getString(R.string.price_from));

        fromSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                initToSpinner(strings.get(i));

                requestDTO.setFrom(Integer.valueOf(strings.get(i)));
                getCastedActivity().loadData(true);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }

    private void initToSpinner(String s) {
        int beg = Integer.valueOf(s) + STEP_SUM;

        final List<String> strings = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            int c = i * STEP_SUM + beg;
            strings.add(String.valueOf(c));
        }

        final ArrayAdapter<String> adapter =
                new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, strings);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        toSpinner.setAdapter(adapter);

        toSpinner.setPrompt(getString(R.string.price_to));

        toSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                requestDTO.setTo(Integer.valueOf(strings.get(i)));
                getCastedActivity().loadData(true);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private MainActivityListAdapter getRecyclerViewAdapter() {
        return recyclerView == null ? null : (MainActivityListAdapter) recyclerView.getAdapter();
    }

    // In case if the recycler view doesn't exist yet must buffer the incoming records
    public void addItem(RecDTO recDTO) {
        final MainActivityListAdapter adapter = getRecyclerViewAdapter();
        if (adapter == null) {
            bufferRecDTO.add(recDTO);
        } else {
            adapter.addItem(recDTO);
        }
    }

    void startLoading() {
        fullProgressBar.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);
    }

    void completeLoading() {
        swipeRefreshLayout.setRefreshing(false);
        fullProgressBar.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
    }

    public void clearItems() {
        if (getRecyclerViewAdapter() != null) {
            getRecyclerViewAdapter().clearItems();
        }
    }

    public void addLoadButton() {
        if (getRecyclerViewAdapter() != null) {
            getRecyclerViewAdapter().addLoadButton();
        }
    }
}
