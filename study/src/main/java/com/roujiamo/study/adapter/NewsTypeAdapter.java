package com.roujiamo.study.adapter;

import android.content.Context;

import com.roujiamo.base.base.BaseAdapter;
import com.roujiamo.base.interfaces.MultipleType;
import com.roujiamo.http.bean.NewsBean;
import com.roujiamo.study.R;
import com.roujiamo.study.databinding.ItemNewsBinding;


/**
 * 多item布局adapter
 */
public class NewsTypeAdapter extends BaseAdapter<NewsBean> {
    public NewsTypeAdapter(Context mContext) {
        super(mContext, new MultipleType<NewsBean>() {
                    @Override
                    public int getLayoutId(NewsBean item, int position) {
                        //根据数据源或位置不同条件加载不同布局
                        switch (position){
                            case 0:
                                return R.layout.item_news_head;
                            case 99:
                                return R.layout.item_news_footer;
                            default:
                                return  R.layout.item_news;
                        }
                    }
                });
    }

    @Override
    protected void bindData(BaseViewHolder holder, int position, NewsBean newsBean) {
        ItemNewsBinding viewDataBinding = (ItemNewsBinding) holder.viewDataBinding;
        viewDataBinding.tvContent.setText("");
    }
}
