package com.aliakbar.meditrack.activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.aliakbar.meditrack.R;
import com.aliakbar.meditrack.manager.ObjectFactory;

public class SplashScreenActivity extends AppCompatActivity {

    private static final String TAG = String.valueOf(R.string.app_name);
    // Splash screen timer
    private static int SPLASH_TIME_OUT = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                regIntent();
            }

        },SPLASH_TIME_OUT);
    }

    private void regIntent() {
        Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
        startActivity(intent);
        this.finish();
    }


}
