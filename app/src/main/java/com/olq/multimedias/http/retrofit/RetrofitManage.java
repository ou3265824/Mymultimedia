package com.olq.multimedias.http.retrofit;

import com.olq.framework.http.retrofit.HttpLoader;
import com.olq.framework.http.retrofit.callback.RxBackCall;
import com.olq.multimedias.bean.BaseBean;
import com.olq.multimedias.bean.User;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2016/11/4 0004.
 */

public class RetrofitManage {

    private static RetrofitManage retrofitManage;

    private RetrofitManage() {
    }

    public static RetrofitManage getIntance(){
        if (retrofitManage==null){
            synchronized (RetrofitManage.class){
                if (retrofitManage==null){
                    retrofitManage=new RetrofitManage();
                }
            }
        }
        return retrofitManage;
    }


    public void getRegister(User user,RxBackCall<BaseBean> observer){
        HttpLoader.getInstace().getCreate(UserApi.class).getUsers(user)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    public void getLogin(String username,String pass,RxBackCall<BaseBean> observer){
        HttpLoader.getInstace().getCreate(UserApi.class).getLogin(username,pass)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

}
