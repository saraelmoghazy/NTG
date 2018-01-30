package com.ntg.user.mvpsample;

import com.ntg.user.mvpsample.data.sourse.TasksRepository;
import com.ntg.user.mvpsample.data.sourse.remote.AddTaskRepo;
import com.ntg.user.mvpsample.data.sourse.remote.RemoteTaskRepo;

/**
 * @author islam
 * Class that inject Tasks Repository
 */

public class Injection {

    public static AddTaskRepo provideAddTasksRepository(){
        return AddTaskRepo.getInstance();
    }
    public static TasksRepository provideTasksRepository(){
        return TasksRepository.getInstance(RemoteTaskRepo.getInstance());
    }
}
