package com.gmail.sobol.i.stanislav.trendtest;

import android.app.Application;
import android.util.Log;

import com.gmail.sobol.i.stanislav.trendtest.di.DaggerComponents;
import com.gmail.sobol.i.stanislav.trendtest.dto.RecordDTO;

import lombok.Getter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by VZ on 17.03.2017.
 */

public class MApplication extends Application {
    //    @Getter
    private Retrofit retrofit;
    private TrendApi api;


//    @Inject
//    TrendApi api;

    @Getter
    private DaggerComponents dagger2RealComponents;

    @Override
    public void onCreate() {
        super.onCreate();
//        dagger2RealComponents = DaggerDaggerComponents.builder().daggerModules(new DaggerModules()).build();
//        dagger2RealComponents.inject(this);


        retrofit = new Retrofit.Builder()
                .baseUrl("http://api.trend-dev.ru")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        api = retrofit.create(TrendApi.class);

        rx.Observable<RecordDTO> observable = api.getData("list", 20, 0, false, 0, 0);
        observable
                .onBackpressureBuffer()
                .subscribeOn(Schedulers.computation())
//                .map(new Func1<RecordDTO, RecDTO>() {
//                    @Override
//                    public RecDTO call(RecordDTO recordDTO) {
//                        return recordDTO.toRecDTO(recordDTO);
//                    }
//                })
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Subscriber<RecDTO>() {
//                    @Override
//                    public void onCompleted() {
//                        Log.d("SSS", "onCompleted = " );
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        Log.d("SSS", "e = " + e);
//                    }
//
//                    @Override
//                    public void onNext(RecDTO recordDTO) {
//                        Log.d("SSS", "recordDTO = " + recordDTO);
//                    }
//                });
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<RecordDTO>() {
                    @Override
                    public void onCompleted() {
                        Log.d("SSS", "onCompleted = ");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("SSS", "e = " + e);
                    }

                    @Override
                    public void onNext(RecordDTO recordDTO) {
                        Log.d("SSS", "recordDTO = " + recordDTO);
                    }
                });


    }
}
