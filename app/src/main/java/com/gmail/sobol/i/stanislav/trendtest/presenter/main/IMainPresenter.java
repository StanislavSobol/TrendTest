package com.gmail.sobol.i.stanislav.trendtest.presenter.main;

import com.gmail.sobol.i.stanislav.trendtest.dto.RequestDTO;
import com.gmail.sobol.i.stanislav.trendtest.presenter.IBasePresenter;

/**
 * Created by VZ on 18.03.2017.
 */
public interface IMainPresenter extends IBasePresenter {

    void loadData(boolean fromCache, RequestDTO requestDTO);

    void clearCache();
}
