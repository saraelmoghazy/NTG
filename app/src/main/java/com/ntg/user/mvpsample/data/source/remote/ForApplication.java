package com.ntg.user.mvpsample.data.source.remote;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;
import javax.inject.Singleton;

/**
 * Created by mohamed yassin on 2/7/2018.
 */
@Singleton
@Qualifier
@Retention(RetentionPolicy.RUNTIME)
public @interface ForApplication {
}
