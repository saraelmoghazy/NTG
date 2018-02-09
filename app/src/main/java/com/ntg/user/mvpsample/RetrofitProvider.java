package com.ntg.user.mvpsample;

import com.ntg.user.mvpsample.remote.RxErrorHandlingCallAdapterFactory;

import retrofit2.Retrofit;

/**
 * Created by GM7 on 2/8/2018.
 */

public class RetrofitProvider {
    RxErrorHandlingCallAdapterFactory callAdapterFactory;
    Retrofit retrofit;

    public RetrofitProvider(RxErrorHandlingCallAdapterFactory callAdapterFactory, Retrofit retrofit) {
        this.callAdapterFactory = callAdapterFactory;
        this.retrofit = retrofit;
    }

    public Retrofit getRetrofit() {
        return retrofit
                .newBuilder()
                .addCallAdapterFactory(callAdapterFactory).build();
    }

}
