package com.ntg.user.mvpsample.data.sourse.remote;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;

/**
 * Created by islam on 2/8/2018.
 */
@ForApplication
@Qualifier
@Retention(RetentionPolicy.RUNTIME)
public @interface ForApplication {
}
