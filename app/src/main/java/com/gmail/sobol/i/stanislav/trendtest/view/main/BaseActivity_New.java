package com.gmail.sobol.i.stanislav.trendtest.view.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.gmail.sobol.i.stanislav.trendtest.view.BaseView;

import java.io.Serializable;

import lombok.Getter;

/**
 * Created by VZ on 18.03.2017.
 */
public abstract class BaseActivity_New<T extends IBasePresenter> extends AppCompatActivity implements BaseView {

    private static final String PRESENTER_KEY = "presenter";

    private IBasePresenter presenter;
    @Getter
    private boolean realStart;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        realStart = savedInstanceState == null || presenter == null;

        if (realStart) {
            presenter = createPresenter();
        } else {
            presenter = (IBasePresenter) savedInstanceState.getSerializable(PRESENTER_KEY);
            if (presenter != null) {
                presenter.setView(this);
            }
        }

        if (presenter == null) {
            throw new RuntimeException("The presenter is null");
        }

        if (!(presenter instanceof Serializable)) {
            throw new RuntimeException("The presenter doesn't implement Serializable interface");
        }
    }

    @Override
    protected void onDestroy() {
        if (presenter != null) {
            presenter.release();
            presenter = null;
        }
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (presenter != null) {
            presenter.onPause();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (presenter != null) {
            presenter.onResume();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(PRESENTER_KEY, (Serializable) presenter);
    }

    public T getPresenter() {
        return (T) presenter;
    }

    /**
     * Compell the succeded activity to provide the proper presenter
     *
     * @return the extended proper presenter's object wrapped the extended interface
     */
    public abstract T createPresenter();
}
