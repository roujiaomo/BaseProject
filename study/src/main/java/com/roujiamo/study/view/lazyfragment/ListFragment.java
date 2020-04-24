package com.roujiamo.study.view.lazyfragment;

import androidx.recyclerview.widget.LinearLayoutManager;


import com.roujiamo.base.base.BaseLazyFragment;
import com.roujiamo.study.R;
import com.roujiamo.study.adapter.EventSampleRvAdapter;
import com.roujiamo.study.databinding.FragmentListBinding;
import com.roujiamo.study.viewmodel.MainViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * 列表Fragment
 */

public class ListFragment extends BaseLazyFragment<MainViewModel, FragmentListBinding> {

    private static final String TAG = "ListFragment";
    private EventSampleRvAdapter mAdapter;
    private List<String> list = new ArrayList<>();

    @Override
    public int getLayoutId() {
        return R.layout.fragment_list;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        for (int i = 0; i < 30; i++) {
            list.add("第" + i + "条");
        }
        mBinding.rvContent.setLayoutManager(new LinearLayoutManager(mActivity));
        mAdapter = new EventSampleRvAdapter(mActivity, R.layout.item_event_sample);
        mBinding.rvContent.setAdapter(mAdapter);
        mAdapter.updateData(list,false);
    }

    @Override
    public void initLiveDataObserve() {

    }

}
