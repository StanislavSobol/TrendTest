package com.gmail.sobol.i.stanislav.trendtest.view.main;

import com.gmail.sobol.i.stanislav.trendtest.dto.RecDTO;
import com.gmail.sobol.i.stanislav.trendtest.view.IBaseView;

/**
 * Created by VZ on 17.03.2017.
 */
public interface IMainView extends IBaseView {

    void addItem(RecDTO recDTO);

    void clearItems();

    void completeLoading();
}
