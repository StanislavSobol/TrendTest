package com.gmail.sobol.i.stanislav.trendtest.view.main;

import android.os.Bundle;
import android.util.Log;

import com.gmail.sobol.i.stanislav.trendtest.R;
import com.gmail.sobol.i.stanislav.trendtest.dto.RecDTO;
import com.gmail.sobol.i.stanislav.trendtest.presenter.main.IMainPresenter;
import com.gmail.sobol.i.stanislav.trendtest.presenter.main.MainPresenter;
import com.gmail.sobol.i.stanislav.trendtest.view.BaseActivity;

public class MainActivity extends BaseActivity<IMainPresenter> implements IMainView {

    private MainFragment mainFragment;

    public MainActivity() {
        Log.d("SSS", "MainActivity create");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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

        //getRecyclerViewAdapter().clearItems();
        if (isRealStart()) {
            getPresenter().clearCache();
        }
        getPresenter().loadData(!isRealStart(), mainFragment.getRequestDTO());
    }

    @Override
    public IMainPresenter createPresenter() {
        return new MainPresenter(this);
    }

    @Override
    public void addItem(final RecDTO recDTO) {
        mainFragment.addItem(recDTO);
    }

    @Override
    public void clearItems() {
        // getRecyclerViewAdapter().clearItems();
    }

    public void loadData() {
        getPresenter().loadData(false, mainFragment.getRequestDTO());
    }
}
