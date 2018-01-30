package com.ntg.user.mvpsample;

import android.content.Context;
import android.support.annotation.NonNull;

import com.ntg.user.mvpsample.data.source.TasksRepository;
import com.ntg.user.mvpsample.data.source.local.TasksLocalDataSource;
import com.ntg.user.mvpsample.poc.data.PocRepository;
import com.ntg.user.mvpsample.poc.data.local.LocalPocRepo;
import com.ntg.user.mvpsample.poc.data.remote.RemotePocRepo;

import static com.google.gson.internal.$Gson$Preconditions.checkNotNull;

/**
 * Created by mohamed yassin on 1/28/2018.
 */

public class Injection {
    public static TasksRepository provideTasksRepository(@NonNull Context context) {
        checkNotNull(context);
        return TasksRepository.getInstance(FakeTasksRemoteDataSource.getInstance(),
                TasksLocalDataSource.getInstance(context));
    }


   public static PocRepository providePocRepo() {
        return PocRepository.getInstance(LocalPocRepo.getInstance(), RemotePocRepo.getInstance());
    }
}
