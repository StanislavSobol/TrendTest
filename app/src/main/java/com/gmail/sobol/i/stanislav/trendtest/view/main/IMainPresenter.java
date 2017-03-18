package com.gmail.sobol.i.stanislav.trendtest.view.main;

/**
 * Created by VZ on 18.03.2017.
 */
public interface IMainPresenter extends IBasePresenter {

    void loadData(boolean fromCache);

    void clearCache();
}
