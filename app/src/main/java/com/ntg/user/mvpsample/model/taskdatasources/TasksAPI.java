package com.ntg.user.mvpsample.model.taskdatasources;

import android.content.Context;

import com.ntg.user.mvpsample.RxErrorHandlingCallAdapterFactory;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * TasksAPI contains method to get http client
 */

public class TasksAPI {
    /** getClient used to form retrofit client
     * @return Retrofit instance to use it for web service requests
     */
    public static Retrofit getClient() {
        String baseURL = "http://ntg-webservices.getsandbox.com/";

        return new Retrofit.Builder()
                .baseUrl(baseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(new RxErrorHandlingCallAdapterFactory())
                .build();
    }
}
