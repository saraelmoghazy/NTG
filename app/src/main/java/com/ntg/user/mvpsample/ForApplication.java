package com.ntg.user.mvpsample;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;
import javax.inject.Singleton;

/**
 * Created by GM7 on 2018-02-11.
 */
@Singleton
@Qualifier
@Retention(RetentionPolicy.RUNTIME)
public @interface ForApplication {
}
