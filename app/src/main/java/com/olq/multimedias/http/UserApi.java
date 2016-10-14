package com.olq.multimedias.http;

import com.olq.multimedias.bean.BaseBean;
import com.olq.multimedias.bean.User;

import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Administrator on 2016/10/8 0008.
 */
public interface UserApi {

    //注册
    @POST(AppConfig.USERS)
    Observable<BaseBean> getUsers(@Body User user);

    //登录
    @GET(AppConfig.LOGIN)
    Observable<BaseBean> getLogin(@Query("username") String username, @Query("password") String password);

}
