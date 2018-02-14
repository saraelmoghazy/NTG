package com.ntg.user.mvpsample.data.source.remote.network;

import android.support.annotation.NonNull;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.Response;

public class CachingInterceptor implements Interceptor {

    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {

        Response originalResponse = chain.proceed(chain.request());
        CacheControl.Builder cacheControlBuilder = new CacheControl.Builder();
        CacheControl cacheControl = cacheControlBuilder
                .maxAge(5, TimeUnit.MINUTES)
                .minFresh(5, TimeUnit.SECONDS)
//                .maxStale(5, TimeUnit.DAYS)
                .build();
//        if (NetworkUtils.isNetworkAvailable(context)) {
//            cacheControl =
//                    cacheControlBuilder.minFresh(5, TimeUnit.SECONDS).build();
//        } else {
//            cacheControl = cacheControlBuilder.maxStale(5, TimeUnit.DAYS).build();
//        }
        return originalResponse.newBuilder()
                .header("Cache-Control", cacheControl.toString())
                .build();
    }
}
