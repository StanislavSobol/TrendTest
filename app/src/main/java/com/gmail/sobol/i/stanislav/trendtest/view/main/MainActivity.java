package com.gmail.sobol.i.stanislav.trendtest.view.main;

import android.os.Bundle;
import android.util.Log;

import com.gmail.sobol.i.stanislav.trendtest.R;
import com.gmail.sobol.i.stanislav.trendtest.dto.RecDTO;

public class MainActivity extends BaseActivity_New<IMainPresenter> implements MainView {

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
        getPresenter().loadData(!isRealStart());
    }

    @Override
    public IMainPresenter createPresenter() {
        return new MainPresenter_New(this);
    }

    @Override
    public void addItem(final RecDTO recDTO) {
        mainFragment.addItem(recDTO);
    }

    @Override
    public void clearItems() {
        // getRecyclerViewAdapter().clearItems();
    }
}
