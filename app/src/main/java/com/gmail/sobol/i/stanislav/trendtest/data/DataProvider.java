package com.gmail.sobol.i.stanislav.trendtest.data;

import com.gmail.sobol.i.stanislav.trendtest.MApplication;
import com.gmail.sobol.i.stanislav.trendtest.dto.RawDTO;
import com.gmail.sobol.i.stanislav.trendtest.dto.RequestDTO;
import com.gmail.sobol.i.stanislav.trendtest.network.TrendApi;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by VZ on 17.03.2017.
 */

public class DataProvider implements DataProviderPresentable {

    @Inject
    TrendApi trendApi;

    public DataProvider() {
        MApplication.getInstance().getDagger2RealComponents().inject(this);
    }

    @Override
    public Observable<RawDTO> loadData(RequestDTO requestDTO) {
        return trendApi.getData("list", 10, requestDTO.getOffset(), false, requestDTO.getFrom(), requestDTO.getTo());
    }
}
