package com.ntg.user.mvpsample;

import android.content.Context;

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
class NetModule {

    public static final String TAG = NetModule.class.getSimpleName();

    String mBaseUrl;

    NetModule(String mBaseUrl) {
        this.mBaseUrl = mBaseUrl;
    }

    @Provides
    Cache provideHttpCache(Context context) {
        int cacheSize = 10 * 1024 * 1024;
        Cache cache = new Cache(context.getCacheDir(), cacheSize);
        return cache;
    }

    @Provides
    OkHttpClient provideOkHttp(Cache cache) {
        CachingInterceptor cachingInterceptor = new CachingInterceptor();
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor = httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(5, TimeUnit.SECONDS)
                .readTimeout(5, TimeUnit.SECONDS)
                .addInterceptor(httpLoggingInterceptor)
                .addNetworkInterceptor(cachingInterceptor)
                .cache(cache)
                .build();

        return okHttpClient;
    }

    @Provides
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
    RxErrorHandlingCallAdapterFactory provideRxErrorHandlerCallAdapter(Retrofit retrofit) {

        return new RxErrorHandlingCallAdapterFactory(retrofit);
    }

    @Provides
    @Singleton
    RetrofitProvider provideRetrofit(RxErrorHandlingCallAdapterFactory callAdapterFactory, Retrofit retrofit) {

        return new RetrofitProvider(callAdapterFactory, retrofit);
    }

}