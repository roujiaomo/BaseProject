package com.roujiamo.base.base;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import com.blankj.utilcode.util.ActivityUtils;
import com.gyf.immersionbar.ImmersionBar;
import com.roujiamo.base.R;

/**
 * 普通Activity 无网络请求 (例 : 主页宿主Activity )
 * @param <DB>
 */
public abstract class BaseActivity<DB extends ViewDataBinding> extends AppCompatActivity {

    public DB mBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ImmersionBar.with(this)
                .statusBarColor(R.color.title_blue)
                .statusBarAlpha(0.2f)  //状态栏透明度，不写默认0.0f
                .fitsSystemWindows(true)
                .init();
        mBinding = DataBindingUtil.setContentView(this, getLayoutId());
        //未封装title栏
        initView();//初始化布局
        initData(); //初始化数据
    }

    /**
     * 获取资源ID
     *
     * @return 布局资源ID
     */
    public abstract int getLayoutId();

    /**
     * 初始化界面
     */
    public abstract void initView();

    public abstract void initData();

    /**
     *  退出程序 供HomeActivity页使用
     */
    public void exit(){
        ActivityUtils.getActivityList().clear();
        System.exit(0);
    }


    /**
     * 适配框架
     * @return
     */
//    @Override
//    public Resources getResources() {
//        //需要升级到 v1.1.2 及以上版本才能使用 AutoSizeCompat
//        AutoSizeCompat.autoConvertDensityOfGlobal((super.getResources());//如果没有自定义需求用这个方法
//        AutoSizeCompat.autoConvertDensity((super.getResources(), 667, false);//如果有自定义需求就用这个方法
//        return super.getResources();
//    }
//
}
