package com.gmail.sobol.i.stanislav.trendtest.view.main;

import android.os.Bundle;

import com.gmail.sobol.i.stanislav.trendtest.R;
import com.gmail.sobol.i.stanislav.trendtest.presenter.PresenterUser;
import com.gmail.sobol.i.stanislav.trendtest.presenter.main.MainPresentable;
import com.gmail.sobol.i.stanislav.trendtest.presenter.main.MainPresenter;
import com.gmail.sobol.i.stanislav.trendtest.view.BaseActivity;

public class MainActivity extends BaseActivity implements MainView, PresenterUser<MainPresentable> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final boolean realStart = isRealStart();
        super.onCreate(savedInstanceState);

//        initViews();
        setContentView(R.layout.activity_main);

        if (realStart) {
            getCastedPresenter().reset();
            getCastedPresenter().loadData();
        }
    }

    @Override
    public MainPresentable createPresenter() {
        return new MainPresenter();
    }

    @Override
    public MainPresentable getCastedPresenter() {
        return (MainPresentable) getPresenter();
    }
}
