package com.roujiamo.study.view;

import android.view.View;

import androidx.lifecycle.Observer;

import com.roujiamo.base.base.BaseDataActivity;
import com.roujiamo.http.bean.TranslationBean;
import com.roujiamo.study.R;
import com.roujiamo.study.databinding.ActivityShareDataBinding;
import com.roujiamo.study.viewmodel.NewsViewModel;

public class ShareDataActivity extends BaseDataActivity<NewsViewModel, ActivityShareDataBinding> implements View.OnClickListener{


    @Override
    public int getLayoutId() {
        return R.layout.activity_share_data;
    }

    public void onClick(View v) {
//        switch (v.getId()){
//            case R.id.btn_request:
//                mViewModel.getNews();
//                break;
//        }
    }

    @Override
    public void initView() {
        mBinding.setOnClickListener(this);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initLiveDataObserve() {
        //数据请求成功自动触发监听
        mViewModel.translationBeanValue.observe(this, new Observer<TranslationBean>() {
            @Override
            public void onChanged(TranslationBean translationBean) {
            }
        });
    }
}
