package com.aliakbar.meditrack.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.aliakbar.meditrack.R;

/**
 * Created by ALIAKBAR on 20-06-2017.
 */

public class AppPreference {

    private SharedPreferences mSharedPreferences;
    public static  final  String IS_FIRST_LOG_IN = "IS_FIRST_LOG_IN";

    public AppPreference(Context context) {
        super();
        if (context!=null){
            mSharedPreferences =context.getSharedPreferences(context.getString(R.string.app_name),Context.MODE_PRIVATE);
        }
    }

    public void updateContext(Context context) {
        if (context != null) {
            mSharedPreferences = context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE);
        }
    }

    public void setIsFirstLogIn(boolean value) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putBoolean(IS_FIRST_LOG_IN, value);
        editor.apply();
    }

    public boolean getIsFirstLogIn() {
        return mSharedPreferences.getBoolean(IS_FIRST_LOG_IN, false);
    }
}
