package com.ntg.user.mvpsample.add_task;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.ntg.user.mvpsample.R;
import com.ntg.user.mvpsample.Util.ActivityUtils;
import com.ntg.user.mvpsample.Util.Injection;
import com.ntg.user.mvpsample.data.source.TasksRepository;
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
        tasksRepository = Injection.provideTasksRepository();
        addTaskFragment = (AddTaskFragment) getSupportFragmentManager()
                .findFragmentById(R.id.add_task_fragment);
        if (addTaskFragment == null) {
            addTaskFragment = AddTaskFragment.newInstance();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),
                    addTaskFragment, R.id.add_task_fragment);
        }
        presenter = new AddTaskPresenter(addTaskFragment, tasksRepository);
    }

}
