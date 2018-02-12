package com.ntg.user.mvpsample.data.source.remote;

import android.content.Context;


import com.ntg.user.mvpsample.tasks.Util;

import javax.inject.Singleton;

import dagger.Module;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import dagger.Provides;

/**
 * Created by mohamed yassin on 2/7/2018.
 */

@Module
public class NetModule {
    public static String BASE_URL = "http://yassin.getsandbox.com/";

    public NetModule(String BaseUrl) {
        this.BASE_URL = BaseUrl;
    }
    @Provides
    @Singleton
    OkHttpClient provideOkHttp(Context context) {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(logging)
                .cache(new Cache(context.getCacheDir(), 10 * 1024 * 1024))
                .addInterceptor(Util.provideOfflineCacheInterceptor(context))
                .addNetworkInterceptor(Util.provideCacheIntercptor())
                .build();
        return client;
    }

    @Provides
    @Singleton
    Retrofit provideBaseRetrofit(OkHttpClient okHttpClient) {
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .client(okHttpClient)
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
    RetrofitProvider provideRetrofit(RxErrorHandlingCallAdapterFactory callAdapterFactory
            , Retrofit retrofit) {
        return new RetrofitProvider(callAdapterFactory, retrofit);
    }

}
