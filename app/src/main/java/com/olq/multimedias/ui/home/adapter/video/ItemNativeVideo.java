package com.olq.multimedias.ui.home.adapter.video;

import com.olq.multimedias.R;
import com.olq.multimedias.bean.NativeVideo;
import com.zhy.adapter.recyclerview.base.ItemViewDelegate;
import com.zhy.adapter.recyclerview.base.ViewHolder;

/**
 * Created by Administrator on 2016/9/28 0028.
 */
public class ItemNativeVideo implements ItemViewDelegate<NativeVideo>{

    @Override
    public int getItemViewLayoutId() {
        return R.layout.item_native;
    }

    @Override
    public boolean isForViewType(NativeVideo item, int position) {
        return position!=0;
    }

    @Override
    public void convert(ViewHolder holder, NativeVideo nativeVideo, int position) {
        if(position!=0&&position!=1){
//            holder.setText(R.id.tv_native_name,nativeVideo.getDisplayName());
//            holder.setImageBitmap(R.id.iv_native_image,nativeVideo.getThumbnail());

        }

    }
}
