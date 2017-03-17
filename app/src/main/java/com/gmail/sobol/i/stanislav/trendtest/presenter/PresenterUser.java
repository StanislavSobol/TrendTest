package com.gmail.sobol.i.stanislav.trendtest.presenter;

public interface PresenterUser<T> {

    T createPresenter();

    T getCastedPresenter();
}
