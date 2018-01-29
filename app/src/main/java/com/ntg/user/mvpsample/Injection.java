package com.ntg.user.mvpsample;

import com.ntg.user.mvpsample.data.sourse.TasksRepository;
import com.ntg.user.mvpsample.data.sourse.remote.RemoteTaskRepo;

/**
 * Created by islam on 1/28/2018.
 */

public class Injection {

    public static TasksRepository provideTasksRepository(){
        return TasksRepository.getInstance(RemoteTaskRepo.getInstance());
    }
}
