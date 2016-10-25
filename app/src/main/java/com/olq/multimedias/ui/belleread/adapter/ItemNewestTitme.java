package com.olq.multimedias.ui.belleread.adapter;

import com.olq.multimedias.bean.Belle;
import com.zhy.adapter.abslistview.base.ItemViewDelegateManager;
import com.zhy.adapter.recyclerview.base.ItemViewDelegate;
import com.zhy.adapter.recyclerview.base.ViewHolder;

/**
 * Created by Administrator on 2016/10/20 0020.
 */

public class ItemNewestTitme implements ItemViewDelegate<Belle> {


    @Override
    public int getItemViewLayoutId() {
        return 0;
    }

    @Override
    public boolean isForViewType(Belle item, int position) {
        return false;
    }

    @Override
    public void convert(ViewHolder holder, Belle belle, int position) {

    }
}
