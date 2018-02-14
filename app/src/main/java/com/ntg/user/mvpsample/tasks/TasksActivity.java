package com.ntg.user.mvpsample.tasks;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.ntg.user.mvpsample.R;
import com.ntg.user.mvpsample.utils.ActivityUtils;

public class TasksActivity extends AppCompatActivity {
    TasksFragment tasksFragment;
    TasksPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_tasks);
        tasksFragment = (TasksFragment) getSupportFragmentManager()
                .findFragmentById(R.id.tasks_fragment);
        if (tasksFragment == null){
            tasksFragment = TasksFragment.newInstance();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),
                    tasksFragment, R.id.tasks_fragment);
        }
        presenter = new TasksPresenter(tasksFragment);
    }
}
