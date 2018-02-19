package com.ntg.user.mvpsample.network.remote;

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
@ForApplication
public class NetModule {

    String mBaseUrl;

    public NetModule(String mBaseUrl) {
        this.mBaseUrl = mBaseUrl;
    }

    @Provides
    public OkHttpClient provideOkHttp() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor = httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor).build();

        return okHttpClient;
    }

    @Provides
    public Retrofit provideBaseRetrofit(OkHttpClient okHttpClient) {
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(mBaseUrl)
                .client(okHttpClient)
                .build();
        return retrofit;
    }

    @Provides
    public RxErrorHandlingCallAdapterFactory provideRxErrorHandlerCallAdapter(Retrofit retrofit) {
        return new RxErrorHandlingCallAdapterFactory(retrofit);
    }

    @Provides
    public RetrofitProvider provideRetrofit(RxErrorHandlingCallAdapterFactory callAdapterFactory, Retrofit retrofit) {
        return new RetrofitProvider(callAdapterFactory, retrofit);
    }
}
