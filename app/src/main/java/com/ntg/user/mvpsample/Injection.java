package com.ntg.user.mvpsample;

import com.ntg.user.mvpsample.data.source.TasksRepository;
import com.ntg.user.mvpsample.data.source.remote.TaskRemoteDataSource;

/**
 * Created by mohamed yassin on 1/28/2018.
 */

public class Injection {
    public static TasksRepository provideTasksRepository(){
        return TasksRepository.getInstance(TaskRemoteDataSource.getInstance());
    }
}
