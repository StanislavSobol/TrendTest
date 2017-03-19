package com.gmail.sobol.i.stanislav.trendtest.view.main;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.gmail.sobol.i.stanislav.trendtest.R;
import com.gmail.sobol.i.stanislav.trendtest.dto.RecDTO;

import butterknife.Bind;
import butterknife.ButterKnife;

//public class MainActivity extends BaseActivity_New<IMainPresenter> implements MainView {
public class MainActivity extends BaseActivity_New<IMainPresenter> implements MainView {

    @Bind(R.id.main_recycler_view)
    RecyclerView recyclerView;

    private MainActivityListAdapter adapter = new MainActivityListAdapter(null);
//    private List<RecDTO> itemsBuffer = new ArrayList<>();

    public MainActivity() {
        Log.d("SSS", "MainActivity create");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        //     recyclerView = (RecyclerView) findViewById(R.id.main_recycler_view);

        //   adapter =   new MainActivityListAdapter(this);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        getRecyclerViewAdapter().clearItems();
        Log.d("SSS", "loadData adapter = " + adapter);

        //  Log.d("SSS", "loadData Thread = " + Thread.currentThread().getName());

//        itemsBuffer.clear();
        if (isRealStart()) {
            getPresenter().clearCache();
        }
        getPresenter().loadData(!isRealStart());
//        getPresenter().loadData(false);

//for (final RecDTO item : itemsBuffer) {
//    adapter.addItem(item);
//}
//        itemsBuffer.clear();

        Log.d("SSS", "loadData this = " + this);

//        RecDTO recDTO = new RecDTO();
//        recDTO.setName("fake name");
//
//        addItem(recDTO);
    }

    @Override
    protected void onDestroy() {
//        Log.d("SSS", "onDestroy Thread = " + Thread.currentThread().getName());


        Log.d("SSS", "onDestroy this = " + this);
        //   adapter = null;
        //    recyclerView.setAdapter(null);
        super.onDestroy();
    }

    @Override
    public IMainPresenter createPresenter() {
        return new MainPresenter_New(this);
    }

    @Override
    public void addItem(final RecDTO recDTO) {
        Log.d("SSS", "addItem this = " + this);
//        itemsBuffer.add(recDTO);

//
//        if (adapter == null) {
//            adapter = new MainActivityListAdapter(this);
//            recyclerView.setAdapter(adapter);
//            recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        }

//        Log.d("SSS", "addItem Thread = " + Thread.currentThread().getName());
        adapter.addItem(recDTO);
//        getRecyclerViewAdapter().addItem(recDTO);

/*
        Log.d("SSS", "addItem adapter = " + adapter);
     //   if (getRecyclerViewAdapter() != null) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                getRecyclerViewAdapter().addItem(recDTO);
            }
        });
      //  }
      */
    }

    @Override
    public void clearItems() {
        // getRecyclerViewAdapter().clearItems();
    }

    private MainActivityListAdapter getRecyclerViewAdapter() {
        return adapter; //(MainActivityListAdapter) recyclerView.getAdapter();
    }
}
