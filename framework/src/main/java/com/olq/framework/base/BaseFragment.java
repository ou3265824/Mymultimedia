package com.olq.framework.base;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.olq.framework.utils.ToastUtil;

import butterknife.ButterKnife;

/**
 * Created by olq on 2016/5/27.
 */
public abstract class BaseFragment extends Fragment {

    public View mView;

    public abstract int getFragmentLayout();

    public abstract void getFragmentCreateView();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (mView != null) {
            ButterKnife.bind(this, mView);
            getFragmentCreateView();
            return mView;
        }
        mView = inflater.inflate(getFragmentLayout(), container,false);
        ButterKnife.bind(this, mView);
        getFragmentCreateView();
        return mView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    public <T extends View> T getFindViewById(int id){
        return (T) mView.findViewById(id);
    }

    public void startActivity(Class<? extends Activity> target, Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(getActivity(), target);
        if (bundle != null)
            intent.putExtras(bundle);
        getActivity().startActivity(intent);
    }

    public void onToast(Object message){
        if(message instanceof String)
        {
            ToastUtil.show(getActivity(),(String) message);
        }else if(message instanceof Integer){
            ToastUtil.show(getActivity(), getString((Integer) message));
        }else{
            ToastUtil.show(getActivity(),(String) message);
        }
    }

    public void onToastLong(String message){
        ToastUtil.showLong(getActivity(),message);
    }


}
