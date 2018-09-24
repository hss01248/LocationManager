package com.yayandroid.locationmanager.providers.locationprovider;

/**
 * Created by hss on 2018/9/24.
 */

public class PassiveLocationProvider extends LocationProvider {
    @Override
    public boolean isDialogShowing() {
        return false;
    }

    @Override
    public void get() {

    }

    @Override
    public void cancel() {

    }
}
