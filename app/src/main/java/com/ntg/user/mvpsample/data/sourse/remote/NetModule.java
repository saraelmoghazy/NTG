package com.ntg.user.mvpsample.data.sourse.remote;

import com.ntg.user.mvpsample.data.RxErrorHandlingCallAdapterFactory;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Sara Elmoghazy on 07/02/2018.
 */
@Module
public class NetModule {

    String mBaseUrl;

    public NetModule(String mBaseUrl) {
        this.mBaseUrl = mBaseUrl;
    }

    @Provides
    @Singleton
    OkHttpClient provideOkHttp() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor = httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor).build();

        return okHttpClient;
    }

    @Provides
    @Singleton
    Retrofit provideBaseRetrofit(OkHttpClient okHttpClient) {
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(mBaseUrl)
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
    RetrofitProvider provideRetrofit(RxErrorHandlingCallAdapterFactory callAdapterFactory, Retrofit retrofit) {
        return new RetrofitProvider(callAdapterFactory, retrofit);
    }
}
