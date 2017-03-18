package com.gmail.sobol.i.stanislav.trendtest.view.main;

import com.gmail.sobol.i.stanislav.trendtest.view.BaseActivity;
import com.gmail.sobol.i.stanislav.trendtest.view.BaseView;

import java.io.Serializable;

import lombok.Getter;
import rx.Subscription;

/**
 * Created by VZ on 18.03.2017.
 */

public abstract class BasePresenter_New implements IBasePresenter, Serializable {
    @Getter
    private BaseView view;
    @Getter
    private boolean viewVisible;

    protected Subscription mainSubscription;

    public BasePresenter_New(BaseView view) {
        this.view = view;
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
