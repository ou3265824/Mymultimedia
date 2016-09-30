package com.olq.multimedias.ui.home.adapter.video;

import android.content.Context;

import com.olq.multimedias.bean.NativeVideo;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;

import java.util.List;

/**
 * Created by Administrator on 2016/9/28 0028.
 */
public class VideoAdapter extends MultiItemTypeAdapter<NativeVideo>{

    public VideoAdapter(Context context, List<NativeVideo> datas) {
        super(context, datas);
        addItemViewDelegate(new ItemNetVideo());
        addItemViewDelegate(new ItemNativeVideo());
    }
}
