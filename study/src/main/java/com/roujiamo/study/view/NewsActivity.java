package com.roujiamo.study.view;

import android.content.Intent;
import android.view.View;

import androidx.lifecycle.Observer;

import com.roujiamo.base.base.BaseDataActivity;
import com.roujiamo.http.bean.TranslationBean;
import com.roujiamo.study.R;
import com.roujiamo.study.databinding.ActivityNewsBinding;
import com.roujiamo.study.viewmodel.NewsViewModel;


public class NewsActivity extends BaseDataActivity<NewsViewModel, ActivityNewsBinding> implements View.OnClickListener {

    @Override
    public int getLayoutId() {
        return R.layout.activity_news;
    }

    @Override
    public void initView() {
        mBinding.setOnClickListener(this);
    }

    @Override
    public void initData() {
    }

    @Override
    public boolean isShowLoadStatus() {
        return false;
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.btn_request) {
            //网络请求
            mViewModel.getNews();
        } else if (id == R.id.btn_jump_shareData) {
            startActivity(new Intent(this, ShareDataActivity.class));
        } else if (id == R.id.btn_jump_rxJavaExample) {
            startActivity(new Intent(this, RxJavaExampleActivity.class));
        }
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
