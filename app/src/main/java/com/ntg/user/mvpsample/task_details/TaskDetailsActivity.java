package com.ntg.user.mvpsample.task_details;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.ntg.user.mvpsample.R;
import com.ntg.user.mvpsample.Util.ActivityUtils;
import com.ntg.user.mvpsample.Util.Injection;
import com.ntg.user.mvpsample.data.source.TasksRepository;

public class TaskDetailsActivity extends AppCompatActivity {

    TasksRepository tasksRepository;
    TaskDetailsPresenter taskDetailsPresenter;
    TaskDetailFragment taskDetailFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_task_details);
        tasksRepository = Injection.provideTasksRepository();
        taskDetailFragment = (TaskDetailFragment) getSupportFragmentManager()
                .findFragmentById(R.id.task_details_fragment);
        if (taskDetailFragment == null) {
            taskDetailFragment = TaskDetailFragment.newInstance();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager()
                    , taskDetailFragment, R.id.task_details_fragment);
        }

        taskDetailsPresenter = new TaskDetailsPresenter(taskDetailFragment, tasksRepository);
    }
}
