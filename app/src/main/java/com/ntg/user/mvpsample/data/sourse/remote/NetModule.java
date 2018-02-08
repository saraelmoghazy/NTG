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
 * @author islam
 */
@Module
public class NetModule {

    String baseUrl;

    public NetModule(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    @Provides
    @Singleton
    OkHttpClient provideOkHttp(){
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor = httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor).build();
        return okHttpClient;
    }

    @Provides
    @Singleton
    Retrofit provideBaseRetrofit(OkHttpClient okHttpClient){
        Retrofit retrofit = new Retrofit
                .Builder()
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(baseUrl)
                .build();
        return retrofit;
    }

    @Provides
    @Singleton
    RxErrorHandlingCallAdapterFactory provideRxErrorHandlingCallAdapterFactory (Retrofit retrofit){
        return new RxErrorHandlingCallAdapterFactory(retrofit);
    }

    @Provides
    @Singleton
    RetrofitProvider provideRetrofit (RxErrorHandlingCallAdapterFactory callAdapterFactory, Retrofit retrofit){
        return new RetrofitProvider(callAdapterFactory , retrofit);
    }
}
