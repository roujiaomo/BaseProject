package com.roujiamo.study.view;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;


import com.alibaba.android.arouter.facade.annotation.Route;
import com.roujiamo.base.base.BaseDataActivity;
import com.roujiamo.study.R;
import com.roujiamo.study.adapter.BaseFragmentPagerAdapter;
import com.roujiamo.study.databinding.ActivityMainBinding;
import com.roujiamo.study.view.lazyfragment.HomeFragment;
import com.roujiamo.study.view.lazyfragment.ListFragment;
import com.roujiamo.study.view.lazyfragment.MineFragment;
import com.roujiamo.study.viewmodel.MainViewModel;

import java.util.ArrayList;
import java.util.List;
@Route(path = "/study/main")
public class MainActivity extends BaseDataActivity<MainViewModel, ActivityMainBinding> {

    private List<String> tabTitle ; //tab标题栏
    private List<Fragment> mFragments; //Vp切换的Fragment集合
    private HomeFragment homeFragment;
    private ListFragment listFragment;
    private MineFragment mineFragment;
    private BaseFragmentPagerAdapter mAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        mFragments = new ArrayList<>();
        homeFragment = new HomeFragment();
        listFragment = new ListFragment();
        mineFragment = new MineFragment();
        mFragments.add(homeFragment);
        mFragments.add(listFragment);
        mFragments.add(mineFragment);
        tabTitle = new ArrayList<>();
        tabTitle.add("首页");
        tabTitle.add("列表");
        tabTitle.add("我的");
        // 懒加载第二个参数要传   FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
        mAdapter  = new BaseFragmentPagerAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT,
                mFragments ,tabTitle);
        mBinding.vpTabs.setAdapter(mAdapter);
        mBinding.vpTabs.setOffscreenPageLimit(mFragments.size()-1);
        mBinding.tlTabs.setupWithViewPager(mBinding.vpTabs);
    }

    @Override
    public void initLiveDataObserve() {

    }
}
