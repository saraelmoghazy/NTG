package com.ntg.user.mvpsample.tasks.data.remote;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by mohamed yassin on 1/29/2018.
 */

public class ApiClient {
    private static final String BASE_URL = "http://yassin.getsandbox.com/";
    private static Retrofit retrofit = null;

    public static Retrofit getClient() {
        if (retrofit == null)
            retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create()).build();
        return retrofit;
    }
}
