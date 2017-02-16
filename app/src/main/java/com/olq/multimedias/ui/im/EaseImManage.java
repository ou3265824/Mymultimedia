package com.olq.multimedias.ui.im;

import android.util.Log;

import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;
import com.olq.framework.http.retrofit.HttpLoader;
import com.olq.framework.http.retrofit.callback.RxBackCall;
import com.olq.multimedias.bean.BaseBean;
import com.olq.multimedias.bean.User;
import com.olq.multimedias.http.retrofit.UserApi;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2016/11/4 0004.
 */

public class EaseImManage {

    private static EaseImManage easeImManage;

    private EaseImManage() {
    }

    public static EaseImManage getIntance(){
        if (easeImManage==null){
            synchronized (EaseImManage.class){
                if (easeImManage==null){
                    easeImManage=new EaseImManage();
                }
            }
        }
        return easeImManage;
    }


    public void getRegister(User user,RxBackCall<BaseBean> observer){
        HttpLoader.getInstace().getCreate(UserApi.class).getUsers(user)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    public void getLogin(String username,String pass,RxBackCall<BaseBean> observer){
        EMClient.getInstance().login(username,pass,new EMCallBack() {//回调
            @Override
            public void onSuccess() {
                EMClient.getInstance().groupManager().loadAllGroups();
                EMClient.getInstance().chatManager().loadAllConversations();
                Log.d("main", "登录聊天服务器成功！");
            }

            @Override
            public void onProgress(int progress, String status) {

            }

            @Override
            public void onError(int code, String message) {
                Log.d("main", "登录聊天服务器失败！");
            }
        });
    }

}
