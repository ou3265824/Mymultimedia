package com.olq.multimedias.ui.home.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by Administrator on 2016/9/28 0028.
 */
public class HomePagerAdapter extends FragmentPagerAdapter {

    private List<Fragment> mViews;
    private Context mContext;

    public HomePagerAdapter(FragmentManager fm, List<Fragment> views) {
        super(fm);
        mViews=views;
    }

    public HomePagerAdapter(FragmentManager fm, List<Fragment> views, Context context) {
        super(fm);
        mViews=views;
        mContext=context;
    }

    @Override
    public Fragment getItem(int position) {
        return mViews.get(position);
    }

    @Override
    public int getCount() {
        return mViews.size();
    }

}


