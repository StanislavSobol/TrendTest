package com.gmail.sobol.i.stanislav.trendtest.di;

import com.gmail.sobol.i.stanislav.trendtest.MApplication;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by VZ on 17.03.2017.
 */

@Singleton
@Component(modules = {DaggerModules.class})
public interface DaggerComponents {

    void inject(MApplication obj);
}
