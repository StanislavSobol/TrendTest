package com.gmail.sobol.i.stanislav.trendtest.data;

import com.gmail.sobol.i.stanislav.trendtest.dto.RawDTO;
import com.gmail.sobol.i.stanislav.trendtest.dto.RequestDTO;

import rx.Observable;

/**
 * Created by VZ on 17.03.2017.
 */
public interface DataProviderPresentable {

    Observable<RawDTO> loadData(RequestDTO requestDTO);
}
