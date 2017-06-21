package com.aliakbar.meditrack.activity;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;

import com.aliakbar.meditrack.R;
import com.aliakbar.meditrack.manager.ObjectFactory;
import com.aliakbar.meditrack.model.User;
import com.aliakbar.meditrack.utils.BaseActivity;
import com.aliakbar.meditrack.utils.Constants;
import com.aliakbar.meditrack.utils.Utils;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends BaseActivity implements View.OnClickListener {

    private TextInputEditText name;
    private TextInputEditText age;
    private AppCompatButton btn_ok;

    FirebaseDatabase database;
    DatabaseReference mUserRef;

    String deviceID = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        initViews();
        viewClickListeners();
    }

    private void viewClickListeners() {
        btn_ok.setOnClickListener(this);
    }

    private void initViews() {
        name = (TextInputEditText) findViewById(R.id.name);
        age = (TextInputEditText) findViewById(R.id.age);
        btn_ok = (AppCompatButton) findViewById(R.id.btn_ok);

        database = FirebaseDatabase.getInstance();
        mUserRef = database.getReference(Constants.USERS);

        deviceID = Utils.getDeviceId(getApplicationContext());


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_ok:
                signIn();
                break;
            default:
                break;

        }
    }

    private void signIn() {

        final String inputName = name.getText().toString();
        final String inputAge = age.getText().toString().trim();
        if (TextUtils.isEmpty(deviceID)) {
            deviceID = Utils.getDeviceId(getApplicationContext());
        }

        ObjectFactory.getInstance().getAppPreference(getApplicationContext()).setIsFirstLogIn(true);
        saveUser(inputName, inputAge, deviceID);

        homeIntent();

    }

    private void saveUser(String name, String age, String uId) {

        User user = new User(name, age, uId);
        mUserRef.child(deviceID).setValue(user);

    }

    private void homeIntent() {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
        this.finish();
    }

}
