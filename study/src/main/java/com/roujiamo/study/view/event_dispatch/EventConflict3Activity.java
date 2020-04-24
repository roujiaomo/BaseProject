package com.roujiamo.study.view.event_dispatch;


import androidx.recyclerview.widget.LinearLayoutManager;

import com.roujiamo.base.base.BaseActivity;
import com.roujiamo.study.R;
import com.roujiamo.study.adapter.EventSampleRvAdapter;
import com.roujiamo.study.databinding.ActivityEventConflict3Binding;

import java.util.ArrayList;
import java.util.List;

/**
 * 父布局纵向ScrollView与纵向RecyclerView
 * 使用NestedScrollView则无冲突
 */
public class EventConflict3Activity extends BaseActivity<ActivityEventConflict3Binding> {

    private EventSampleRvAdapter mAdapter;
    private List<String> list = new ArrayList<>();

    @Override
    public int getLayoutId() {
        return R.layout.activity_event_conflict3;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        for (int i = 0; i < 30; i++) {
            list.add("第" + i + "条");
        }
        mBinding.rvContent.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new EventSampleRvAdapter(this, R.layout.item_event_sample);
        mBinding.rvContent.setAdapter(mAdapter);
        mAdapter.updateData(list,false);
    }
}
