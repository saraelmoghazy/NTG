package com.ntg.user.mvpsample;

import android.app.Application;

import com.ntg.user.mvpsample.remote.RxErrorHandlingCallAdapterFactory;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by GM7 on 2/8/2018.
 */

@Module
class ApiModule {

    String mBaseUrl;

    ApiModule(String mBaseUrl) {
        this.mBaseUrl = mBaseUrl;
    }

    @Provides
    @Singleton
    Cache provideHttpCache(Application application) {
        int cacheSize = 10 * 1024 * 1024;
        Cache cache = new Cache(application.getCacheDir(), cacheSize);
        return cache;
    }
    @Provides
    @Singleton
    OkHttpClient provideOkHttp(Cache cache) {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor = httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(5, TimeUnit.SECONDS)
                .readTimeout(5, TimeUnit.SECONDS)
                .addInterceptor(httpLoggingInterceptor)
                .cache(cache)
                .build();
        return okHttpClient;
    }

    @Provides
    @Singleton
    Retrofit provideBaseRetrofit(OkHttpClient okHttpClient) {
        Retrofit retrofit = new Retrofit
                .Builder()
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(mBaseUrl)
                .build();

        return retrofit;
    }


    @Provides
    @Singleton
    RxErrorHandlingCallAdapterFactory provideRxErrorHandlerCallAdapter(Retrofit retrofit) {
        return new RxErrorHandlingCallAdapterFactory(retrofit);
    }

    @Provides
    @Singleton
    RetrofitProvider provideRetrofit(RxErrorHandlingCallAdapterFactory callAdapterFactory, Retrofit retrofit) {
        return new RetrofitProvider(callAdapterFactory, retrofit);
    }

}