package com.ntg.user.mvpsample.tasks;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.ntg.user.mvpsample.PresenterLayer.TasksPresenter;
import com.ntg.user.mvpsample.R;
import com.ntg.user.mvpsample.model.ActivityFragment;
import com.ntg.user.mvpsample.model.taskdatasources.TasksRepository;
import com.ntg.user.mvpsample.model.taskdatasources.TasksRemoteDataSource;

public class TasksActivity extends AppCompatActivity {
    TasksFragment tasksFragment;
    TasksRepository tasksRepository;
    TasksPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_tasks);
        tasksFragment = (TasksFragment) getSupportFragmentManager()
                .findFragmentById(R.id.tasks_fragment);
        if (tasksFragment == null){
            tasksFragment = TasksFragment.newInstance();
            ActivityFragment.addFragmentToActivity(getSupportFragmentManager(),
                    tasksFragment, R.id.tasks_fragment);
        }
        tasksRepository = TasksRepository.getInstance(TasksRemoteDataSource.getInstance());
        presenter = new TasksPresenter(tasksFragment, tasksRepository);
    }
}
