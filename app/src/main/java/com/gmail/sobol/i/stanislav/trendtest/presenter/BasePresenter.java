package com.gmail.sobol.i.stanislav.trendtest.presenter;


import com.gmail.sobol.i.stanislav.trendtest.view.BaseActivity;
import com.gmail.sobol.i.stanislav.trendtest.view.BaseView;

import lombok.Getter;
import rx.Subscription;

abstract public class BasePresenter {

    @Getter
    private BaseView view;
    @Getter
    private boolean viewVisible;

    protected Subscription mainSubscription;

    public BasePresenter() {
        dagger2Inject();
    }

    protected abstract void dagger2Inject();

    public void release() {
        if (mainSubscription != null) {
            mainSubscription.unsubscribe();
        }
    }

    public void setView(BaseActivity baseActivity) {
        view = (BaseView) baseActivity;
    }

    abstract protected BaseView getCastedView();

    public void onResume() {
        viewVisible = true;
    }

    public void onPause() {
        viewVisible = false;
    }
}
