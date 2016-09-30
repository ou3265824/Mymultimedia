package com.olq.multimedias.base;

import android.content.Context;
import android.location.LocationManager;


import com.olq.framework.base.BaseApplication;

import java.util.List;

/**
 * Created by Administrator on 2016/8/8 0008.
 */
public class InitApplication extends BaseApplication {
    public static InitApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }
    public static InitApplication getInstance() {
        return instance;
    }
}
