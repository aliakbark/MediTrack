package com.aliakbar.meditrack.utils;

import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by ALIAKBAR on 20-06-2017.
 */

public abstract class BaseActivity extends AppCompatActivity {

    private BroadcastReceiver broadcastReceiver = null;
    private IntentFilter intentFilter = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    public void registerReceivers() {
        if (broadcastReceiver != null && intentFilter != null) {
            LocalBroadcastManager.getInstance(getApplicationContext()).registerReceiver(broadcastReceiver, intentFilter);
        }
    }

    public void unregisterReceivers() {
        if (broadcastReceiver != null && intentFilter != null) {
            LocalBroadcastManager.getInstance(getApplicationContext()).unregisterReceiver(broadcastReceiver);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceivers();
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceivers();
    }

    public void setBroadcastReceiver(BroadcastReceiver broadcastReceiver, IntentFilter intentFilter) {
        this.broadcastReceiver = broadcastReceiver;
        this.intentFilter = intentFilter;
    }
}
