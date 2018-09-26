package com.yayandroid.locationmanager;

import android.app.Dialog;
import android.content.Context;

/**
 * Created by huangshuisheng on 2018/9/26.
 */

public interface ILocationLibConfig {

    Dialog buildLoadingDialog(String text,Context context);

    void updateText(String text,Dialog dialog);

    String msgWhenrequestGps();

    /**
     *
     * @return
     */
    String msgWhenrequestPermission();

    /**
     * Getting location...
     * @return
     */
    String msgLoading();

}
