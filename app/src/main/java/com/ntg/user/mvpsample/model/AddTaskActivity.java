package com.ntg.user.mvpsample.model;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.ntg.user.mvpsample.PresenterLayer.AddTaskPresenter;
import com.ntg.user.mvpsample.R;
import com.ntg.user.mvpsample.model.taskdatasources.TasksRepository;
import com.ntg.user.mvpsample.model.taskdatasources.TasksRemoteDataSource;

/** AddTaskActivity is used to add AddTaskFragment
 */
public class AddTaskActivity extends AppCompatActivity {

    AddTaskFragment addTaskFragment;
    TasksRepository tasksRepository;
    AddTaskPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_add_task);
        tasksRepository =TasksRepository.getInstance(TasksRemoteDataSource.getInstance());
        addTaskFragment = (AddTaskFragment) getSupportFragmentManager()
                .findFragmentById(R.id.add_task_fragment);
        if (addTaskFragment == null) {
            addTaskFragment = AddTaskFragment.newInstance();
            ActivityFragment.addFragmentToActivity(getSupportFragmentManager(),
                    addTaskFragment, R.id.add_task_fragment);
        }
        presenter = new AddTaskPresenter(addTaskFragment, tasksRepository);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add_task, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}
