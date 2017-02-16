package com.example.test;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.test.replace.ReplaceActivity;
import com.example.test.replace.fragment.LoginFragment;

public class MainActivity extends AppCompatActivity {

    private int[] imgs={R.mipmap.ic_launcher,R.mipmap.ic_launcher,R.mipmap.ic_launcher,R.mipmap.ic_launcher};
    private Button button;
    private ImageView[] imageViews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView imageView = (ImageView) findViewById(R.id.image);
        ImageView imageView2 = (ImageView) findViewById(R.id.image2);
        ImageView imageView3 = (ImageView) findViewById(R.id.image3);
        ImageView imageView4 = (ImageView) findViewById(R.id.image4);
        ImageView imageView5 = (ImageView) findViewById(R.id.image5);
//        ImageView imageView2 = (ImageView) findViewById(R.id.image);
        imageViews = new ImageView[]{imageView4,imageView3,imageView2,imageView};
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(getApplicationContext(),"点击",Toast.LENGTH_LONG).show();
//            startActivity(new Intent(getApplicationContext(), ReplaceActivity.class));
            }
        });
        imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                startActivity(new Intent(getApplicationContext(), JsoupActivity.class));
            }
        });

//        button = (Button) findViewById(R.id.button);
        imageView5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(getApplicationContext(),"点击",Toast.LENGTH_LONG).show();
//                button();
            }
        });

    }

    /**
     *  translationX、translationY
     *  rotation
     *
     *
     */
    public void button(){
//        TranslateAnimation
//        RotateAnimation

        //属性动画 平移 translation
//        ObjectAnimator.ofFloat(imageView,"translationX",0f,200f).setDuration(3000).start();
//        ObjectAnimator.ofFloat(imageView,"translationY",0f,200f).setDuration(3000).start();
        //旋转 rotation
//        ObjectAnimator.ofFloat(imageView,"rotation",0F,270F).setDuration(3000).start();
//        ObjectAnimator.ofFloat(imageView,"rotationX",0F,270F).setDuration(3000).start();
//        ObjectAnimator.ofFloat(imageView,"rotationY",0F,270F).setDuration(3000).start();
        //透明度 alpha 0-1之前
//        ObjectAnimator.ofFloat(imageView,"alpha",0f,0.5f).setDuration(2000).start();
        //缩放
//        ObjectAnimator.ofFloat(imageView,"ScaleX",0f,1f).setDuration(2000).start();
//        ObjectAnimator.ofFloat(imageView,"ScaleY",0f,1f).setDuration(2000).start();


          //优化组合动画，节省系统资源
//        PropertyValuesHolder p1=PropertyValuesHolder.ofFloat("rotation",0F,270F);
//        PropertyValuesHolder p2=PropertyValuesHolder.ofFloat("translationX",0F,270F);
//        PropertyValuesHolder p3=PropertyValuesHolder.ofFloat("translationY",0F,270F);
//        ObjectAnimator.ofPropertyValuesHolder(imageView,p1,p2,p3).setDuration(3000).start();

        //属性动画 平移
//        ObjectAnimator animator1=ObjectAnimator.ofFloat(imageView,"translationX",0f,200f);
//        ObjectAnimator animator2=ObjectAnimator.ofFloat(imageView,"translationY",0f,200f);
//        //旋转
//        ObjectAnimator animator3=ObjectAnimator.ofFloat(imageView,"rotation",0F,270F);
//        //组合动画
//        AnimatorSet set=new AnimatorSet();
//        //
//        set.play(animator1).with(animator2);//同时进行
//        set.play(animator3).after(animator1);//完成
//        //一起执行动画
////        set.playTogether(animator1,animator2,animator3);
//        //按顺序执行动画
////        set.playSequentially(animator1,animator2,animator3);
//        set.setDuration(3000).start();

        //监听
//        ObjectAnimator animator=ObjectAnimator.ofFloat(imageView,"alpha",0f,0.5f);
//        animator.addListener(new AnimatorListenerAdapter() {
//            @Override
//            public void onAnimationEnd(Animator animation) {
//                super.onAnimationEnd(animation);
//                Toast.makeText(getApplicationContext(),"运行完成",Toast.LENGTH_LONG).show();
//            }
//        });
////        animator.addListener(new Animator.AnimatorListener() {
////            @Override //开始
////            public void onAnimationStart(Animator animator) {
////
////            }
////
////            @Override //结束
////            public void onAnimationEnd(Animator animator) {
////                Toast.makeText(getApplicationContext(),"完成",Toast.LENGTH_LONG).show();
////            }
////
////            @Override
////            public void onAnimationCancel(Animator animator) {
////
////            }
////
////            @Override
////            public void onAnimationRepeat(Animator animator) {
////
////            }
////        });
//
//        animator.setDuration(2000).start();


        float radian= (float) (Math.PI/180*30);
        float q= (float) (Math.PI/180*90);
        float w= (float) (Math.PI/2);
        Log.i("test","radian:"+q+"---"+w);
        for (int i=0;i<imageViews.length;i++){
            float x = (float) (500 * Math.cos(radian * i));
            float y = (float) (500 * Math.sin(radian * i));
            ObjectAnimator animator1=ObjectAnimator.ofFloat(imageViews[i],"translationX",0,x);
            ObjectAnimator animator2=ObjectAnimator.ofFloat(imageViews[i],"translationY",0,y);
//        //旋转
//            ObjectAnimator animator3=ObjectAnimator.ofFloat(imageViews[i],"rotation",0F,3600F);
//        //组合动画
            AnimatorSet set=new AnimatorSet();
            set.playTogether(animator1,animator2);
            set.setStartDelay(i*300);
            set.setDuration(1000).start();
        }


    }





}
