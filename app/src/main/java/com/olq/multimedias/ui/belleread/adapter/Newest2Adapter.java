package com.olq.multimedias.ui.belleread.adapter;

import android.content.Context;

import com.olq.multimedias.bean.Belle;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;

import java.util.List;

/**
 * Created by Administrator on 2016/10/20 0020.
 */

public class Newest2Adapter extends MultiItemTypeAdapter<Belle>{


    public Newest2Adapter(Context context, List<Belle> datas) {
        super(context, datas);
        addItemViewDelegate(new ItemNewestTitme());
        addItemViewDelegate(new ItemNewestContent());
    }
}
