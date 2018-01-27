package com.ntg.user.mvpsample.data.source.remote.network;

import android.content.Context;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ilias on 25/01/2018.
 */

public class TasksAPI {
    public static Retrofit getClient(Context context) {
        File cacheFile = new File(context.getCacheDir(), "responses");
        int cacheSize = 10 * 1024 * 1024;
        Cache cache = new Cache(cacheFile, cacheSize);
        CachingInterceptor cachingInterceptor = new CachingInterceptor();
//        ParameterInterceptor parameterInterceptor = new ParameterInterceptor();
//        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
//        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        String baseURL = "http://mesawer.getsandbox.com/";
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(5, TimeUnit.SECONDS)
                .readTimeout(5, TimeUnit.SECONDS)
//                .addInterceptor(httpLoggingInterceptor)
//                .addInterceptor(parameterInterceptor)
                .addNetworkInterceptor(cachingInterceptor)
                .cache(cache)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        return retrofit;
    }
}
