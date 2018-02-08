package com.ntg.user.mvpsample.add_tasks;

import com.ntg.user.mvpsample.data.sourse.remote.RetrofitProvider;
import javax.inject.Singleton;
import dagger.Module;
import dagger.Provides;

/**
 * Created by islam on 2/8/2018.
 */
@Module
public class AddTaskModule {

    @Provides
    @Singleton
    AddTaskRepo provideAddTaskRepo(RetrofitProvider retrofitProvider){
        return AddTaskRepo.getInstance(retrofitProvider);
    }
}
