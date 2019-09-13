package com.ntg.user.mvpsample;

import android.content.Context;

import java.io.IOException;

import io.reactivex.annotations.NonNull;
import okhttp3.Interceptor;
import okhttp3.Response;

/**
 * Created by GM7 on 2018-02-11.
 */

public class CachingInterceptor extends NTGApplication implements Interceptor {
    Context context = getAppContext();

    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {

        Response originalResponse = chain.proceed(chain.request());
        //  CacheControl.Builder cacheControlBuilder = new CacheControl.Builder();

        if (Utils.isNetworkAvailable(context)) {
            int maxAge = 60; // read from cache for 1 minute
            return originalResponse.newBuilder()
                    .header("Cache-Control", "public, max-age=" + maxAge)
                    .build();
        } else {
            int maxStale = 60;//* 60 * 24 * 28; // tolerate 4-weeks stale
            return originalResponse.newBuilder()
                    .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                    .build();
        }
    }
}