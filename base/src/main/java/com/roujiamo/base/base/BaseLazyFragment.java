package com.roujiamo.base.base;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;


import com.roujiamo.base.constant.LoadStatus;
import com.roujiamo.base.utils.DialogUtil;
import com.roujiamo.base.widget.ProgressLoading;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * 基于Fragment setMaxLifecycle 的LazyFragment
 * 此方式设置Fragment初始化最大生命周期走到onStart 在onResume处理数据请求等即可
 * https://juejin.im/post/5cdb7c15f265da036c57ac66#heading-0
 * @param <VM>
 * @param <DB>
 */
public abstract class BaseLazyFragment<VM extends BaseViewModel, DB extends ViewDataBinding> extends Fragment {

    public Context mActivity;
    public DB mBinding;
    public VM mViewModel;
    private ProgressLoading progressLoading;//加载进度条
    private boolean isFirstLoad = true; // 是否第一次加载

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false);
        return mBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initViewModel();
        initLoadStatus(); //数据请求状态
        initLiveDataObserve();
        initView();//初始化界面数据
    }

    @Override
    public void onResume() {
        super.onResume();
        if (isFirstLoad) {
            initData();//网络请求数据
            //第一次加载后
            isFirstLoad = isVisibleReload();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        isFirstLoad = true;
    }

    private  void initViewModel(){
        mViewModel = ViewModelProviders.of(this).get(getVMClass());
    }

    private void initLoadStatus(){
        progressLoading = DialogUtil.getProgressDialog(mActivity);
        mViewModel.loadStatusLiveData.observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer status) {
                switch (status) {
                    case LoadStatus.STATUS_LOADING:
                        progressLoading.showMessage("加载中");
                        break;
                    case LoadStatus.STATUS_UPLOADING:
                        progressLoading.showMessage("正在上传");
                        break;
                    case LoadStatus.STATUS_REQUEST:
                        progressLoading.showMessage("正在请求");
                        break;
                    case LoadStatus.STATUS_CONTENT: //加载完成
                        if(progressLoading.isShowing()){
                            progressLoading.hideLoading();
                        }
                        break;
                    case LoadStatus.STATUS_EMPTY:
                        break;
                    case LoadStatus.STATUS_ERROR: //服务器错误
                        if(progressLoading.isShowing()){
                            progressLoading.hideLoading();
                        }
                        break;
                    case LoadStatus.STATUS_NO_NETWORK: //网络错误
                        if(progressLoading.isShowing()){
                            progressLoading.hideLoading();
                        }
                        break;
                }

            }
        });
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

    /**
     * 初始化数据
     */
    public abstract void initData();

    public abstract void initLiveDataObserve();

    /**
     * 是否每次可见都请求数据  true每次重新请求数据 false 只第一次可见加载数据
     * @return
     */
    public  boolean isVisibleReload(){
        return false;
    };


    private Class<VM> getVMClass() {
        ParameterizedType type = (ParameterizedType) getClass().getGenericSuperclass();
        Type[] actualTypeArguments = type.getActualTypeArguments();
        return (Class<VM>) actualTypeArguments[0];
    }
}
