package com.ntg.user.mvpsample.data.source.remote.network;

import com.ntg.user.mvpsample.utils.App;

import java.io.File;
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
 * Created by ilias on 07/02/2018.
 */

@Module
public class NetworkModule {



    @Provides
    public OkHttpClient provideOkHttpClient(){
//        File cacheFile = new File(App.context.getCacheDir(), "responses");
//        int cacheSize = 10 * 1024 * 1024;
//        Cache cache = new Cache(cacheFile, cacheSize);
//        CachingInterceptor cachingInterceptor = new CachingInterceptor();
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return new OkHttpClient.Builder()
                .connectTimeout(5, TimeUnit.SECONDS)
                .readTimeout(5, TimeUnit.SECONDS)
                .addInterceptor(httpLoggingInterceptor)
//                .addNetworkInterceptor(cachingInterceptor)
//                .cache(cache)
                .build();
    }

    @Provides
    @Singleton
    public Retrofit provideRetrofit(OkHttpClient okHttpClient){
        String baseURL = "http://mesawer.getsandbox.com/";
        return new Retrofit.Builder()
                .baseUrl(baseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxErrorHandlingCallAdapterFactory.create())
                .client(okHttpClient)
                .build();
   }
}
