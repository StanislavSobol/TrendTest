package com.gmail.sobol.i.stanislav.trendtest.view.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

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
//        onCommonStart();

        //  boolean realStart = false;

        if (savedInstanceState == null) {
            presenter = createPresenter();
        } else {
            presenter = (IBasePresenter) savedInstanceState.getSerializable(PRESENTER_KEY);
        }

        if (!(presenter instanceof Serializable)) {
            throw new RuntimeException("The presenter doesn't implement Serializable interface");
        }

        realStart = savedInstanceState == null;

//        if (realStart) {
//            onRealStart();
//        } else {
//            onUnrealStart();
//        }
    }

    @Override
    protected void onDestroy() {
        if (presenter != null) {
            presenter.release();
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
        if (isChangingConfigurations()) {
            outState.putSerializable(PRESENTER_KEY, (Serializable) presenter);
        } else {
            presenter = null;
        }
        Log.d("SSS", "onSaveInstanceState = " + isChangingConfigurations());
    }

    public T getPresenter() {
        return (T) presenter;
    }

//    /**
//     * Used in common start. In every onCreate event
//     */
//    protected abstract void onCommonStart();
//
//    /**
//     * Used only if presenter was created in the same onCreate method (e.g. there was no changing configuration)
//     */
//    protected abstract void onRealStart();
//
//    /**
//     * Used only if presenter was NOT created in the same onCreate method (e.g. there was changing configuration)
//     */
//    protected abstract void onUnrealStart();


    /**
     * Compell the succeded activity to provide the proper presenter
     *
     * @return the extended proper presenter's object wrapped the extended interface
     */
    public abstract T createPresenter();
}
