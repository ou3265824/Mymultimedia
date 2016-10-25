package com.olq.multimedias.ui.belleread;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.reflect.TypeToken;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.olq.framework.http.okgo.OkgoLoader;
import com.olq.framework.http.okgo.callback.OkGsonCallBack;
import com.olq.framework.http.okgo.callback.OkHttpCallBack;
import com.olq.framework.utils.L;
import com.olq.multimedias.R;
import com.olq.multimedias.bean.Belle;
import com.olq.multimedias.bean.BelleBase;
import com.olq.multimedias.http.okgo.OkUrlConfig;
import com.olq.multimedias.ui.base.InitFragment;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Response;

import static android.R.attr.type;


public class NewestFragment extends InitFragment {

    @Bind(R.id.xrecycler_view)
    XRecyclerView xrecyclerView;

    @Override
    public int getFragmentLayout() {
        return R.layout.fragment_newest;
    }

    @Override
    public void getFragmentCreateView() {
//        xrecyclerView.setAdapter();
//        View.VISIBLE
        L.log("网址："+OkUrlConfig.getNEWS());
        OkgoLoader.getInstace().getSendByGet(OkUrlConfig.getNEWS(), new OkGsonCallBack<BelleBase<List<Belle>>>(new TypeToken<BelleBase<List<Belle>>>(){}.getType()) {
            @Override
            public void onSuccess(BelleBase<List<Belle>> BelleBase, Call call, Response response) {
                L.log("onSuccess:"+BelleBase.toString());
                List<Belle> belles=BelleBase.getTngou();
//                xrecyclerView
            }

            @Override
            public void onError(Call call, Response response, Exception e) {
                L.log("onError:"+e.toString());
            }
        });

//        new OkHttpCallBack<BelleBase<List<Belle>>>() {
//            @Override
//            public void onSuccess(BelleBase<List<Belle>> belle, Call call, Response response) {
//                L.log("onSuccess:"+belle.toString());
//            }
//
//            @Override
//            public void onError(Call call, Response response, Exception e) {
//                L.log("onError:"+e.toString());
//            }
//        });
    }


}
