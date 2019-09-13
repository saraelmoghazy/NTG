package com.ntg.user.mvpsample.task_details;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.ntg.user.mvpsample.BaseActivity;
import com.ntg.user.mvpsample.R;
import com.ntg.user.mvpsample.TasksRepo;
import com.ntg.user.mvpsample.Utils;
import com.ntg.user.mvpsample.remote.SubTask;
import com.ntg.user.mvpsample.remote.Task;
import com.ntg.user.mvpsample.tasks.TaskAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ShowActivity extends BaseActivity implements TaskDetailsContract.View {

    @BindView(R.id.tvTaskTitle)
    TextView tvTitle;
    @BindView(R.id.tvTaskDesc)
    TextView tvDesc;
    @BindView(R.id.addSub)
    Button btn;
    @BindView(R.id.recyclerViewSubTask)
    RecyclerView recyclerView;
    EditText subTaskTitle, subTaskProgress;
    Context context = this;
    SubTaskAdapter subTaskAdapter;
    TaskDetailsContract.Presenter presenter;
    TasksRepo tasksRepo;
    Task task;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        ButterKnife.bind(this);
        tasksRepo = new TasksRepo();
        presenter = new TaskDetailsPresenter(this, tasksRepo);
        Intent intent = getIntent();
        task = intent.getParcelableExtra("Task");
        tvTitle.setText(task.getTitle());
        tvDesc.setText(task.getBody());
        dialog(context);
        List<SubTask> subTasks = task.getSubTasks();
        //  List<SubTask> subTaskProgress = ;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        subTaskAdapter = new SubTaskAdapter(context,subTasks);
        recyclerView.setAdapter(subTaskAdapter);
        btn.setOnClickListener((View v) -> {
            {
                // get prompts.xml view
                LayoutInflater li = LayoutInflater.from(context);
                View promptsView = li.inflate(R.layout.dialog_add_sub, null);
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
                alertDialogBuilder.setView(promptsView);
                //ButterKnife.bind(this,promptsView);
                subTaskTitle = promptsView.findViewById(R.id.subTitle);
                subTaskProgress = promptsView.findViewById(R.id.subProgress);
                // set dialog message
                alertDialogBuilder
                        .setCancelable(false)
                        .setPositiveButton("OK", (dialog, which) -> {
                                    String title = subTaskTitle.getText().toString();
                                    String progress = subTaskProgress.getText().toString();
                                    SubTask subTask = new SubTask(title, progress);
                                    presenter.saveSubTask(task.getId(), subTask);

                                }
                        )
                        .setNegativeButton("Cancel", (dialog, which) -> dialog.cancel());
                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }
        });
    }

    @Override
    public void setPresenter(TaskDetailsContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void showNewSubTaskInList(SubTask subTask) {
        subTaskAdapter.addSubTask(subTask);
//        List<Integer> subTaskProgress =new ArrayList<>();
//        subTaskProgress.add(Integer.parseInt(subTask.getProgress()));
//        Utils utils=new Utils();
//        utils.percentage(subTaskProgress);
    }

    @Override
    public void showSavingSubTaskError() {
        Toast.makeText(this, "error", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showSavingSubTaskSuccess() {
        Log.d("tag", "show");
        Toast.makeText(this, "SubTask Saved", Toast.LENGTH_SHORT).show();
        dialog(context);
    }


}
