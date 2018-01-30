package com.ntg.user.mvpsample.data.source.remote;

/*import com.ntg.user.mvpsample.data.model.ArticleResponse;
import com.ntg.user.mvpsample.data.model.SourceResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by mohamed yassin on 1/27/2018.
 */

import com.ntg.user.mvpsample.data.Task;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiService {

 @POST("tasks")
 Call<Task> saveTask(@Body Task task);
}
