package com.ntg.user.mvpsample.data.source.remote.network;

import com.ntg.user.mvpsample.utils.App;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * TasksAPI contains method to get http client
 */

public class TasksAPI {
    /**
     * getClient used to form retrofit client
     *
     * @return Retrofit instance to use it for web service requests
     */
    public static Retrofit getClient() {
        File cacheFile = new File(App.context.getCacheDir(), "responses");
        int cacheSize = 10 * 1024 * 1024;
        Cache cache = new Cache(cacheFile, cacheSize);
        CachingInterceptor cachingInterceptor = new CachingInterceptor();
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        String baseURL = "http://mesawer.getsandbox.com/";
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(5, TimeUnit.SECONDS)
                .readTimeout(5, TimeUnit.SECONDS)
                .addInterceptor(httpLoggingInterceptor)
                .addNetworkInterceptor(cachingInterceptor)
                .cache(cache)
                .build();

        return new Retrofit.Builder()
                .baseUrl(baseURL)
                .addConverterFactory(GsonConverterFactory.create())
//                .addCallAdapterFactory(new ErrorHandlingRxCallAdapterFactory())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client)
                .build();
    }
}
