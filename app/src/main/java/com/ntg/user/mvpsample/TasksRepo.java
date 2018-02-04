package com.ntg.user.mvpsample;


import com.ntg.user.mvpsample.local.TaskLocalRepo;
import com.ntg.user.mvpsample.remote.Task;
import com.ntg.user.mvpsample.remote.TaskRemoteRepo;
/**
 * Created by GM7 on 1/29/2018.
 */

public class TasksRepo implements TasksDataSource {
    TaskRemoteRepo remoteRepo;
    TaskLocalRepo localRepo;


    public TasksRepo() {
        localRepo = TaskLocalRepo.newInstance();
        remoteRepo = TaskRemoteRepo.newInstance();
    }
    
  
   
    
    @Override
    public void getPosts(GetPostsCallBack getPostsCallBack) {
        remoteRepo.getPosts(getPostsCallBack);
    }
    
    @Override
    public void saveTasks(Task task, SavePostsCallBack savePostsCallBack) {
        remoteRepo.saveTasks(task,savePostsCallBack);
    }
    
    @Override
    public void updateTasks(Task task, UpdatePostsCallBack updatePostsCallBack) {
        remoteRepo.updateTasks(task,updatePostsCallBack);
    }
    
   
}
