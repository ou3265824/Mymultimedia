package com.olq.multimedias.ui.home.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;

import com.olq.multimedias.R;
import com.olq.multimedias.ui.base.InitFragment;
import com.olq.multimedias.ui.belleread.NewestFragment;

import butterknife.Bind;
import butterknife.ButterKnife;


public class BelleReadFragment extends InitFragment {

    @Bind(R.id.tl_belle_tab)
    TabLayout tlBelleTab;
    @Bind(R.id.vp_belle_pager)
    ViewPager vpBellePager;


    @Override
    public int getFragmentLayout() {
        return R.layout.fragment_belle_read;
    }

    @Override
    public void getFragmentCreateView() {
        vpBellePager.setAdapter(new FragmentPagerAdapter(getChildFragmentManager()) {
//            private String[] tabName = {"推荐","性感美女", "日韩美女", "丝袜美腿", "美女照片","美女写真","清纯美女","性感车模"};
            private String[] tabName = {"0","1", "2", "3", "4","5","6","7"};
            @Override
            public Fragment getItem(int position) {
                return new NewestFragment();
            }

            @Override
            public int getCount() {
                return tabName.length;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return tabName[position];
            }
        });
        tlBelleTab.setupWithViewPager(vpBellePager);

        tlBelleTab.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }



}
