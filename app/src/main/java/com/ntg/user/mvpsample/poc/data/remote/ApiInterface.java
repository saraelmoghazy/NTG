package com.ntg.user.mvpsample.poc.data.remote;

import com.ntg.user.mvpsample.poc.data.Poc;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by mohamed yassin on 1/29/2018.
 */

public interface ApiInterface {
    @GET("tasks")
     Call<List<Poc>> getPocs();
}
