package com.ntg.user.mvpsample.data.sourse.remote;

import com.ntg.user.mvpsample.data.RxErrorHandlingCallAdapterFactory;

import retrofit2.Retrofit;

/**
 * Created by Sara Elmoghazy on 07/02/2018.
 */

public class RetrofitProvider {
    RxErrorHandlingCallAdapterFactory callAdapterFactory;
    Retrofit retrofit;

    public RetrofitProvider(RxErrorHandlingCallAdapterFactory callAdapterFactory, Retrofit retrofit) {
        this.callAdapterFactory = callAdapterFactory;
        this.retrofit = retrofit;
    }

    public Retrofit getRetrofit() {
        return retrofit.newBuilder().addCallAdapterFactory(callAdapterFactory).build();
    }
}
