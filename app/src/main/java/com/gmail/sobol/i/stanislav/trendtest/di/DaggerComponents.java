package com.gmail.sobol.i.stanislav.trendtest.di;

import com.gmail.sobol.i.stanislav.trendtest.MApplication;
import com.gmail.sobol.i.stanislav.trendtest.data.DataProvider;
import com.gmail.sobol.i.stanislav.trendtest.presenter.main.MainPresenter;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by VZ on 17.03.2017.
 */

@Singleton
@Component(modules = {DaggerModules.class})
public interface DaggerComponents {

    void inject(MApplication obj);

    void inject(MainPresenter obj);

    void inject(DataProvider obj);
}
