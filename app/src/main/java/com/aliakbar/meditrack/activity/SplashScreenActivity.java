package com.aliakbar.meditrack.activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;

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
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                if (ObjectFactory.getInstance().getAppPreference(getApplicationContext()).getIsFirstLogIn()) {
                    homeIntent();
                } else {
                    regIntent();


                }
            }

        }, SPLASH_TIME_OUT);
    }

    private void regIntent() {
        Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
        startActivity(intent);
        this.finish();
    }

    private void homeIntent() {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
        this.finish();
    }


}
