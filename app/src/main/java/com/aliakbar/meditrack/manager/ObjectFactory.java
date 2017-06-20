package com.aliakbar.meditrack.manager;

import android.content.Context;

import com.aliakbar.meditrack.utils.AppPreference;

/**
 * Created by ALIAKBAR on 20-06-2017.
 */

public class ObjectFactory {

    private static ObjectFactory instance = null;
    private static AppPreference appPreference = null;

    protected ObjectFactory() {
    }



    public static ObjectFactory getInstance(){
        if (instance == null){
            instance = new ObjectFactory();
        }
        return instance;
    }

    public AppPreference getAppPreference(Context context){
        if (appPreference == null){
            appPreference = new AppPreference(context);
        }else {
            appPreference.updateContext(context);
        }

        return appPreference;
    }

    public static boolean isDestroyed() {
        return instance == null;
    }
}
