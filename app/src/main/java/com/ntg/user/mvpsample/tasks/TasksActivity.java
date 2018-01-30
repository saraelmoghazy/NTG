package com.ntg.user.mvpsample.tasks;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.ntg.user.mvpsample.R;
import com.ntg.user.mvpsample.Util.ActivityUtils;
import com.ntg.user.mvpsample.Util.Injection;
import com.ntg.user.mvpsample.data.Task;
import com.ntg.user.mvpsample.data.source.TasksRepository;

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
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),
                    tasksFragment, R.id.tasks_fragment);
        }
        tasksRepository = Injection.provideTasksRepository();
        presenter = new TasksPresenter(tasksFragment, tasksRepository);
    }
}
