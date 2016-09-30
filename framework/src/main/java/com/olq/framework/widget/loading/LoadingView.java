package com.olq.framework.widget.loading;

import android.content.Context;

import com.olq.framework.base.BaseApplication;


/**
 * Created by Administrator on 2016/7/11 0011.
 */
public class LoadingView {

    private static LoadingDialog dialog = null;

    public static void show(Context context) {
        try {
            if (context == null)
                return;
            if (dialog != null)
                dialog.dismiss();
            dialog = new LoadingDialog();
            dialog.show();
        } catch (Exception e) {

        }
    }

    public static void show() {
        show(BaseApplication.getInstance().getActivity());
    }

    public static void dismiss() {
        if (dialog != null) {
            dialog.dismiss();
        }
    }
}
