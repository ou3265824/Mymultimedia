package com.olq.multimedias.ui.belleread;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.google.gson.reflect.TypeToken;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.olq.framework.http.GlideLoader;
import com.olq.framework.http.okgo.OkgoLoader;
import com.olq.framework.http.okgo.callback.OkGsonCallBack;
import com.olq.framework.http.okgo.callback.OkHttpCallBack;
import com.olq.framework.utils.L;
import com.olq.framework.utils.ScreenUtils;
import com.olq.framework.utils.ToolFor9Ge;
import com.olq.multimedias.R;
import com.olq.multimedias.bean.Belle;
import com.olq.multimedias.bean.BelleBase;
import com.olq.multimedias.http.okgo.OkUrlConfig;
import com.olq.multimedias.ui.base.InitFragment;
import com.olq.multimedias.ui.belleread.adapter.NewestAdapter;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Response;



public class NewestFragment extends InitFragment {

    @Bind(R.id.xrecycler_view)
    XRecyclerView xrecyclerView;
    List<Belle> belles;
    private NewestAdapter adapter;
    private int classify;

    @Override
    public int getFragmentLayout() {
        return R.layout.fragment_newest;
    }

    @Override
    public void getFragmentCreateView() {
        L.log("网址："+OkUrlConfig.getNEWS());
        init(classify);
        getXrecycleView();
        xrecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                L.log("ID:"+classify);
                classify=0;
                init(classify);

            }

            @Override
            public void onLoadMore() {
                classify++;
                L.log("ID:"+classify);
                if (classify>7){
                    classify=7;
                    xrecyclerView.loadMoreComplete();
                    return;
                }
                init(classify);
            }
        });
    }

    private void getXrecycleView(){
        if (belles==null){
            belles=new ArrayList<>();
        }
        L.log("list:"+belles.toString());
        xrecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));

        xrecyclerView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        xrecyclerView.setLoadingMoreProgressStyle(ProgressStyle.BallRotate);
        xrecyclerView.setArrowImageView(R.drawable.iconfont_downgrey);
        if (adapter==null){
            adapter = new NewestAdapter(getActivity(), R.layout.item_pictures_hot,belles);
            xrecyclerView.setAdapter(adapter);
        }
        xrecyclerView.setRefreshing(true);
//        xrecyclerView.setLoadingMoreEnabled(true);
//        xrecyclerView.setPullRefreshEnabled(true);
        adapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                onToast(position+"");
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                return false;
           }
        });

    }

    public void init(final int classify){
        OkgoLoader.getInstace().getSendByGet(OkUrlConfig.getNEWS(classify), new OkGsonCallBack<BelleBase<List<Belle>>>(new TypeToken<BelleBase<List<Belle>>>(){}.getType()) {
            @Override
            public void onSuccess(BelleBase<List<Belle>> BelleBase, Call call, Response response) {
                if (classify==0){
                    belles.clear();
                    xrecyclerView.refreshComplete();
                }else{
                    xrecyclerView.loadMoreComplete();
                }
                L.log("onSuccess:"+BelleBase.toString());
                belles.addAll(BelleBase.getTngou());
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onError(Call call, Response response, Exception e) {
                L.log("onError:"+e.toString());
            }
        });
    }


}
