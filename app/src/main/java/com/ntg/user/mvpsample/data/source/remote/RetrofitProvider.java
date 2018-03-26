package com.ntg.user.mvpsample.data.source.remote;

import okhttp3.Interceptor;
import retrofit2.Retrofit;

/**
 * Created by mohamed yassin on 2/7/2018.
 */

public class RetrofitProvider {
    RxErrorHandlingCallAdapterFactory callAdapterFactory;
    Retrofit retrofit;

    public RetrofitProvider(RxErrorHandlingCallAdapterFactory callAdapterFactory,
                            Retrofit retrofit) {
        this.callAdapterFactory = callAdapterFactory;
        this.retrofit = retrofit;

    }

    public Retrofit getRetrofit() {
        return retrofit.newBuilder().addCallAdapterFactory(callAdapterFactory)
                .build();
    }
}
