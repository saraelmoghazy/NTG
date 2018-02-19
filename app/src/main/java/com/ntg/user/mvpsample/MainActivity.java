package com.ntg.user.mvpsample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.ntg.user.mvpsample.network.Task;
import com.ntg.user.mvpsample.task_details.TaskDetailsFragment;
import com.ntg.user.mvpsample.tasks.view.TasksFragment;

public class MainActivity extends AppCompatActivity implements TaskItemListener{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TasksFragment tasksFragment = TasksFragment.newInstance();
        getFragmentManager().beginTransaction().add(R.id.container, tasksFragment).commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.refresh:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onTaskClicked(Task task) {
        getFragmentManager().beginTransaction().replace(R.id.container, TaskDetailsFragment.newInstance(task)).commit();
    }
}
