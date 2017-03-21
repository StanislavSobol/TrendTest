package com.gmail.sobol.i.stanislav.trendtest.di;

import com.gmail.sobol.i.stanislav.trendtest.data.DataProvider;
import com.gmail.sobol.i.stanislav.trendtest.data.DataProviderPresentable;
import com.gmail.sobol.i.stanislav.trendtest.network.TrendApi;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by VZ on 17.03.2017.
 */

@Module
public class DaggerModules {
    @Provides
    @Singleton
    @QRetrofit
    Retrofit providesRetrofit() {
        return new Retrofit.Builder()
                .baseUrl("http://api.trend-dev.ru")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }

    @Provides
    TrendApi provideTrendApi(@QRetrofit Retrofit retrofit) {
        return retrofit.create(TrendApi.class);
    }

    @Provides
    @Singleton
    DataProviderPresentable provideDataProviderPresentable() {
        return new DataProvider();
    }
}
