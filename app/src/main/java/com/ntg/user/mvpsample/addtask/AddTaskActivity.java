package com.ntg.user.mvpsample.addtask;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.ntg.user.mvpsample.ActivityUtils;
import com.ntg.user.mvpsample.Injection;
import com.ntg.user.mvpsample.R;
import com.ntg.user.mvpsample.data.source.TasksRepository;



public class AddTaskActivity extends AppCompatActivity {
    AddTaskFragment addTaskFragment;
    TasksRepository tasksRepository;
    AddTaskPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.addtask_act);
        tasksRepository = Injection.provideTasksRepository();
        addTaskFragment = (AddTaskFragment) getSupportFragmentManager()
                .findFragmentById(R.id.addtask_fragment);
        if (addTaskFragment == null) {
            addTaskFragment = AddTaskFragment.newInstance();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),
                    addTaskFragment, R.id.addtask_fragment);
        }
        presenter = new AddTaskPresenter(addTaskFragment, tasksRepository);
    }
}
