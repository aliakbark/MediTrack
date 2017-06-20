package com.aliakbar.meditrack.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.aliakbar.meditrack.R;
import com.aliakbar.meditrack.manager.ObjectFactory;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }

    private void initViews() {
        checkFirstLogIn();
    }

    private void checkFirstLogIn() {

        if (ObjectFactory.getInstance().getAppPreference(getApplicationContext()).getIsFirstLogIn()){

        }else {

        }

    }
}
