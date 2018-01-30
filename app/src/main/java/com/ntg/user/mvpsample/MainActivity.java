package com.ntg.user.mvpsample;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
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


import com.ntg.user.mvpsample.remote.Task;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements TasksContract.View {
    TaskAdapter adapter;
    TasksRepo tasksRepo;
    TasksContract.Presenter presenter;
    RecyclerView recyclerView;
    private FloatingActionButton btn;
    EditText taskName, taskDesc;
    Context context = this;
    List<Task> taskList;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerView);
        btn = findViewById(R.id.fab);
        tasksRepo = new TasksRepo();
        presenter = new TaskPresenter(this, tasksRepo);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new TaskAdapter(new ArrayList<Task>(0));
        recyclerView.setAdapter(adapter);
        presenter.getTask();
        // add btn listener
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                // get prompts.xml view
                LayoutInflater li = LayoutInflater.from(context);
                View promptsView = li.inflate(R.layout.dialog_signin, null);
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
                // set prompts.xml to alertdialog builder
                alertDialogBuilder.setView(promptsView);
                taskName = promptsView.findViewById(R.id.taskname);
                taskDesc = promptsView.findViewById(R.id.disc);
                // set dialog message
                alertDialogBuilder
                        .setCancelable(false)
                        .setPositiveButton("OK",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                    presenter.saveTask(getTaskFromUser());
                                    }
                                })
                        .setNegativeButton("Cancel",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();
                                    }
                                });
                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }
        });
        
        //recycleview
        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(context, recyclerView ,new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        // do whatever
                        Task task = taskList.get(position);
                       
                       /* AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
                        alertDialog.setTitle(task.getTitle());
                        alertDialog.setMessage(task.getBody());
                        alertDialog.show();*/
                      AlertDialog.Builder builder=new AlertDialog.Builder(context);
                      View mview=getLayoutInflater().inflate()
                    }
                
                    @Override public void onLongItemClick(View view, int position) {
                        // do whatever
                        Toast.makeText(getApplicationContext(),"long click",Toast.LENGTH_SHORT).show();
                    
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
        taskList=tasks;
    }

    @Override
    public void showEmptyTasks() {

    }

    @Override
    public void showErrorMessage(String errMsg) {

    }

    @Override
    public void showSaveTaskSuccessMsg() {
        Toast.makeText(getApplicationContext(),"Task is Saved",Toast.LENGTH_SHORT).show();
    }
    
    @Override
    public void showSaveTaskFailedMsg() {
        Toast.makeText(getApplicationContext(),"task failed",Toast.LENGTH_SHORT).show();
    }
    
    @Override
    public void setPresenter(TasksContract.Presenter presenter) {
        this.presenter = presenter;
    }
}