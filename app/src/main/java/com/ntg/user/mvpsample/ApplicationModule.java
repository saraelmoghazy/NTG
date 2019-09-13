package com.ntg.user.mvpsample;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by GM7 on 2/9/2018.
 */
@ForApplication
@Module
public class ApplicationModule {
    Context context;

    public ApplicationModule(Context application) {
        this.context = application;
    }

    @Provides
    Context getContext() {
        return context;
    }
}