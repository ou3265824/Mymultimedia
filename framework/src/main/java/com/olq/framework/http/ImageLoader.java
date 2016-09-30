package com.olq.framework.http;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;


/**
 * Created by Administrator on 2016/5/16.
 */
public class ImageLoader {

    private static ImageLoader imageManager;
    private Context mContext;

    public ImageLoader(Context context) {
        this.mContext=context;
    }

    public static ImageLoader getInstance(Context context){
        if(imageManager==null){
            synchronized (ImageLoader.class){
                if(imageManager==null){
                    imageManager=new ImageLoader(context);
                }
            }
        }
        return imageManager;
    }

    public void displayImage(String url,ImageView imageView){
        Glide.with(mContext).load(url).into(imageView);
    }




}
