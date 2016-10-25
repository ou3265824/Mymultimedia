package com.olq.framework.utils;

import com.google.gson.Gson;

import java.lang.reflect.Type;

/**
 * Created by Administrator on 2016/10/20 0020.
 */

public class GsonUtils {


    private static Gson gson=new Gson();

    //解析json数据
    public static <T> T getBeanFromJson(String json,Type type){
        return gson.fromJson(json, type);
    }

    public static <T> T getBeanFromJson(String json,Class<T> clazz){
        return gson.fromJson(json, clazz);
    }

    //对象转换成json数据
    public static String getBeanToJson(Object json)
    {
        return gson.toJson(json);
    }

}
