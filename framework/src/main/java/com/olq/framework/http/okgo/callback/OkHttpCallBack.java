package com.olq.framework.http.okgo.callback;

import com.olq.framework.base.BaseCallBack;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by Administrator on 2016/10/20 0020.
 */

public abstract class OkHttpCallBack<T> extends BaseCallBack{

    public abstract void onSuccess(T t, Call call, Response response);

    public abstract void onError(Call call, Response response, Exception e);


}
