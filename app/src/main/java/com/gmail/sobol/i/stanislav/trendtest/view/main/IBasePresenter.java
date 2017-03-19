package com.gmail.sobol.i.stanislav.trendtest.view.main;

import com.gmail.sobol.i.stanislav.trendtest.view.BaseView;

/**
 * Created by VZ on 18.03.2017.
 */
public interface IBasePresenter {

//    BaseActivity baseActivity

    void release();

    void onPause();

    void onResume();

    void setView(BaseView view);
}
