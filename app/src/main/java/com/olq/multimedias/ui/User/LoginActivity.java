package com.olq.multimedias.ui.user;

import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;

import com.olq.framework.http.retrofit.HttpLoader;
import com.olq.multimedias.R;
import com.olq.multimedias.bean.BaseBean;
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
public class LoginActivity extends InitActivity {

    @Bind(R.id.username)
    AutoCompleteTextView username;
    @Bind(R.id.password)
    EditText password;
    @Bind(R.id.login)
    Button login;
    @Bind(R.id.register)
    Button register;

    @Override
    protected int getLayout() {
        return R.layout.activity_login;
    }

    @Override
    protected void getonCreate() {

    }



    @OnClick({R.id.login, R.id.register})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.login:
                HttpLoader.getInstace().getCreate(UserApi.class).getLogin(username.getText().toString(),password.getText().toString()).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<BaseBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(BaseBean baseBean) {

                    }
                });
                break;
            case R.id.register:
                startActivity(RegisterActivit.class,null,false);
                break;
        }
    }
}

