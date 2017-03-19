package com.gmail.sobol.i.stanislav.trendtest.presenter;

import com.gmail.sobol.i.stanislav.trendtest.view.IBaseView;

/**
 * Created by VZ on 18.03.2017.
 */
public interface IBasePresenter {

    void release();

    void onPause();

    void onResume();

    void setView(IBaseView view);
}
