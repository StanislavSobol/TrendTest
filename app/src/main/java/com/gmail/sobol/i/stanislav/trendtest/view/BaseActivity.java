package com.gmail.sobol.i.stanislav.trendtest.view;

/**
 * Created by VZ on 24.11.2016.
 */

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.gmail.sobol.i.stanislav.trendtest.presenter.BasePresenter;
import com.gmail.sobol.i.stanislav.trendtest.presenter.PresenterUser;

import java.util.HashMap;
import java.util.Map;

abstract public class BaseActivity extends AppCompatActivity {

    private static Map<Object, BasePresenter> presentersMap = new HashMap<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (isRealStart()) {
            if (this instanceof PresenterUser) {
                final BasePresenter presenter = (BasePresenter) ((PresenterUser) this).createPresenter();
                presentersMap.put(this, presenter);
            }
        }

        if (getPresenter() != null) {
            getPresenter().setView(this);
        }
    }

    @Override
    protected void onDestroy() {
        if (getPresenter() != null) {
            getPresenter().setView(null);
            if (!isChangingConfigurations()) {
                getPresenter().release();
                presentersMap.remove(this);
            }
        }
        super.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (getPresenter() != null) getPresenter().onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (getPresenter() != null) getPresenter().onPause();
    }

    public BasePresenter getPresenter() {
        return presentersMap.get(this);
    }

    public boolean isRealStart() {
        return getPresenter() == null;
    }

//    abstract protected UUID getActivityId();
//
//    abstract protected void clearActivityId();
}