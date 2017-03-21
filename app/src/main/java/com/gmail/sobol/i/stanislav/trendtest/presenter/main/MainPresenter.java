package com.gmail.sobol.i.stanislav.trendtest.presenter.main;

import android.util.Log;

import com.gmail.sobol.i.stanislav.trendtest.MApplication;
import com.gmail.sobol.i.stanislav.trendtest.data.DataProviderPresentable;
import com.gmail.sobol.i.stanislav.trendtest.dto.RawDTO;
import com.gmail.sobol.i.stanislav.trendtest.dto.RecDTO;
import com.gmail.sobol.i.stanislav.trendtest.dto.RequestDTO;
import com.gmail.sobol.i.stanislav.trendtest.presenter.BasePresenter;
import com.gmail.sobol.i.stanislav.trendtest.view.IBaseView;
import com.gmail.sobol.i.stanislav.trendtest.view.main.IMainView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by VZ on 18.03.2017.
 */
public class MainPresenter extends BasePresenter implements IMainPresenter, Serializable {

    private List<RecDTO> items = new ArrayList<>();

    @Inject
    DataProviderPresentable dataProvider;

    public MainPresenter(IBaseView view) {
        super(view);
    }

    @Override
    protected void dagger2Inject() {
        MApplication.getInstance().getDagger2RealComponents().inject(this);
    }

    @Override
    protected IMainView getCastedView() {
        return (IMainView) getView();
    }

    @Override
    public void loadData(boolean fromCache, RequestDTO requestDTO) {
        getCastedView().clearItems();

        if (fromCache) {
            for (final RecDTO item : items) {
                getCastedView().addItem(item);
            }
            return;
        }

        if (requestDTO == null) {
            requestDTO = new RequestDTO();
        }

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
                //       .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<RecDTO>() {
                    @Override
                    public void onCompleted() {
                        getCastedView().completeLoading();
                        mainSubscription.unsubscribe();
                    }

                    @Override
                    public void onError(Throwable e) {
                        mainSubscription.unsubscribe();
                        Log.d("SSS", "e = " + e);
                    }

                    @Override
                    public void onNext(RecDTO recDTO) {
                        items.add(recDTO);
                        getCastedView().addItem(recDTO);
                    }
                });
    }

    @Override
    public void clearCache() {
        items.clear();
    }
}
