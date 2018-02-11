package com.ntg.user.mvpsample.add_edit_task;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.ntg.user.mvpsample.R;
import com.ntg.user.mvpsample.utils.ActivityUtils;

/** AddEditTaskActivity is used to add AddEditTaskFragment
 */
public class AddEditTaskActivity extends AppCompatActivity {

    AddEditTaskFragment addEditTaskFragment;
    AddEditTaskPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_add_task);
        addEditTaskFragment = (AddEditTaskFragment) getSupportFragmentManager()
                .findFragmentById(R.id.add_task_fragment);
        if (addEditTaskFragment == null) {
            addEditTaskFragment = AddEditTaskFragment.newInstance();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),
                    addEditTaskFragment, R.id.add_task_fragment);
        }
        presenter = new AddEditTaskPresenter(addEditTaskFragment);
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
