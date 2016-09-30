package com.olq.framework.base;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;


import com.olq.framework.R;
import com.olq.framework.utils.ToastUtil;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/5/17.
 */
public abstract class BaseActivity extends AppCompatActivity {

    protected abstract int getLayout();

    /**
     * onCreate方法
     *
     * @param
     */
    protected abstract void getonCreate();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        BaseApplication.getInstance().addActivity(this);
        BaseApplication.getInstance().setActivity(this);
        setContentView(getLayout());
        ButterKnife.bind(this);
        getonCreate();
    }



    @Override
    protected void onResume() {
        super.onResume();
        BaseApplication.getInstance().setActivity(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
        BaseApplication.getInstance().removeActivity(this);
        BaseApplication.getInstance().setActivity(null);
    }



    public void startActivity(Class<? extends Activity> target, Bundle bundle, boolean finish) {
        Intent intent = new Intent();
        intent.setClass(this, target);
        if (bundle != null)
            intent.putExtras(bundle);
        startActivity(intent);
        if (finish)
            finish();
    }

    public void onToast(Object message){
        if(message instanceof String)
        {
            ToastUtil.show(this,(String) message);
        }else if(message instanceof Integer){
            ToastUtil.show(this, getString((Integer) message));
        }else{
            ToastUtil.show(this,(String) message);
        }
    }

    public void onToastLong(String message){
        ToastUtil.showLong(this,message);
    }

    long exitTime=0;
    public void onExit() {
        if ((System.currentTimeMillis() - exitTime) > 2000) {
            ToastUtil.show(this, "再按一次退出程序");
            exitTime = System.currentTimeMillis();
        }else {
            finish();
            System.exit(0);
        }
    }

}
