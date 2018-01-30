package com.ntg.user.mvpsample.data.sourse.remote;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by islam on 1/27/2018.
 */

public class ApiClient {

    private static final String BASE_URL = "http://twilight-grass-3888.getsandbox.com/";
    private static Retrofit retrofit = null;

    public static Retrofit getClient() {
        if (retrofit == null)
            retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        return retrofit;
    }
}
