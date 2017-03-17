package com.gmail.sobol.i.stanislav.trendtest.di;

import com.gmail.sobol.i.stanislav.trendtest.TrendApi;

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
        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.trend-dev.ru")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        return retrofit;
    }

    @Provides
    TrendApi provideDeviceService(@QRetrofit Retrofit retrofit) {
        return retrofit.create(TrendApi.class);
    }
}
