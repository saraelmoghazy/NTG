package com.ntg.user.mvpsample.network.remote;

import com.ntg.user.mvpsample.model.Story;
import com.ntg.user.mvpsample.model.StoryTask;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

/**
 * @author Sara Elmoghazy
 */
public interface APIEndPoint {


    @GET("stories")
    Observable<List<Story>> getStories();

    @POST("saveStory")
    Observable<Integer> saveStory(@Body Story story);

    @PUT("saveTasks/{taskId}")
    Observable<Response<Void>> saveTasks(@Path("taskId") int id, @Body List<StoryTask> storyTasks);
}
