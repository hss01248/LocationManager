package com.yayandroid.locationmanager;

import android.location.Location;
import com.yayandroid.locationmanager.constants.FailType;

/**
 * Created by huangshuisheng on 2018/9/26.
 */

public interface XLocationCallback {
    void onLocationChanged(Location location);

     void onLocationFailed(@FailType int failType,String errorMsg) ;

}
