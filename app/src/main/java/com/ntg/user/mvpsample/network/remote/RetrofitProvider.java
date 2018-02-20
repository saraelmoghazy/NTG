package com.ntg.user.mvpsample.network.remote;

import retrofit2.Retrofit;

/**
 * @author Sara Elmoghazy
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
