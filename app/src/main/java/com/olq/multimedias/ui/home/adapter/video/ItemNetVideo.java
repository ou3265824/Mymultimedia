package com.olq.multimedias.ui.home.adapter.video;

import com.olq.multimedias.R;
import com.olq.multimedias.bean.NativeVideo;
import com.zhy.adapter.recyclerview.base.ItemViewDelegate;
import com.zhy.adapter.recyclerview.base.ViewHolder;

/**
 * Created by Administrator on 2016/9/28 0028.
 */
public class ItemNetVideo implements ItemViewDelegate<NativeVideo>{

    @Override
    public int getItemViewLayoutId() {
        return R.layout.item_net;
    }

    @Override
    public boolean isForViewType(NativeVideo item, int position) {
        return position==0;
    }

    @Override
    public void convert(ViewHolder holder, NativeVideo nativeVideo, int position) {

    }
}
