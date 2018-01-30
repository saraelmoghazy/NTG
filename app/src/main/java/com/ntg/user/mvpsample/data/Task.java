package com.ntg.user.mvpsample.data;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.UUID;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.POST;

/**
 * Created by devsaad on 1/28/2018.
 */

public class Task implements Serializable {


    @SerializedName("title")
    private String tittle;
    @SerializedName("description")
    private String describtion;

    public String getTittle() {
        return tittle;
    }

    public String getDescribtion() {
        return describtion;
    }


    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    public void setDescribtion(String describtion) {
        this.describtion = describtion;
    }
}