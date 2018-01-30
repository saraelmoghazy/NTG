package com.ntg.user.mvpsample.tasks;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.ntg.user.mvpsample.R;
import com.ntg.user.mvpsample.data.Task;
import com.ntg.user.mvpsample.data.TaskRepository;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements TasksContract.View {
    TasksContract.Presenter tasksPresenter;
    TaskRepository taskRepository;
    RecyclerView recyclerView;
    TaskAdapter myAdapter;
    LinearLayoutManager llm;
    TextView noTasksTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.rv_tasks);
        llm = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(llm);
        myAdapter = new TaskAdapter(new ArrayList<Task>());
        recyclerView.setAdapter(myAdapter);
        taskRepository = new TaskRepository();
        tasksPresenter = new TasksPresenter(this, taskRepository);
        tasksPresenter.start();
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
    public void showTask(List<Task> tasks) {
        myAdapter.replaceData(tasks);
    }

    @Override
    public void setPresenter(TasksContract.Presenter presenter) {
        this.tasksPresenter = presenter;
    }

    @Override
    public void showNoTasks() {

    }
}
