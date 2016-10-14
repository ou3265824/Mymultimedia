package com.olq.multimedias.ui.base;


import com.olq.framework.base.BaseApplication;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by Administrator on 2016/8/8 0008.
 */
public class InitApplication extends BaseApplication {
    public static InitApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);
    }

    public static InitApplication getInstance() {
        return instance;
    }

}
