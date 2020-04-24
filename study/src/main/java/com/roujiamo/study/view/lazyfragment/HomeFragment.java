package com.roujiamo.study.view.lazyfragment;

import android.util.Log;
import android.view.View;

import com.blankj.utilcode.util.ActivityUtils;
import com.roujiamo.base.base.BaseLazyFragment;
import com.roujiamo.study.R;
import com.roujiamo.study.databinding.FragmentHomeBinding;
import com.roujiamo.study.view.AutoSizeTestActivity;
import com.roujiamo.study.view.ServiceTestActivity;
import com.roujiamo.study.view.event_dispatch.EventConflict1Activity;
import com.roujiamo.study.view.event_dispatch.EventConflict2Activity;
import com.roujiamo.study.view.event_dispatch.EventConflict3Activity;
import com.roujiamo.study.view.event_dispatch.EventSampleActivity;
import com.roujiamo.study.viewmodel.MainViewModel;


public class HomeFragment extends BaseLazyFragment<MainViewModel, FragmentHomeBinding> implements View.OnClickListener {

    private static final String TAG = "HomeFragment";

    @Override
    public int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    public void initView() {
        mBinding.setOnClickListener(this);
    }

    @Override
    public void initData() {
        Log.d(TAG, "initData: " + "HomeFragment可见");
    }


    @Override
    public void initLiveDataObserve() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case 1:
                final int i = 0;
                break;
        }
        int id = v.getId();
        if (id == R.id.btn_test_autoSize) {
            ActivityUtils.startActivity(AutoSizeTestActivity.class);
        } else if (id == R.id.btn_test_service) {
            ActivityUtils.startActivity(ServiceTestActivity.class);
        } else if (id == R.id.btn_event_conflict1) {
            ActivityUtils.startActivity(EventConflict1Activity.class);
        } else if (id == R.id.btn_event_conflict2) {
            ActivityUtils.startActivity(EventConflict2Activity.class);
        } else if (id == R.id.btn_event_conflict3) {
            ActivityUtils.startActivity(EventConflict3Activity.class);
        } else if (id == R.id.btn_event_sample) {
            ActivityUtils.startActivity(EventSampleActivity.class);
        }
    }

}
