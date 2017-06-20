package com.aliakbar.meditrack.activity;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.aliakbar.meditrack.R;
import com.aliakbar.meditrack.fragment.LoginFragment;
import com.aliakbar.meditrack.manager.ObjectFactory;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initViews();
    }

    private void initViews() {
        checkFirstLogIn();
    }

    private void checkFirstLogIn() {

        if (ObjectFactory.getInstance().getAppPreference(getApplicationContext()).getIsFirstLogIn()){
            homeIntent();
        }else {
            loginIntent();
        }

    }

    private void loginIntent() {
        Fragment fragment = new LoginFragment();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.login_fragment_container, fragment)
                .commit();
    }

    private void homeIntent() {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
        this.finish();
    }
}
