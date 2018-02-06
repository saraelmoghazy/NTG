package com.ntg.user.mvpsample.tasks;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.ntg.user.mvpsample.R;
import com.ntg.user.mvpsample.TasksRepo;
import com.ntg.user.mvpsample.remote.Task;
import com.ntg.user.mvpsample.task_details.ShowActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements TasksContract.View {
    TaskAdapter adapter;
    TasksRepo tasksRepo;
    TasksContract.Presenter presenter;
    Context context = this;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.fab)
    FloatingActionButton btn;
    EditText taskName, taskDesc;
    List<Task> taskList;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        tasksRepo = new TasksRepo();
        presenter = new TaskPresenter(this, tasksRepo);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new TaskAdapter(new ArrayList<Task>(0));
        recyclerView.setAdapter(adapter);
       // presenter.getTask();
        dialog();
        // add btn listener
        btn.setOnClickListener((View v) -> {
            {
                // get prompts.xml view
                LayoutInflater li = LayoutInflater.from(context);
                View promptsView = li.inflate(R.layout.dialog_add_task, null);
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
                alertDialogBuilder.setView(promptsView);
                taskName = promptsView.findViewById(R.id.taskname);
                taskDesc = promptsView.findViewById(R.id.disc);
                alertDialogBuilder
                        .setCancelable(false)
                        .setPositiveButton("OK", (dialog, which) ->
                                presenter.saveTask(getTaskFromUser())
                        )
                        .setNegativeButton("Cancel", (dialog, which) -> dialog.cancel());
                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }
        });
        //recycleview
        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(context, recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Task task = taskList.get(position);
                        Intent intent = new Intent(MainActivity.this, ShowActivity.class);
                        intent.putExtra("Task", task);
                        startActivity(intent);
                    }
                    
                    @Override
                    public void onLongItemClick(View view, int position) {
                        // do whatever
                        Toast.makeText(getApplicationContext(), "long click", Toast.LENGTH_SHORT).show();
                    }
                })
        );
    }
    
    Task getTaskFromUser() {
        String title = taskName.getText().toString();
        String desc = taskDesc.getText().toString();
        return new Task(title, desc);
    }
    
    @Override
    public void showTasks(List<Task> tasks) {
        adapter.replaceData(tasks);
        taskList = tasks;
    }
    
    @Override
    public void showEmptyTasks() {
    }
    
    @Override
    public void showErrorMessage(String errMsg) {
        Toast.makeText(getApplicationContext(), errMsg, Toast.LENGTH_SHORT).show();
    }
    
    @Override
    public void showSaveTaskSuccessMsg() {
        dialog();
        Toast.makeText(getApplicationContext(), "Task is Saved", Toast.LENGTH_SHORT).show();
    }
    
    @Override
    public void showSaveTaskFailedMsg() {
        Toast.makeText(getApplicationContext(), "task failed", Toast.LENGTH_SHORT).show();
    }
    
    @Override
    public void setPresenter(TasksContract.Presenter presenter) {
        this.presenter = presenter;
    }
   public void dialog(){
        ProgressDialog pd = new ProgressDialog(MainActivity.this);
        pd.setMessage("loading");
        pd.show();
        new Thread(new Runnable() {
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                pd.dismiss();
            }
        }).start();
        presenter.getTask();
    }
}