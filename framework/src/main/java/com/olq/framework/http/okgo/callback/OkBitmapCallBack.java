package com.olq.framework.http.okgo.callback;

import android.graphics.Bitmap;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by Administrator on 2016/10/20 0020.
 */

public abstract class OkBitmapCallBack extends OkHttpCallBack<Bitmap>{


    public abstract void onSuccess(Bitmap bitmap, Call call, Response response);

    public abstract void onError(Call call, Response response, Exception e);


}
