package com.olq.multimedias.ui.home;

import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.TextView;

import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.olq.framework.utils.SpfUtils;
import com.olq.multimedias.R;
import com.olq.multimedias.SpConfig;
import com.olq.multimedias.base.InitActivity;

import java.util.ArrayList;

import butterknife.Bind;

public class LaunchActivity extends InitActivity {


    @Override
    protected int getLayout() {
        return R.layout.activity_launch;
    }


    @Bind(R.id.tv_launch_number)
    TextView tvLaunchNumber;
    private int number = 3;


    @Override
    protected void getonCreate() {
//        handler.post(runnable);
        startActivity(HomePageActivity.class, null, true);

    }

    Handler handler = new Handler();
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            handler.postDelayed(runnable, 1000);
            tvLaunchNumber.setText(number-- + "秒");
            if (number == 0) {
                boolean falg = (boolean) SpfUtils.get(getApplicationContext(), SpConfig.GUIDANCE, SpConfig.FALG, false);
                if (!falg) {
                    startActivity(GuidanceActivity.class, null, true);
                } else {
                    startActivity(HomePageActivity.class, null, true);
                }
            }
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (handler != null && runnable != null) {
            handler.removeCallbacks(runnable);
        }
    }

}
