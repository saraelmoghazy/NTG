package com.ntg.user.mvpsample.data;

import com.google.gson.Gson;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by devsaad on 1/28/2018.
 */

public class ServiceGenerator {

   public static Retrofit getClient(){
       String bsaeURL = "http://ntg-webservices.getsandbox.com/";
       return new Retrofit.Builder()
               .baseUrl(bsaeURL)
               .addConverterFactory(GsonConverterFactory.create())
               .build();
   }
}
