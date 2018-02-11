package com.ntg.user.mvpsample.data.source.remote;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;
import javax.inject.Singleton;

/**
 * Created by ilias on 08/02/2018.
 */
@Singleton
@Retention(RetentionPolicy.RUNTIME)
public @interface ForApplication {
}
