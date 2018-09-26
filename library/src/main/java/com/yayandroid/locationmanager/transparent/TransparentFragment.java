package com.yayandroid.locationmanager.transparent;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Window;
import com.yayandroid.locationmanager.LocationUtil;
import com.yayandroid.locationmanager.XLocationCallback;
import com.yayandroid.locationmanager.base.LocationBaseFragment;
import com.yayandroid.locationmanager.configuration.Configurations;
import com.yayandroid.locationmanager.configuration.LocationConfiguration;
import com.yayandroid.locationmanager.constants.FailType;
import com.yayandroid.locationmanager.constants.ProcessType;
import com.yayandroid.locationmanager.helper.ErrorTypeToMsg;


public class TransparentFragment extends LocationBaseFragment  {

    private Dialog progressDialog;
    private XLocationCallback callback;
    private boolean oneshot = true;//采集一次,还是连续采集



    public void setCallback(XLocationCallback callback) {
        this.callback = callback;
    }




    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public void getLocation() {
        super.getLocation();
        displayProgress();
    }

    @Override
    public LocationConfiguration getLocationConfiguration() {
        return Configurations.defaultConfiguration(getHostActivity().getString(LocationUtil.locationLibConfig.msgWhenrequestPermission()),
                getHostActivity().getString(LocationUtil.locationLibConfig.msgWhenrequestGps()));
    }

    @Override
    public void onLocationChanged(Location location) {
        dismissProgress();
        if(callback != null){
            callback.onLocationChanged(location);
            if(oneshot){
                callback = null;
            }
        }
    }

    @Override
    public void onLocationFailed(@FailType int failType) {
        displayProgress();
        if(callback != null){
            callback.onLocationFailed(failType, ErrorTypeToMsg.getMsg(failType));
            if(oneshot){
                callback = null;
            }
        }
    }

    @Override
    public void onProcessTypeChanged(@ProcessType int processType) {
       /* switch (processType) {
            case ProcessType.GETTING_LOCATION_FROM_GOOGLE_PLAY_SERVICES: {
               updateProgress("Getting Location from Google Play Services...");
                break;
            }
            case ProcessType.GETTING_LOCATION_FROM_GPS_PROVIDER: {
               updateProgress("Getting Location from GPS...");
                break;
            }
            case ProcessType.GETTING_LOCATION_FROM_NETWORK_PROVIDER: {
                updateProgress("Getting Location from Network...");
                break;
            }
            case ProcessType.ASKING_PERMISSIONS:
            case ProcessType.GETTING_LOCATION_FROM_CUSTOM_PROVIDER:
                // Ignored
                break;
        }*/
    }

    @Override
    public void onResume() {
        super.onResume();

        if (getLocationManager().isWaitingForLocation()
              && !getLocationManager().isAnyDialogShowing()) {
            displayProgress();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        dismissProgress();
    }

    private void displayProgress() {
        if (progressDialog == null) {
            String msg = getHostActivity().getString(LocationUtil.locationLibConfig.msgLoading());
            if(LocationUtil.locationLibConfig == null){
                ProgressDialog dialog = new ProgressDialog(getHostActivity());
                dialog.getWindow().addFlags(Window.FEATURE_NO_TITLE);
                dialog.setMessage(msg);
                progressDialog  = dialog;
            }else {
                progressDialog = LocationUtil.locationLibConfig.buildLoadingDialog(msg,getHostActivity());
            }
        }

        if (!progressDialog.isShowing()) {
            progressDialog.show();
        }
    }

    public void updateProgress(String text) {
        if (progressDialog != null && progressDialog.isShowing()) {
            if(progressDialog instanceof  ProgressDialog){
                ((ProgressDialog)progressDialog).setMessage(text);
            }else {
                if(LocationUtil.locationLibConfig != null){
                    LocationUtil.locationLibConfig.updateText(text,progressDialog);
                }
            }

        }
    }


    public void dismissProgress() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        dismissProgress();

    }
}
