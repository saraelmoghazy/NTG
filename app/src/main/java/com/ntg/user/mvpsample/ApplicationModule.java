package com.ntg.user.mvpsample;

import android.app.Application;
import android.content.Context;

import com.ntg.user.mvpsample.data.source.remote.ForApplication;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by mohamed yassin on 2/7/2018.
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
