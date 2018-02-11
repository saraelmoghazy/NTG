package com.ntg.user.mvpsample;

import android.app.Application;
import android.content.Context;

/**
 * Created by GM7 on 2018-02-11.
 */

public class NTGApplication extends Application {
    private static ApplicationComponent applicationComponent;
    private static Context context;
    public static ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }

    public static ApplicationComponent createApplicationComponent(Context context) {
        if (applicationComponent == null) {
            applicationComponent = DaggerApplicationComponent
                    .builder().applicationModule(new ApplicationModule(context)).build();

        }

        return applicationComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        createApplicationComponent(this);
        NTGApplication.context = getApplicationContext();
    }

    public static Context getAppContext() {
        return NTGApplication.context;
    }
}
