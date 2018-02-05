package com.ntg.user.mvpsample.utils;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;

/**
 * Created by ilias on 05/02/2018.
 */

public class App extends Application {
    @SuppressLint("StaticFieldLeak")
    public static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }
}
