package com.olq.framework.http;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;


/**
 * Created by Administrator on 2016/5/16.
 */
public class GlideLoader {

    private static GlideLoader imageManager;
    private Context mContext;

    public GlideLoader(Context context) {
        this.mContext=context;
    }

    public static GlideLoader getInstance(Context context){
        if(imageManager==null){
            synchronized (GlideLoader.class){
                if(imageManager==null){
                    imageManager=new GlideLoader(context);
                }
            }
        }
        return imageManager;
    }

    public void displayImage(String url,ImageView imageView){
        Glide.with(mContext).load(url).into(imageView);
    }




}
