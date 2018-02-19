package com.ntg.user.mvpsample.network.remote;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;
import javax.inject.Singleton;

/**
 * Created by Sara Elmoghazy on 11/02/2018.
 */
@Singleton
@Qualifier
@Retention(RetentionPolicy.RUNTIME)
public @interface ForApplication {
}
