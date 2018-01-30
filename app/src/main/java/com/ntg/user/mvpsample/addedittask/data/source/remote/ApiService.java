package com.ntg.user.mvpsample.addedittask.data.source.remote;

/*import com.ntg.user.mvpsample.addedittask.data.model.ArticleResponse;
import com.ntg.user.mvpsample.addedittask.data.model.SourceResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by mohamed yassin on 1/27/2018.
 */

import com.ntg.user.mvpsample.Task;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiService {

 @POST("saveTask")
 Call<Task> saveTask(@Body Task task);
}
