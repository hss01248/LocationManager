package com.yayandroid.locationmanager.sample;

import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import android.widget.Toast;
import com.yayandroid.locationmanager.LocationUtil;
import com.yayandroid.locationmanager.XLocationCallback;
import com.yayandroid.locationmanager.sample.activity.SampleActivity;
import com.yayandroid.locationmanager.sample.fragment.SampleFragmentActivity;
import com.yayandroid.locationmanager.sample.service.SampleServiceActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void inActivityClick(View view) {
        startActivity(new Intent(this, SampleActivity.class));
    }

    public void inFragmentClick(View view) {
        startActivity(new Intent(this, SampleFragmentActivity.class));
    }

    public void inServiceClick(View view) {
        startActivity(new Intent(this, SampleServiceActivity.class));
    }

    public void locationutil(View view) {
        LocationUtil.getLocation(this, new XLocationCallback() {
            @Override
            public void onLocationChanged(Location location) {
                Log.w("dd","onLocationChanged- location:lat:"+location.getLatitude()+"\nlon:"+location.getLongitude());
                Toast.makeText(getApplicationContext(),"onLocationChanged- location:lat:"+location.getLatitude()+"\nlon:"
                        +location.getLongitude(),Toast.LENGTH_LONG).show();
            }

            @Override
            public void onLocationFailed(int failType,String errorMsg) {
                Log.e("dd","onLocationFailed---:"+failType);
                Toast.makeText(getApplicationContext(),"onLocationFailed---:"+failType+"\n"+errorMsg,Toast.LENGTH_LONG).show();
            }
        });
    }
}
