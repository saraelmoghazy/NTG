package com.ntg.user.mvpsample;

import android.content.Context;
import android.support.annotation.NonNull;

import com.ntg.user.mvpsample.addedittask.data.source.TasksRepository;
import com.ntg.user.mvpsample.addedittask.data.source.local.TasksLocalDataSource;
import com.ntg.user.mvpsample.tasks.data.PocRepository;
import com.ntg.user.mvpsample.tasks.data.local.LocalPocRepo;
import com.ntg.user.mvpsample.tasks.data.remote.RemotePocRepo;

import static com.google.gson.internal.$Gson$Preconditions.checkNotNull;

/**
 * Created by mohamed yassin on 1/28/2018.
 */

public class Injection {

   public static PocRepository providePocRepo() {
        return PocRepository.getInstance(LocalPocRepo.getInstance(), RemotePocRepo.getInstance());
    }
}
