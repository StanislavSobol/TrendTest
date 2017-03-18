package com.gmail.sobol.i.stanislav.trendtest.view.main;

import android.os.Bundle;

import com.gmail.sobol.i.stanislav.trendtest.R;
import com.gmail.sobol.i.stanislav.trendtest.dto.RecDTO;

public class MainActivity extends BaseActivity_New<IMainPresenter> implements MainView
//        , PresenterUser<IMainPresenter>
{

    private MainFragment mainFragment;

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

        if (isRealStart()) {
            getPresenter().clearCache();
        }
        getPresenter().loadData(!isRealStart());
    }

//    @Override
//    protected void onCommonStart() {
//        Log.d("SSS", "onCommonStart");
//
//        setContentView(R.layout.activity_main);
//        mainFragment = MainFragment.newInstance();
//        getFragmentManager()
//                .beginTransaction()
//                .replace(
//                        R.id.main_container,
//                        mainFragment,
//                        MainFragment.class.getName()
//                )
//                .commit();
//    }
//
//    @Override
//    protected void onRealStart() {
//        getCastedPresenter().clearCache();
//        getCastedPresenter().loadData(false);
//    }
//
//    @Override
//    protected void onUnrealStart() {
//        Log.d("SSS", "onUnrealStart");
//        getCastedPresenter().loadData(true);
//    }

    @Override
    public IMainPresenter createPresenter() {
        return new MainPresenter_New(this);
    }

//    @Override
//    public IMainPresenter getCastedPresenter() {
//        return (IMainPresenter) getPresenter();
//    }

    @Override
    public void addItem(RecDTO recDTO) {
        mainFragment.addItem(recDTO);
    }
}
