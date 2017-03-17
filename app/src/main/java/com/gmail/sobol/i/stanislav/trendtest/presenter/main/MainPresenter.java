package com.gmail.sobol.i.stanislav.trendtest.presenter.main;

import android.util.Log;

import com.gmail.sobol.i.stanislav.trendtest.MApplication;
import com.gmail.sobol.i.stanislav.trendtest.data.DataProviderPresentable;
import com.gmail.sobol.i.stanislav.trendtest.dto.RawDTO;
import com.gmail.sobol.i.stanislav.trendtest.dto.RecDTO;
import com.gmail.sobol.i.stanislav.trendtest.dto.RequestDTO;
import com.gmail.sobol.i.stanislav.trendtest.presenter.BasePresenter;
import com.gmail.sobol.i.stanislav.trendtest.view.main.MainView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by VZ on 17.03.2017.
 */
public class MainPresenter extends BasePresenter implements MainPresentable {

    private List<RecDTO> items = new ArrayList<>();

    @Inject
    DataProviderPresentable dataProvider;

    @Override
    protected void dagger2Inject() {
        MApplication.getInstance().getDagger2RealComponents().inject(this);
    }

    @Override
    protected MainView getCastedView() {
        return (MainView) getView();
    }

    @Override
    public void loadData() {

        final RequestDTO requestDTO = new RequestDTO();

        requestDTO.setOffset(0);
        requestDTO.setFrom(0);
        requestDTO.setTo(10000);

        mainSubscription = dataProvider.loadData(requestDTO)
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
                        mainSubscription.unsubscribe();
                    }

                    @Override
                    public void onError(Throwable e) {
                        mainSubscription.unsubscribe();
                        Log.d("SSS", "e = " + e);
                    }

                    @Override
                    public void onNext(RecDTO recDTO) {
                        getCastedView().addItem(recDTO);
                    }
                });
    }

    @Override
    public void reset() {

    }
}
