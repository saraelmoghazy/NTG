package com.ntg.user.mvpsample.data.source.remote.network;

import com.ntg.user.mvpsample.data.source.remote.ForApplication;
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
@ForApplication
@Module
class NetworkModule {

    @Provides
    OkHttpClient provideOkHttpClient() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return new OkHttpClient.Builder()
                .connectTimeout(5, TimeUnit.SECONDS)
                .readTimeout(5, TimeUnit.SECONDS)
                .addInterceptor(httpLoggingInterceptor)
                .build();
    }

    @Provides
    Retrofit provideBaseRetrofit(OkHttpClient okHttpClient) {
        String baseURL = "http://mesawer.getsandbox.com/";
        return new Retrofit.Builder()
                .baseUrl(baseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
    }

    @Provides
    RxErrorHandlingCallAdapterFactory provideRxErrorHandlerCallAdapter(Retrofit retrofit) {
        return (RxErrorHandlingCallAdapterFactory) RxErrorHandlingCallAdapterFactory.create(retrofit);
    }


    @Provides
    RetrofitProvider provideRetrofit(RxErrorHandlingCallAdapterFactory rxErrorHandlingCallAdapterFactory, Retrofit retrofit) {
        return new RetrofitProvider(retrofit, rxErrorHandlingCallAdapterFactory);
    }

}
