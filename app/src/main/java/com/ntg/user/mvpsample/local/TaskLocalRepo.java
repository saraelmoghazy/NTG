package com.ntg.user.mvpsample.local;


import com.ntg.user.mvpsample.TasksDataSource;
import com.ntg.user.mvpsample.remote.Task;
/**
 * Created by GM7 on 1/29/2018.
 */

public class TaskLocalRepo implements TasksDataSource {
  // Context context=this;
    public static TaskLocalRepo newInstance()
    {
        return new  TaskLocalRepo();
    }

    @Override
    public void getPosts(GetPostsCallBack getPostsCallBack) {
   //DatabaseHandler db=new DatabaseHandler(context);

    }
  
  @Override
  public void saveTasks(Task task, SavePostsCallBack savePostsCallBack) {
  }
}

