package com.ntg.user.mvpsample.data.source.remote.network;

import retrofit2.Retrofit;

/**
 * Created by ilias on 08/02/2018.
 */

public class RetrofitProvider {
    private Retrofit retrofit;
    private RxErrorHandlingCallAdapterFactory rxErrorHandlingCallAdapterFactory;

    RetrofitProvider(Retrofit retrofit,
                     RxErrorHandlingCallAdapterFactory rxErrorHandlingCallAdapterFactory) {
        this.retrofit = retrofit;
        this.rxErrorHandlingCallAdapterFactory = rxErrorHandlingCallAdapterFactory;
    }

    public Retrofit getRetrofit() {
        return retrofit.newBuilder().addCallAdapterFactory(rxErrorHandlingCallAdapterFactory).build();
    }


}
