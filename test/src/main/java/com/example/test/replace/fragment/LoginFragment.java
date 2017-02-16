package com.example.test.replace.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.test.R;

/**
 * Created by Administrator on 2016/11/2 0002.
 */

public class LoginFragment extends Fragment{

    private OnClickListener onclick;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_login,null);
        TextView textView= (TextView) view.findViewById(R.id.tv_login);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onclick.onClick(view);
            }
        });
        return view;
    }

//    public void onlogin(View view){
//        onclick.onClick();
//    }

    public interface OnClickListener{
        void onClick(View view);
    }
    public void setOnclickListener(OnClickListener onclick) {
        this.onclick = onclick;
    }


}
