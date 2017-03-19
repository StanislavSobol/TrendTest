package com.gmail.sobol.i.stanislav.trendtest.presenter;

import com.gmail.sobol.i.stanislav.trendtest.view.IBaseView;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;
import rx.Subscription;

/**
 * Created by VZ on 18.03.2017.
 */

public abstract class BasePresenter implements IBasePresenter, Serializable {
    @Setter
    @Getter
    private IBaseView view;
    @Getter
    private boolean viewVisible;

    protected Subscription mainSubscription;

    public BasePresenter(IBaseView view) {
        this.view = view;
        dagger2Inject();
    }

    protected abstract void dagger2Inject();

    @Override
    public void release() {
        if (mainSubscription != null) {
            mainSubscription.unsubscribe();
        }
    }

    abstract protected IBaseView getCastedView();

    @Override
    public void onResume() {
        viewVisible = true;
    }

    @Override
    public void onPause() {
        viewVisible = false;
    }
}
