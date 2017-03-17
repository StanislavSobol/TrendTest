package com.gmail.sobol.i.stanislav.trendtest.presenter.main;

import com.gmail.sobol.i.stanislav.trendtest.presenter.BasePresenter;
import com.gmail.sobol.i.stanislav.trendtest.view.main.MainView;

/**
 * Created by VZ on 17.03.2017.
 */
public class MainPresenter extends BasePresenter implements MainPresentable {

    @Override
    protected void dagger2Inject() {

    }

    @Override
    protected MainView getCastedView() {
        return (MainView) getView();
    }
}
