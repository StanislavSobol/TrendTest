package com.gmail.sobol.i.stanislav.trendtest;

import android.app.Application;
import android.util.Log;

import com.gmail.sobol.i.stanislav.trendtest.di.DaggerComponents;
import com.gmail.sobol.i.stanislav.trendtest.di.DaggerDaggerComponents;
import com.gmail.sobol.i.stanislav.trendtest.di.DaggerModules;
import com.gmail.sobol.i.stanislav.trendtest.dto.RawDTO;
import com.gmail.sobol.i.stanislav.trendtest.dto.RecDTO;
import com.gmail.sobol.i.stanislav.trendtest.network.TrendApi;

import java.util.List;

import javax.inject.Inject;

import lombok.Getter;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by VZ on 17.03.2017.
 */

public class MApplication extends Application {
    @Inject
    TrendApi api;

    @Getter
    private DaggerComponents dagger2RealComponents;

    @Override
    public void onCreate() {
        super.onCreate();
        dagger2RealComponents = DaggerDaggerComponents.builder().daggerModules(new DaggerModules()).build();
        dagger2RealComponents.inject(this);

        //   testRetro();
        testRetroFlatMap();
    }

    private void testRetro() {
        rx.Observable<RawDTO> observable = api.getData("list", 20, 0, false, 0, 0);
        observable
                .onBackpressureBuffer()
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<RawDTO>() {
                    @Override
                    public void onCompleted() {
                        Log.d("SSS", "onCompleted = ");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("SSS", "e = " + e);
                    }

                    @Override
                    public void onNext(RawDTO recordDTO) {
                        Log.d("SSS", "recordDTO = " + recordDTO);
                    }
                });
    }

    private void testRetroFlatMap() {
        rx.Observable<RawDTO> observable = api.getData("list", 20, 0, false, 0, 0);
        observable
                .onBackpressureBuffer()
                .subscribeOn(Schedulers.computation())
                .flatMap(new Func1<RawDTO, Observable<RecDTO>>() {
                    @Override
                    public Observable<RecDTO> call(final RawDTO rawDTO) {
                        return Observable.create(new Observable.OnSubscribe<RecDTO>() {
                            @Override
                            public void call(Subscriber<? super RecDTO> subscriber) {
                                final List<RecDTO> items = rawDTO.getRecDTOs(rawDTO);
                                for (final RecDTO item : items) {
                                    subscriber.onNext(item);
                                }
                                subscriber.onCompleted();
                            }
                        });
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<RecDTO>() {
                    @Override
                    public void onCompleted() {
                        Log.d("SSS", "onCompleted = ");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("SSS", "e = " + e);
                    }

                    @Override
                    public void onNext(RecDTO recordDTO) {
                        Log.d("SSS", "recordDTO = " + recordDTO);
                    }
                });
    }
}
