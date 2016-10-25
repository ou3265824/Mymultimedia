package com.olq.framework.http.okgo.callback;

import java.io.File;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by Administrator on 2016/10/20 0020.
 */

public abstract class OkFileCallBack extends OkHttpCallBack<File>{

    public abstract void onSuccess(File t, Call call, Response response);

    public abstract void onError(Call call, Response response, Exception e);


}
