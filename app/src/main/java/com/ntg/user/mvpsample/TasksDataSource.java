package com.ntg.user.mvpsample;



import com.ntg.user.mvpsample.remote.Task;

import java.util.List;

/**
 * Created by GM7 on 1/29/2018.
 */

public interface TasksDataSource {
    interface GetPostsCallBack
    {
        void onPostsLoaded(List<Task> taskList);
        void onPostsFailed(String errorMessage);
        
    }
    interface SavePostsCallBack{
        void onPostsSaved(Task task);
        void onPostsFailed(String errorMessage);
    }

    void getPosts(GetPostsCallBack getPostsCallBack);
    void saveTasks(Task task, SavePostsCallBack savePostsCallBack);
}
