package com.ntg.user.mvpsample;

import android.content.Context;

import dagger.Component;

/**
 * Created by GM7 on 2018-02-11.
 */
@ForApplication
@Component(modules = {ApplicationModule.class})
public interface ApplicationComponent {

    Context getContext();
}