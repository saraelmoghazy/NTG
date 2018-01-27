package com.ntg.user.mvpsample;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.ntg.user.mvpsample.add_tasks.AddTaskFragment;
import com.ntg.user.mvpsample.task.TasksFragment;

public class MainActivity extends AppCompatActivity implements TasksFragment.OnFragmentInteractionListener
        , AddTaskFragment.OnFragmentInteractionListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TasksFragment tasksFragment = TasksFragment.newInstance();

        getSupportFragmentManager().beginTransaction().add(R.id.container , tasksFragment).commit();
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
    public void onFragmentInteraction(Uri uri) {

    }
}
