package com.ntg.user.mvpsample.tasks;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.ntg.user.mvpsample.ActivityUtils;
import com.ntg.user.mvpsample.Injection;
import com.ntg.user.mvpsample.R;
import com.ntg.user.mvpsample.data.source.TasksRepository;


public class TaskActivity extends AppCompatActivity {

    TaskFragment tasksFragment;
    TasksRepository tasksRepository;
    TaskPresenter presenter;




    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tasks_act);
         tasksFragment = (TaskFragment) getSupportFragmentManager().findFragmentById(R.id.tasks_fragment);

        if (tasksFragment == null) {
            tasksFragment = TaskFragment.newInstance();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), tasksFragment, R.id.tasks_fragment);
        }
        tasksRepository = Injection.provideTasksRepository();
        presenter = new TaskPresenter(tasksFragment, tasksRepository);
    }


}
