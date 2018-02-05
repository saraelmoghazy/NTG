package com.ntg.user.mvpsample.data.source.remote.network;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by ilias on 05/02/2018.
 */
@IntDef({
        ErrorType.NETWORK,
        ErrorType.HTTP,
        ErrorType.UNEXPECTED
})

@Retention(RetentionPolicy.RUNTIME)
public @interface ErrorType {

    int NETWORK = 1;
    int HTTP = 2;
    int UNEXPECTED = 3;
}
