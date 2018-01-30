package com.ntg.user.mvpsample;


import com.ntg.user.mvpsample.remote.Task;

import java.util.List;

/**
 * Created by GM7 on 1/29/2018.
 */

public class TaskPresenter implements TasksContract.Presenter{
    TasksContract.View view;
    TasksDataSource tasksDataSource;

    public TaskPresenter(TasksContract.View view, TasksDataSource tasksDataSource) {
        this.view = view;
        this.tasksDataSource = tasksDataSource;
    }

    @Override
    public void getTask() {
        tasksDataSource.getPosts(new TasksDataSource.GetPostsCallBack() {
            @Override
            public void onPostsLoaded(List<Task> taskList) {
                view.showTasks(taskList);
            }

            @Override
            public void onPostsFailed(String errorMessage) {

            }
        });
    }
    
    @Override
    public void saveTask(Task task) {
        tasksDataSource.saveTasks(task, new TasksDataSource.SavePostsCallBack() {
            @Override
            public void onPostsSaved(Task task) {
            view.showSaveTaskSuccessMsg();
            }
    
            @Override
            public void onPostsFailed(String errorMessage) {
                view.showSaveTaskFailedMsg();
            }
        });
        tasksDataSource.getPosts(new TasksDataSource.GetPostsCallBack() {
            @Override
            public void onPostsLoaded(List<Task> taskList) {
                view.showTasks(taskList);
            }
        
            @Override
            public void onPostsFailed(String errorMessage) {
            
            }
        });
    }
}
