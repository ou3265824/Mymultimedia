package com.olq.multimedias.ui.belleread.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.olq.framework.http.GlideLoader;
import com.olq.framework.utils.ScreenUtils;
import com.olq.multimedias.R;
import com.olq.multimedias.bean.Belle;
import com.olq.multimedias.http.okgo.OkUrlConfig;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

/**
 * Created by Administrator on 2016/10/20 0020.
 */

public class NewestAdapter extends CommonAdapter<Belle>{

    private Context mContext;

    public NewestAdapter(Context context, int layoutId, List<Belle> datas) {
        super(context, layoutId, datas);
        mContext=context;
    }

    @Override
    protected void convert(ViewHolder holder, Belle belle, int position) {
        holder.setText(R.id.tv_hot_title,belle.getTitle());
        ImageView imageView=holder.getView(R.id.iv_hot_img);
        ScreenUtils.changeWH(imageView, (int) (ScreenUtils.getScreenWidth(mContext)*0.7), (int) (ScreenUtils.getScreenHeight(mContext)*0.49));
        GlideLoader.getInstance(mContext).displayImage(OkUrlConfig.IMG_HTTP+belle.getImg(),imageView);
    }



}
