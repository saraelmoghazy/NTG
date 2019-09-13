package com.ntg.user.mvpsample.show_task;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.ntg.user.mvpsample.R;
import com.ntg.user.mvpsample.model.ActivityFragment;
import com.ntg.user.mvpsample.model.taskdatasources.TasksRepository;
import com.ntg.user.mvpsample.model.taskdatasources.TasksRemoteDataSource;

public class ShowTaskActivity extends AppCompatActivity {

    TasksRepository tasksRepository;
    ShowTaskPresenter taskDetailsPresenter;
    ShowTaskFragment taskDetailFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_task_details);
        tasksRepository = TasksRepository.getInstance(TasksRemoteDataSource.getInstance());
        taskDetailFragment = (ShowTaskFragment) getSupportFragmentManager()
                .findFragmentById(R.id.task_details_fragment);
        if (taskDetailFragment == null) {
            taskDetailFragment = ShowTaskFragment.newInstance();
            ActivityFragment.addFragmentToActivity(getSupportFragmentManager()
                    , taskDetailFragment, R.id.task_details_fragment);
        }

        taskDetailsPresenter = new ShowTaskPresenter(taskDetailFragment, tasksRepository);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_tasks, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}
