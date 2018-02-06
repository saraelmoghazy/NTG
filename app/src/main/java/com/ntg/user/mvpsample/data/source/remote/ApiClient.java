package com.ntg.user.mvpsample.data.source.remote;



import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by mohamed yassin on 1/27/2018.
 */

public class ApiClient {
    public static final String BASE_URL = "http://yassin.getsandbox.com/";;
    private static Retrofit sRetrofit = null;
    static Context mContext;
    public ApiClient(Context mContext){
        this.mContext=mContext;
    }


    public static Retrofit getClient() {
        if (sRetrofit == null) {

            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient client = new OkHttpClient.Builder()
                    .addInterceptor(logging)
                    .addInterceptor(provideOfflineCacheInterceptor())
                    .addNetworkInterceptor(provideCacheIntercptor())
                    .cache(new Cache(mContext.getCacheDir(),10 * 1024 * 1024))
                    .build();


                    sRetrofit = new Retrofit.Builder()
                            .baseUrl(BASE_URL)
                            .addConverterFactory(GsonConverterFactory.create())
                            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//                            .addCallAdapterFactory(new RxErrorHandlingCallAdapterFactory())
                            .client(client)
                            .build();
                }


        return sRetrofit;
    }
    public static Interceptor provideCacheIntercptor(){
        return new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Response  response=chain.proceed(chain.request());
                CacheControl cacheControl=new CacheControl.Builder()
                        .maxAge(2, TimeUnit.MINUTES)
                        .build();
                return response.newBuilder()
                        .header("Cache-Control",cacheControl.toString())
                        .build();
            }
        };
    }
    public static Interceptor provideOfflineCacheInterceptor(){
        return new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                if(!isOnline()){
                    CacheControl cacheControl=new CacheControl.Builder()
                            .maxStale(7, TimeUnit.DAYS)
                            .build();
                    request=request.newBuilder()
                            .cacheControl(cacheControl)
                            .build();
                }


                return chain.proceed(request);

            }
        };
    }
    protected static boolean isOnline(){
        ConnectivityManager cm=(ConnectivityManager)mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo=cm.getActiveNetworkInfo();
        if(networkInfo !=null && networkInfo.isConnectedOrConnecting()){
            return true;
        }else{
            return false;
        }
    }



}
