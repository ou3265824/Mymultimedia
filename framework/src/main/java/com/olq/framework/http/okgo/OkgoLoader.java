package com.olq.framework.http.okgo;

import android.app.Application;
import android.graphics.Bitmap;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheEntity;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.callback.BitmapCallback;
import com.lzy.okgo.callback.FileCallback;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.cookie.store.PersistentCookieStore;
import com.olq.framework.http.okgo.callback.OkFileCallBack;
import com.olq.framework.http.okgo.callback.OkGsonCallBack;
import com.olq.framework.http.okgo.callback.OkHttpCallBack;
import com.olq.framework.http.okgo.callback.OkStringCallBack;
import com.olq.framework.utils.GsonUtils;

import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Response;

import static android.R.string.ok;

/**
 * Created by Administrator on 2016/10/20 0020.
 */

public class OkgoLoader {

    private static OkgoLoader mOkgoLoader;

    public OkgoLoader() {

    }

    public static OkgoLoader getInstace(){
        if (mOkgoLoader==null){
            synchronized (OkgoLoader.class){
                if(mOkgoLoader==null){
                    mOkgoLoader=new OkgoLoader();
                }
            }
        }
        return mOkgoLoader;
    }

    /**
     * 初始化
     * @param app
     */
    public void init(Application app){
        OkGo.init(app);
        getConfiguration();
    }


    public void getConfiguration(){

        //以下设置的所有参数是全局参数,同样的参数可以在请求的时候再设置一遍,那么对于该请求来讲,请求中的参数会覆盖全局参数
        //好处是全局参数统一,特定请求可以特别定制参数
        try {
            //以下都不是必须的，根据需要自行选择,一般来说只需要 debug,缓存相关,cookie相关的 就可以了
            OkGo.getInstance()

                    //打开该调试开关,控制台会使用 红色error 级别打印log,并不是错误,是为了显眼,不需要就不要加入该行
                    .debug("OkGo")

                    //如果使用默认的 60秒,以下三行也不需要传
                    .setConnectTimeout(OkGo.DEFAULT_MILLISECONDS)  //全局的连接超时时间
                    .setReadTimeOut(OkGo.DEFAULT_MILLISECONDS)     //全局的读取超时时间
                    .setWriteTimeOut(OkGo.DEFAULT_MILLISECONDS)    //全局的写入超时时间

                    //可以全局统一设置缓存模式,默认是不使用缓存,可以不传,具体其他模式看 github 介绍 https://github.com/jeasonlzy/
                    .setCacheMode(CacheMode.REQUEST_FAILED_READ_CACHE)

                    //可以全局统一设置缓存时间,默认永不过期,具体使用方法看 github 介绍
                    .setCacheTime(CacheEntity.CACHE_NEVER_EXPIRE);

                    //如果不想让框架管理cookie,以下不需要
//                .setCookieStore(new MemoryCookieStore())        //cookie使用内存缓存（app退出后，cookie消失）
//                    .setCookieStore(new PersistentCookieStore());  //cookie持久化存储，如果cookie不过期，则一直有效

                    //可以设置https的证书,以下几种方案根据需要自己设置,不需要不用设置
//                    .setCertificates()                                  //方法一：信任所有证书
//                    .setCertificates(getAssets().open("srca.cer"))      //方法二：也可以自己设置https证书
//                    .setCertificates(getAssets().open("aaaa.bks"), "123456", getAssets().open("srca.cer"))//方法三：传入bks证书,密码,和cer证书,支持双向加密

                    //可以添加全局拦截器,不会用的千万不要传,错误写法直接导致任何回调不执行
//                .addInterceptor(new Interceptor() {
//                    @Override
//                    public Response intercept(Chain chain) throws IOException {
//                        return chain.proceed(chain.request());
//                    }
//                })

                    //这两行同上,不需要就不要传
//                    .addCommonHeaders(headers) //设置全局公共头
//                    .addCommonParams(params);  //设置全局公共参数
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getSendByGet(String url, final OkGsonCallBack<?> callback){
        OkGo.get(url)     // 请求方式和请求url
                .tag(this)                       // 请求的 tag, 主要用于取消对应的请求
                .cacheKey(url)            // 设置当前请求的缓存key,建议每个不同功能的请求设置一个
//                .cacheMode(CacheMode.DEFAULT)    // 缓存模式，详细请看缓存介绍
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        // s 即为所需要的结果
                        Success(callback,s,call,response);
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        Error(callback,call, response, e);
                    }
                });
    }

    /**
     * 图片请求
     * @param url
     * @param callback
     */
    public void getDisplayByBitmap(String url,BitmapCallback callback){
        OkGo.get(url)//
                .tag(this)
                .cacheKey(url)
//                .execute(callback);

                .execute(new BitmapCallback() {
                    @Override
                    public void onSuccess(Bitmap bitmap, Call call, Response response) {
                        // bitmap 即为返回的图片数据
                    }
                });
    }

    /**
     * 下载文件，get请求
     * @param url
     * @param file
     * @param callback
     */
    public void getDisplayByGet(String url,String file,FileCallback callback){
        OkGo.get(url)//
                .tag(this)
                .cacheKey(url)
                .execute(callback);

//                .execute(new FileCallback(file) {  //文件下载时，需要指定下载的文件目录和文件名
//                    @Override
//                    public void onSuccess(File file, Call call, Response response) {
//                        // file 即为文件数据，文件保存在指定目录
//                    }
//
//                    @Override
//                    public void downloadProgress(long currentSize, long totalSize, float progress, long networkSpeed) {
//                        //这里回调下载进度(该回调在主线程,可以直接更新ui)
//                    }
//                });
    }

    /**
     * string格式post请求
     * @param url
     * @param string
     * @param callback
     */
    public void getDisplayByPost(String url,String string,StringCallback callback){
        OkGo.post(url)//
                .tag(this)//
                .cacheKey(url)
                .upString(string).execute(callback);

//                .execute(new StringCallback() {
//                    @Override
//                    public void onSuccess(String s, Call call, Response response) {
//                        //上传成功
//                    }
//
//                    @Override
//                    public void upProgress(long currentSize, long totalSize, float progress, long networkSpeed) {
//                        //这里回调上传进度(该回调在主线程,可以直接更新ui)
//                    }
//                });
    }

    /**
     * json格式post请求
     * @param url
     * @param params
     * @param callback
     */
    public void getDisplayByPost(String url, Map params,StringCallback callback){
//        Map params = new HashMap<>();
//        params.put("key1", "value1");
//        params.put("key2", "这里是需要提交的json格式数据");
//        params.put("key3", "也可以使用三方工具将对象转成json字符串");
//        params.put("key4", "其实你怎么高兴怎么写都行");
        JSONObject jsonObject = new JSONObject(params);
        OkGo.post(url)//
                .tag(this)//
                .cacheKey(url)
                .upJson(jsonObject.toString())
                .execute(callback);

//                .execute(new StringCallback() {
//                    @Override
//                    public void onSuccess(String s, Call call, Response response) {
//                        //上传成功
//                    }
//
//
//                    @Override
//                    public void upProgress(long currentSize, long totalSize, float progress, long networkSpeed) {
//                        //这里回调上传进度(该回调在主线程,可以直接更新ui)
//                    }
//                });
    }

    /**
     * 同步请求
     * @param url
     */
    public Response getResponseGet(String url){
        Response response = null;
        try {
            response = OkGo.get(url)//
                    .tag(this)//
                    .cacheKey(url)
//                    .headers("aaa", "111")//
//                    .params("bbb", "222")
                    .execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  response;
    }


    //成功回调
    private <T> void Success(OkHttpCallBack<T> callback,String result,Call call, Response response)
    {
        if(callback!=null)
        {
//            callback.onSuccess((T) result,call,response);
            if (callback instanceof OkGsonCallBack)
            {
                OkGsonCallBack gsonCallBack=(OkGsonCallBack)callback;
                callback.onSuccess((T) GsonUtils.getBeanFromJson(result, gsonCallBack.getType()),call,response);
            }else if(callback instanceof OkStringCallBack){
                ((OkStringCallBack)callback).onSuccess(result,call,response);
            }else if(callback instanceof OkFileCallBack){
//                ((OkFileCallBack)callback).onSuccess(result,call,response);
            }
        }

    }

    //失败回调
    private <T> void Error(OkHttpCallBack<T> callback, Call call, Response response, Exception e){
        if(callback!=null)
        {
            callback.onError(call,response,e);
        }
    }

}
