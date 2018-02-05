package com.ntg.user.mvpsample.utils;

import android.content.Context;

import com.ntg.user.mvpsample.data.source.TasksRepository;
import com.ntg.user.mvpsample.data.source.remote.TasksRemoteDataSource;

/**
 * Created by ilias on 29/01/2018.
 */

public class Injection {

    public static TasksRepository provideTasksRepository(){
        return TasksRepository.getInstance(TasksRemoteDataSource.getInstance());
    }
}
