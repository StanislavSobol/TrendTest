package com.gmail.sobol.i.stanislav.trendtest;

import android.app.Application;

import com.gmail.sobol.i.stanislav.trendtest.di.DaggerComponents;
import com.gmail.sobol.i.stanislav.trendtest.di.DaggerDaggerComponents;
import com.gmail.sobol.i.stanislav.trendtest.di.DaggerModules;
import com.gmail.sobol.i.stanislav.trendtest.network.TrendApi;

import javax.inject.Inject;

import lombok.Getter;

/**
 * Created by VZ on 17.03.2017.
 */

public class MApplication extends Application {

    @Getter
    private static MApplication instance;

    @Inject
    TrendApi api;

    @Getter
    private DaggerComponents dagger2RealComponents;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        dagger2RealComponents = DaggerDaggerComponents.builder().daggerModules(new DaggerModules()).build();
        dagger2RealComponents.inject(this);
    }
}
