package com.ntg.user.mvpsample;

import android.content.Context;

import com.ntg.user.mvpsample.data.source.remote.ForApplication;
import com.ntg.user.mvpsample.data.source.remote.NetModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by mohamed yassin on 2/7/2018.
 */
@ForApplication
@Component(modules = {ApplicationModule.class})
public interface ApplicationComponent {

    Context getContext();
}
