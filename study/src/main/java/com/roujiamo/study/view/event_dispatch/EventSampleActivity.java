package com.roujiamo.study.view.event_dispatch;

import com.roujiamo.base.base.BaseActivity;
import com.roujiamo.study.R;
import com.roujiamo.study.databinding.ActivityEventSampleBinding;

/**
 * 外层 ,内层, 底部都不拦截处理事件
 *
 * 外层ViewGroup dispatchTouchEvent: ACTION_DOWN
 * 外层ViewGroup onInterceptTouchEvent: ACTION_DOWN
 * 内层ViewGroup dispatchTouchEvent: ACTION_DOWN
 * 内层ViewGroup onInterceptTouchEvent: ACTION_DOWN
 * 底层view dispatchTouchEvent: ACTION_DOWN
 * 底层view onTouchEvent: ACTION_DOWN
 * 内层ViewGroup onTouchEvent: ACTION_DOWN
 * 外层ViewGroup onTouchEvent: ACTION_DOWN
 * 
 */
public class EventSampleActivity extends BaseActivity<ActivityEventSampleBinding> {


    @Override
    public int getLayoutId() {
        return R.layout.activity_event_sample;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }
}
