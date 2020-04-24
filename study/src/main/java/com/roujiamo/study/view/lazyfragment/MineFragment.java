package com.roujiamo.study.view.lazyfragment;


import android.util.Log;

import com.roujiamo.base.base.BaseLazyFragment;
import com.roujiamo.study.R;
import com.roujiamo.study.databinding.FragmentMineBinding;
import com.roujiamo.study.viewmodel.MainViewModel;


public class MineFragment extends BaseLazyFragment<MainViewModel, FragmentMineBinding> {

    private static final String TAG = "MineFragment";
    @Override
    public int getLayoutId() {
        return R.layout.fragment_mine;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        Log.d(TAG, "initData: "+ "MineFragment可见");
    }

    @Override
    public void initLiveDataObserve() {

    }

}
