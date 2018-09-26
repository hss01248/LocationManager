package com.yayandroid.locationmanager;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import com.yayandroid.locationmanager.transparent.TransparentFragment;

/**
 * Created by huangshuisheng on 2018/9/26.
 */

public class LocationUtil {



    static final String TAG = "LocationUtil";



    public static ILocationLibConfig locationLibConfig;

    public static void setLoadingDialogStyle(ILocationLibConfig iLoadingDialog) {
        LocationUtil.locationLibConfig = iLoadingDialog;
    }

    public static void getLocation(@NonNull final FragmentActivity activity,XLocationCallback callback) {
        TransparentFragment fragment = getTransFragment(activity.getSupportFragmentManager());
        fragment.setCallback(callback);
        fragment.setHostActivity(activity);
        fragment.getLocation();

    }

    public static void getLocation(@NonNull final Fragment fragment,XLocationCallback callback) {
        TransparentFragment fragment2 = getTransFragment(fragment.getChildFragmentManager());
        fragment2.setCallback(callback);
        fragment2.getLocation();
    }

    private static TransparentFragment getTransFragment(FragmentManager fragmentManager) {
        TransparentFragment fragment = findFragment(fragmentManager);
        boolean isNewInstance = fragment == null;
        if (isNewInstance) {
            fragment = new TransparentFragment();
            fragmentManager
                    .beginTransaction()
                    .add(fragment, TAG)
                    .commitAllowingStateLoss();
            fragmentManager.executePendingTransactions();
        }
        return fragment;
    }

    private static TransparentFragment findFragment(FragmentManager fragmentManager) {
        return (TransparentFragment) fragmentManager.findFragmentByTag(TAG);
    }





}
