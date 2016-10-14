package com.olq.framework.http.builder;


import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;


/**
 * Created by Administrator on 2016/4/27.
 */
public class HeaderInterceptor implements Interceptor {

    private String APP_ID_KEY="X-Bmob-Application-Id";
    private String APP_ID_VALUE="d8b33ee0db2961e37d17964a359ee74a";

    private String API_KEY_KEY="X-Bmob-REST-API-Key";
    private String API_KEY_VALUE="026742c58a756de64b31d60f1a634af3";

    @Override
    public Response intercept(Chain chain) throws IOException {

        Request request=chain.request().newBuilder()
                .addHeader(APP_ID_KEY,APP_ID_VALUE)
                .addHeader(API_KEY_KEY,API_KEY_VALUE)
                .build();
        return chain.proceed(request);
    }



}
