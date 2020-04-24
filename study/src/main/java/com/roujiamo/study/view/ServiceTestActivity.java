package com.roujiamo.study.view;

import android.content.ComponentName;
import android.content.Context;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.view.View;

import com.blankj.utilcode.util.ServiceUtils;
import com.roujiamo.base.base.BaseDataActivity;
import com.roujiamo.base.base.BaseViewModel;
import com.roujiamo.study.R;
import com.roujiamo.study.databinding.ActivityTestServiceBinding;
import com.roujiamo.study.service.ExampleService;

public class ServiceTestActivity extends BaseDataActivity<BaseViewModel, ActivityTestServiceBinding> implements View.OnClickListener {
    @Override
    public int getLayoutId() {
        return R.layout.activity_test_service;
    }

    @Override
    public void initView() {
        mBinding.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
//            case R.id.btn_startService:
//                ServiceUtils.startService(ExampleService.class);
//                break;
//            case R.id.btn_stopService:
//                ServiceUtils.stopService(ExampleService.class);
//                break;
//            case R.id.btn_bindService:
//                ServiceUtils.bindService(ExampleService.class, conn, Context.BIND_AUTO_CREATE);
//                break;
//            case R.id.btn_unBindService:
//                ServiceUtils.unbindService(conn);
//                break;
        }
    }

    private ServiceConnection conn = new ServiceConnection() {

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            ExampleService.MyBinder binder = (ExampleService.MyBinder)service;
            ExampleService exampleService = binder.getService();
            exampleService.fromActivity();
            //拿到service对象 处理service对象数据
        }
    };

    @Override
    public void initData() {

    }

    @Override
    public void initLiveDataObserve() {

    }
}
