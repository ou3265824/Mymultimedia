package com.olq.framework.http.retrofit;


import com.google.gson.Gson;
import com.olq.framework.http.retrofit.builder.HeaderInterceptor;
import com.olq.framework.http.retrofit.builder.LoggingInterceptor;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2016/4/27.
 */
public class HttpLoader {

    private OkHttpClient client;
    private Retrofit retrofit;
    private static HttpLoader httpLoader;
    private static int OVER_TIME=30000;
    private static String BASE_URL="https://api.bmob.cn/";

    private HttpLoader() {
        if(client==null)
        {
            client=getOkHttpClient();
        }
        if(retrofit==null){
            retrofit=getRetrofit();
        }
    }

    public static HttpLoader getInstace() {
        if (httpLoader == null) {
            synchronized (HttpLoader.class) {
                if (httpLoader == null) {
                    httpLoader = new HttpLoader();
                }
            }
        }
        return httpLoader;
    }



    private OkHttpClient getOkHttpClient(){
        return new OkHttpClient.Builder().addInterceptor(new LoggingInterceptor())
                .retryOnConnectionFailure(true).connectTimeout(OVER_TIME, TimeUnit.SECONDS)
                .addNetworkInterceptor(new HeaderInterceptor()).build();
    }

    private Retrofit getRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }

    public <T> T getCreate(Class<T> clazz) {
        return retrofit.create(clazz);
    }




}
