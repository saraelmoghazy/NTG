package com.ntg.user.mvpsample.addedittask;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.ntg.user.mvpsample.ActivityUtils;
import com.ntg.user.mvpsample.Injection;
import com.ntg.user.mvpsample.R;

/**
 * Created by mohamed yassin on 1/28/2018.
 */

public class AddTaskActivity extends AppCompatActivity {

    private AddEditTaskPresenter mAddEditTaskPresenter;

    private ActionBar mActionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addtask_act);
        setupToolbar();
        AddEditTaskFragment addEditTaskFragment = setupAddTaskFragment();
        setPresenter(addEditTaskFragment);
    }

    private void setPresenter(AddEditTaskFragment addEditTaskFragment) {
        mAddEditTaskPresenter = new AddEditTaskPresenter(Injection.provideTasksRepository(this
                .getApplicationContext()), addEditTaskFragment);
    }

    @NonNull
    private AddEditTaskFragment setupAddTaskFragment() {
        AddEditTaskFragment addEditTaskFragment = (AddEditTaskFragment) getSupportFragmentManager()
                .findFragmentById(R.id.contentFrame);

        if (addEditTaskFragment == null) {
            addEditTaskFragment = AddEditTaskFragment.newInstance();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),
                    addEditTaskFragment, R.id.contentFrame);
        }

        return addEditTaskFragment;
    }

    private void setupToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        mActionBar.setTitle("New Task");
        setSupportActionBar(toolbar);
        mActionBar = getSupportActionBar();
        mActionBar.setDisplayHomeAsUpEnabled(true);
        mActionBar.setDisplayShowHomeEnabled(true);
    }
}
