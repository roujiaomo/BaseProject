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
    private NewsViewModel newsViewModel;

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
//        switch (v.getId()){
//            case R.id.btn_request:
//                mViewModel.getNews();
//                break;
//            case R.id.btn_jump_shareData:
//                startActivity(new Intent(this, ShareDataActivity.class));
//                break;
//            case R.id.btn_jump_rxJavaExample:
//                startActivity(new Intent(this, RxJavaExampleActivity.class));
//                break;
//        }
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
