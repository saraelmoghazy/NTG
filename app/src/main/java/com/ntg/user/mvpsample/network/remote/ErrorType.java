package com.ntg.user.mvpsample.network.remote;

/**
 * @author Sara Elmoghazy
 */

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@IntDef({ErrorType.NETWORK, ErrorType.HTTP, ErrorType.UNEXPECTED})
@Retention(RetentionPolicy.RUNTIME)
public @interface ErrorType {
    int NETWORK = 1;
    int HTTP = 2;
    int UNEXPECTED = 3;
}
