package com.ntg.user.mvpsample.data.source.remote;



import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by mohamed yassin on 1/27/2018.
 */

public class ApiClient {
    public static final String BASE_URL = "http://yassin.getsandbox.com/";;
    private static Retrofit sRetrofit = null;

    public static Retrofit getClient() {
        if (sRetrofit == null) {


                    sRetrofit = new Retrofit.Builder()
                            .baseUrl(BASE_URL)
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();
                }


        return sRetrofit;
    }
}
