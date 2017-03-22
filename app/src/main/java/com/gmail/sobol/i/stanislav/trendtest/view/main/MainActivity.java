package com.gmail.sobol.i.stanislav.trendtest.view.main;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.gmail.sobol.i.stanislav.trendtest.R;
import com.gmail.sobol.i.stanislav.trendtest.dto.RecDTO;
import com.gmail.sobol.i.stanislav.trendtest.presenter.main.IMainPresenter;
import com.gmail.sobol.i.stanislav.trendtest.presenter.main.MainPresenter;
import com.gmail.sobol.i.stanislav.trendtest.view.BaseActivity;

public class MainActivity extends BaseActivity<IMainPresenter> implements IMainView {

    private MainFragment mainFragment;

    public MainActivity() {
        Log.d("SSS", "MainActivity create");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        mainFragment = MainFragment.newInstance();
        getFragmentManager()
                .beginTransaction()
                .replace(
                        R.id.main_container,
                        mainFragment,
                        MainFragment.class.getName()
                )
                .commit();

        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }

        setTitle("");

        if (isRealStart()) {
            getPresenter().clearCache();
        }
        getPresenter().loadData(!isRealStart(), mainFragment.getRequestDTO());
    }

    @Override
    public IMainPresenter createPresenter() {
        return new MainPresenter(this);
    }

    @Override
    public void addItem(final RecDTO recDTO) {
        mainFragment.addItem(recDTO);
    }

    @Override
    public void clearItems() {
        //getRecyclerViewAdapter().clearItems();
    }

    @Override
    public void completeLoading() {
        mainFragment.completeLoading();
        mainFragment.addLoadButton();
    }

    public void loadData(boolean showProgressBar) {
        if (showProgressBar) {
            mainFragment.startLoading();
        }
        mainFragment.clearItems();
        getPresenter().loadData(false, mainFragment.getRequestDTO());
    }

    public void loadMoreData() {
        getPresenter().loadData(false, mainFragment.getRequestDTO());
    }
}
