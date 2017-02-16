package com.olq.multimedias.ui.user;

import android.widget.AutoCompleteTextView;
import android.widget.EditText;

import com.olq.framework.http.retrofit.HttpLoader;
import com.olq.framework.http.retrofit.callback.RxBackCall;
import com.olq.framework.utils.L;
import com.olq.multimedias.R;
import com.olq.multimedias.bean.BaseBean;
import com.olq.multimedias.bean.User;
import com.olq.multimedias.http.retrofit.AppConfig;
import com.olq.multimedias.http.retrofit.RetrofitManage;
import com.olq.multimedias.http.retrofit.UserApi;
import com.olq.multimedias.ui.base.InitActivity;

import butterknife.Bind;
import butterknife.OnClick;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * A login screen that offers login via email/password.
 */
public class RegisterActivit extends InitActivity {

    @Bind(R.id.username)
    AutoCompleteTextView username;
    @Bind(R.id.password)
    EditText password;

    @Override
    protected int getLayout() {
        return R.layout.activity_register;
    }

    @Override
    protected void getonCreate() {
        L.log("url:"+AppConfig.USERS);
    }



    @OnClick(R.id.register)
    public void onClick() {
        User user=new User();
        user.setUsername(username.getText().toString());
        user.setPassword(password.getText().toString());
        RetrofitManage.getIntance().getRegister(user, new RxBackCall<BaseBean>() {
            @Override
            public void onCompleted() {
                L.log("onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                L.log("onError"+e.toString());
            }

            @Override
            public void onNext(BaseBean baseBean) {
                L.log("onNext"+baseBean.toString());
            }
        });
//        HttpLoader.getInstace().getCreate(UserApi.class).getUsers(user).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<BaseBean>() {
//            @Override
//            public void onCompleted() {
//
//            }
//
//            @Override
//            public void onError(Throwable e) {
//
//            }
//
//            @Override
//            public void onNext(BaseBean baseBean) {
//
//            }
//        });
    }
}

