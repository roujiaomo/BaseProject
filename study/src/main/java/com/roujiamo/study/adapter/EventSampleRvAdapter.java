package com.roujiamo.study.adapter;

import android.content.Context;

import com.roujiamo.base.base.BaseAdapter;
import com.roujiamo.study.databinding.ItemEventSampleBinding;


public class EventSampleRvAdapter extends BaseAdapter<String> {

    public EventSampleRvAdapter(Context mContext, int layoutId) {
        super(mContext, layoutId);
    }

    @Override
    protected void bindData(BaseViewHolder holder, int position, String s) {
        ( (ItemEventSampleBinding)holder.viewDataBinding).tvContent.setText(s);
    }
}
