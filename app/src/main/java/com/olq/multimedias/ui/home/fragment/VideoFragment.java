package com.olq.multimedias.ui.home.fragment;

import android.content.ContentResolver;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.media.ThumbnailUtils;
import android.os.Bundle;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.olq.framework.utils.L;
import com.olq.framework.utils.ToastUtil;
import com.olq.multimedias.R;
import com.olq.multimedias.ui.base.InitFragment;
import com.olq.multimedias.bean.NativeVideo;
import com.olq.multimedias.ui.home.adapter.video.VideoAdapter;
import com.olq.multimedias.ui.video.VideoPlayActivity;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * Created by Administrator on 2016/9/28 0028.
 */
public class VideoFragment extends InitFragment {

    @Bind(R.id.xrecycler)
    XRecyclerView mRecyclerView;
    List<NativeVideo> nativeVideos = new ArrayList<>();

    @Override
    public int getFragmentLayout() {
        return R.layout.fragment_video;
    }

    @Override
    public void getFragmentCreateView() {
        scanVideo();
    }

    /**
     * 扫描本地视频
     */
    private void scanVideo() {
        new Thread() {
            @Override
            public void run() {
                super.run();
                nativeVideos = getVidioData();
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (nativeVideos.size() > 0) {
                            init();
                        } else {
                            ToastUtil.show(getActivity(), "木有扫描到视频!");
                        }
                    }
                });
            }
        }.start();
    }


    private void init() {
        VideoAdapter videoAdapter = new VideoAdapter(getActivity(), nativeVideos);
        mRecyclerView.setAdapter(videoAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        mRecyclerView.setLoadingMoreProgressStyle(ProgressStyle.BallRotate);
//        mRecyclerView.setArrowImageView(R.drawable.iconfont_downgrey);
        videoAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                L.log("position:"+position);
                Bundle bundle = new Bundle();
                bundle.putParcelable("video", (Parcelable) nativeVideos.get(position-1));
                startActivity(VideoPlayActivity.class, bundle);
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                return false;
            }
        });
        mRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
//                refreshTime ++;
//                times = 0;
//                new Handler().postDelayed(new Runnable(){
//                    public void run() {
//
//                        listData.clear();
//                        for(int i = 0; i < 15 ;i++){
//                            listData.add("item" + i + "after " + refreshTime + " times of refresh");
//                        }
//                        mAdapter.notifyDataSetChanged();
//                        mRecyclerView.refreshComplete();
//                    }
//
//                }, 1000);            //refresh data here
            }

            @Override
            public void onLoadMore() {
//                if(times < 2){
//                    new Handler().postDelayed(new Runnable(){
//                        public void run() {
//                            mRecyclerView.loadMoreComplete();
//                            for(int i = 0; i < 15 ;i++){
//                                listData.add("item" + (i + listData.size()) );
//                            }
//                            mRecyclerView.loadMoreComplete();
//                            mAdapter.notifyDataSetChanged();
//                        }
//                    }, 1000);
//                } else {
//                    new Handler().postDelayed(new Runnable() {
//                        public void run() {
//                            for(int i = 0; i < 9 ;i++){
//                                listData.add("item" + (1 + listData.size() ) );
//                            }
//                            mRecyclerView.setNoMore(true);
//                            mAdapter.notifyDataSetChanged();
//                        }
//                    }, 1000);
//                }
//                times ++;
            }
        });
    }

    //得到SD卡中的视频信息
    private List<NativeVideo> getVidioData() {
        List<NativeVideo> videos = new ArrayList<>();
        videos.add(new NativeVideo());
        ContentResolver resolver = getActivity().getContentResolver();
//        if (context != null) {
        Cursor cursor = resolver.query(
                MediaStore.Video.Media.EXTERNAL_CONTENT_URI, null, null,
                null, null);
        if (cursor != null) {
            while (cursor.moveToNext()) {

                int id = cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Video.Media._ID));
                String title = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.TITLE));
                String album = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.ALBUM));
                String artist = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.ARTIST));
                String displayName = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DISPLAY_NAME));
                String mimeType = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.MIME_TYPE));
                long duration = cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DURATION));
                String path = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DATA));
                Bitmap thumbnail = ThumbnailUtils.createVideoThumbnail(path, MediaStore.Images.Thumbnails.MINI_KIND);

                long sizes = cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.SIZE));
//
//                //将时长转换00:00：00制
//                //获取时间格式
//                SimpleDateFormat format = new SimpleDateFormat();
//                //转换时区
//                format.setTimeZone(TimeZone.getTimeZone("GTM+00:00"));
//                //时长已准备完毕
//                String time = format.format(sizes);


                String size = getSize(sizes);
                NativeVideo video = new NativeVideo();
//                video.setId(id);
//                video.setTitle(title);
//                video.setAlbum(album);
//                video.setArtist(artist);
//                video.setDisplayName(displayName);
//                video.setMimeType(mimeType);
//                video.setDuration(duration);
//                video.setThumbnail(thumbnail);
//                video.setPath(path);
                videos.add(video);
            }
            cursor.close();
        }
        return videos;

    }

    private static String getSize(float length) {
        float kb = 1024;
        float mb = 1024 * kb;
        float gb = 1024 * mb;
        if (length < kb) {
            return String.format("%d B", (int) length);
        } else if (length < mb) {
            return String.format("%.2f KB", length / kb);
        } else if (length < gb) {
            return String.format("% .2f MB", length / mb);
        } else {
            return String.format("%.2f GB", length / gb);
        }
    }

}
