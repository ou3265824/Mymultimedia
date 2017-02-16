package com.example.test.wieght;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Scroller;

/**
 * 自定义
 */

public class MyScrollView extends ViewGroup{

    private Context mContext;
    private Scroller mScroller;
    private int mScreenHeight;

    public MyScrollView(Context context) {
        super(context);
        mContext=context;
        init();
    }

    public MyScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext=context;
        init();
    }

    public MyScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext=context;
        init();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int count=getChildCount();
        Log.i("test","子view个数："+count);

        for (int i=0;i<count;i++){
            View childView=getChildAt(i);
            measureChild(childView,widthMeasureSpec,heightMeasureSpec);
        }
    }


        @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int count=getChildCount();
        Log.i("test","子view个数："+count);
        MarginLayoutParams params= (MarginLayoutParams) getLayoutParams();
        params.height=mScreenHeight*count;
        setLayoutParams(params);
        for (int i=0;i<count;i++){
            View childView=getChildAt(i);
            if (childView.getVisibility()!=View.GONE){
                childView.layout(l,i*mScreenHeight,r, (int) ((i+0.5)*mScreenHeight));
            }
        }
    }


    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return super.onInterceptTouchEvent(ev);
    }
    int mLastY;
    int mStart;
    int mEnd;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int y= (int) event.getY();
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN://按下
                mLastY=y;
                mStart=getScrollY();
                break;
            case MotionEvent.ACTION_MOVE://移动
                if (!mScroller.isFinished()){
                    mScroller.abortAnimation();
                }
                int dy=mLastY-y;
                if (getScrollY()<0){
                    dy=0;
                }
                if (getScrollY()>getHeight()-mScreenHeight){
                    dy=0;
                }
                scrollBy(0,dy);
                mLastY=y;
                break;
            case MotionEvent.ACTION_UP://离开
                mEnd=getScrollY();
                int dScrollY=mEnd-mStart;
                if (dScrollY>0){
                    if (dScrollY<mScreenHeight/3){
                        mScroller.startScroll(0,getScrollY(),0,-dScrollY);
                    }else{
                        mScroller.startScroll(0,getScrollY(),0,mScreenHeight-dScrollY);
                    }
                }else{
                    if (-dScrollY<mScreenHeight/3){
                        mScroller.startScroll(0,getScrollY(),0,-dScrollY);
                    }else{
                        mScroller.startScroll(0,getScrollY(),0,-mScreenHeight-dScrollY);
                    }
                }
                break;
        }
        Log.i("test","位置："+mLastY+"----"+mStart+"----"+mEnd);
        postInvalidate();
        return true;
    }


    @Override
    public void computeScroll() {
        super.computeScroll();
        if(mScroller.computeScrollOffset()){
            scrollTo(0,mScroller.getCurrY());
            postInvalidate();
        }
    }

    private void init(){
        mScroller = new Scroller(mContext);
        mScreenHeight = getScreenHeight(mContext);
    }

    /**
     * 获得屏幕高度
     *
     * @param context
     * @return
     */
    public static int getScreenWidth(Context context) {
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.widthPixels;
    }

    /**
     * 获得屏幕宽度
     *
     * @param context
     * @return
     */
    public static int getScreenHeight(Context context) {
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.heightPixels;
    }


}
