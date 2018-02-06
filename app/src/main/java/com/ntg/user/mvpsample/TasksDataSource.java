package com.ntg.user.mvpsample;



import com.ntg.user.mvpsample.remote.SubTask;
import com.ntg.user.mvpsample.remote.Task;

import java.util.List;

/**
 * Created by GM7 on 1/29/2018.
 */

public interface TasksDataSource {
    
    interface GetPostsCallBack {
        void onPostsLoaded(List<Task> taskList);
        void onPostsFailed(String errorMessage);
    
    }
    interface SavePostsCallBack{
        void onPostsSaved(Task task);
        void onPostsFailed(String errorMessage);
    }
    interface SaveSubTaskCallBack{
        void onSubTaskSaved();
        void onSubTaskFailed(String errorMessage);
    }
    interface UpdatePostsCallBack{
        void onPostsUpdated(Task task);
        void onPostsFailed(String errorMessage);
    }
    void getPosts(GetPostsCallBack getPostsCallBack);
    void saveTasks(Task task, SavePostsCallBack savePostsCallBack);
    void updateTasks(Task task, UpdatePostsCallBack updatePostsCallBack);
    void saveSubTask(String taskId, SubTask subTask, SaveSubTaskCallBack saveSubTaskCallBack);
}
