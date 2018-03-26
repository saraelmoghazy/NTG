package com.ntg.user.mvpsample;

import android.app.Application;
import android.content.Context;

/**
 * Created by mohamed yassin on 2/7/2018.
 */

public class NTGApplication extends Application {

    private static ApplicationComponent applicationComponent;

    public static ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        createApplicationComponent(this);
    }

    public static void createApplicationComponent(Context context) {
        if (getApplicationComponent() == null)
            applicationComponent = DaggerApplicationComponent.builder()
                    .applicationModule(new ApplicationModule(context)).build();
    }
}
