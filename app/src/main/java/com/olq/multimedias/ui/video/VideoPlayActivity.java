package com.olq.multimedias.ui.video;

import android.content.Context;
import android.content.res.Configuration;
import android.os.PowerManager;
import android.view.View;

import com.dou361.ijkplayer.bean.VideoijkBean;
import com.dou361.ijkplayer.widget.PlayerView;
import com.olq.framework.utils.L;
import com.olq.multimedias.R;
import com.olq.multimedias.ui.base.InitActivity;
import com.olq.multimedias.bean.NativeVideo;
import com.olq.multimedias.utlis.MediaUtils;

import java.util.List;

public class VideoPlayActivity extends InitActivity {

    private PlayerView player;
    private Context mContext;
    private List<VideoijkBean> list;
    private PowerManager.WakeLock wakeLock;
    View rootView;

    @Override
    protected int getLayout() {
        return R.layout.activity_video_play;
    }

    @Override
    protected void getonCreate() {
        this.mContext = this;
        NativeVideo nativeVideo=getIntent().getExtras().getParcelable("video");
        L.log("视频："+nativeVideo.toString());
        getPlay(nativeVideo);

    }

    private void getPlay(final NativeVideo nativeVideo) {
        /**常亮*/
        PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
        wakeLock = pm.newWakeLock(PowerManager.SCREEN_BRIGHT_WAKE_LOCK, "liveTAG");
        wakeLock.acquire();
//        String url = nativeVideo.getPath();
//        player = new PlayerView(this)
//                .setTitle(nativeVideo.getDisplayName())
//                .setScaleType(PlayStateParams.fitparent)
//                .hideMenu(true)
//                .forbidTouch(false)
//                .setForbidHideControlPanl(true)
//                .showThumbnail(new OnShowThumbnailListener() {
//                    @Override
//                    public void onShowThumbnail(ImageView ivThumbnail) {
////                        Glide.with(mContext)
////                                .load("http://pic2.nipic.com/20090413/406638_125424003_2.jpg")
////                                .placeholder(R.color.cl_default)
////                                .error(R.color.cl_error)
////                                .into(ivThumbnail);
//                        ivThumbnail.setImageBitmap(nativeVideo.getThumbnail());
//                    }
//                })
//                .setPlaySource(url)
//                .startPlay();
    }




    @Override
    protected void onPause() {
        super.onPause();
        if (player != null) {
            player.onPause();
        }
        /**demo的内容，恢复系统其它媒体的状态*/
        MediaUtils.muteAudioFocus(mContext, true);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (player != null) {
            player.onResume();
        }
        /**demo的内容，暂停系统其它媒体的状态*/
        MediaUtils.muteAudioFocus(mContext, false);
        /**demo的内容，激活设备常亮状态*/
        if (wakeLock != null) {
            wakeLock.acquire();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (player != null) {
            player.onDestroy();
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (player != null) {
            player.onConfigurationChanged(newConfig);
        }
    }

    @Override
    public void onBackPressed() {
        if (player != null && player.onBackPressed()) {
            return;
        }
        super.onBackPressed();
        /**demo的内容，恢复设备亮度状态*/
        if (wakeLock != null) {
            wakeLock.release();
        }
    }

}
