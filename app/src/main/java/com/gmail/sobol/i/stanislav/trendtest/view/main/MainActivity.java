package com.gmail.sobol.i.stanislav.trendtest.view.main;

import android.os.Bundle;
import android.util.Log;

import com.gmail.sobol.i.stanislav.trendtest.R;
import com.gmail.sobol.i.stanislav.trendtest.dto.RecDTO;
import com.gmail.sobol.i.stanislav.trendtest.presenter.PresenterUser;
import com.gmail.sobol.i.stanislav.trendtest.presenter.main.MainPresentable;
import com.gmail.sobol.i.stanislav.trendtest.presenter.main.MainPresenter;
import com.gmail.sobol.i.stanislav.trendtest.view.BaseActivity;

public class MainActivity extends BaseActivity implements MainView, PresenterUser<MainPresentable> {

    private MainFragment mainFragment;

    public MainActivity() {
        Log.d("SSS", "MainActivity constructor");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("x", 777);
        Log.d("SSS", "onSaveInstanceState = "+ isChangingConfigurations());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final boolean realStart = isRealStart();
        super.onCreate(savedInstanceState);

        if (savedInstanceState != null) {
            Log.d("SSS", "X = " + savedInstanceState.getInt("x"));
        }

//        initViews();
        setContentView(R.layout.activity_main);
        mainFragment = MainFragment.newInstance();
        getFragmentManager()
                .beginTransaction()
                .replace(
                        R.id.main_container,
                        mainFragment,
                        MainFragment.class.getName()
                )
                .commit();

        if (realStart) {
            getCastedPresenter().reset();
            getCastedPresenter().loadData();
        }
    }

    @Override
    public MainPresentable createPresenter() {
        return new MainPresenter();
    }

    @Override
    public MainPresentable getCastedPresenter() {
        return (MainPresentable) getPresenter();
    }

    @Override
    public void addItem(RecDTO recDTO) {
        mainFragment.addItem(recDTO);
    }
}
