package com.yayandroid.locationmanager.helper;

import com.yayandroid.locationmanager.constants.FailType;

/**
 * Created by huangshuisheng on 2018/9/26.
 */

public class ErrorTypeToMsg {
    
    public static String getMsg(@FailType int failType){
        switch (failType) {
            case FailType.TIMEOUT: {
                return "Couldn't get location, and timeout!";
            }
            case FailType.PERMISSION_DENIED: {
                return "Couldn't get location, because user didn't give permission!";
            }
            case FailType.NETWORK_NOT_AVAILABLE: {
                return "Couldn't get location, because network is not accessible!";
            }
            case FailType.GOOGLE_PLAY_SERVICES_NOT_AVAILABLE: {
                return "Couldn't get location, because Google Play Services not available!";
            }
            case FailType.GOOGLE_PLAY_SERVICES_CONNECTION_FAIL: {
                return "Couldn't get location, because Google Play Services connection failed!";
            }
            case FailType.GOOGLE_PLAY_SERVICES_SETTINGS_DIALOG: {
                return "Couldn't display settingsApi dialog!";
            }
            case FailType.GOOGLE_PLAY_SERVICES_SETTINGS_DENIED: {
                return "Couldn't get location, because user didn't activate providers via settingsApi!";
            }
            case FailType.VIEW_DETACHED: {
                return "Couldn't get location, because in the process view was detached!";
            }
            case FailType.VIEW_NOT_REQUIRED_TYPE: {
                return "Couldn't get location, "
                        + "because view wasn't sufficient enough to fulfill given configuration!";
            }
            case FailType.UNKNOWN: {
                return "Ops! Something went wrong!";
            }
            default:
                return "";
        }
    }

}
