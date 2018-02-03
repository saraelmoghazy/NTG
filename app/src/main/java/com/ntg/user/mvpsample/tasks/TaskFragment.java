package com.ntg.user.mvpsample.tasks;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ntg.user.mvpsample.R;
import com.ntg.user.mvpsample.addtask.AddTaskActivity;
import com.ntg.user.mvpsample.data.Task;

import java.util.ArrayList;
import java.util.List;

import de.keyboardsurfer.android.widget.crouton.Crouton;
import de.keyboardsurfer.android.widget.crouton.Style;


public class TaskFragment extends Fragment  implements TaskContract.View, TaskItemListener {
    private TaskContract.Presenter presenter;
    RecyclerView mToDosList;
    RelativeLayout mTasksView;
    LinearLayout mNoTasks;
    TaskAdapter taskAdapter;
    ProgressBar progressBar;
    LinearLayoutManager llm;
    TextView noTasksTxtView;



    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        FloatingActionButton fab =
                (FloatingActionButton) getActivity().findViewById(R.id.fab_add_task);
        fab.setImageResource(R.drawable.ic_add);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent AddTaskIntent=new Intent(getContext(), AddTaskActivity.class);
                startActivity(AddTaskIntent);

            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tasks_frag, container, false);
        setHasOptionsMenu(true);
        mToDosList =  view.findViewById(R.id.toDos_list);
        mTasksView =  view.findViewById(R.id.tasksLayout);
        mNoTasks = view.findViewById(R.id.noTasksLayout);
        progressBar = view.findViewById(R.id.progressBar);
        noTasksTxtView=view.findViewById(R.id.noTasksMain);
        llm = new LinearLayoutManager(getContext());
        mToDosList.setLayoutManager(llm);
        taskAdapter = new TaskAdapter(getContext(),new ArrayList<Task>(0), this);
        mToDosList.setAdapter(taskAdapter);

        return view;
    }
    public static TaskFragment newInstance() {
        return new TaskFragment();

    }


    @Override
    public void onResume() {
        super.onResume();
        presenter.start();
    }


    @Override
    public void showTasks(List<Task> taskList) {
        mNoTasks.setVisibility(View.GONE);
        progressBar.setVisibility(View.GONE);
        mTasksView.setVisibility(View.VISIBLE);
        taskAdapter.replaceData(taskList);
    }

    @Override
    public void showNoTasks() {
        mNoTasks.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.GONE);
        mTasksView.setVisibility(View.GONE);
    }

    @Override
    public void showMessageError() {
        noTasksTxtView.setVisibility(View.VISIBLE);
        noTasksTxtView.setText("Server Error: Check Your Connection");

    }


    @Override
    public void onTaskClicked(Task task) {
        Crouton.makeText(getActivity(), "Selected Item : " + task.getTitle() +
                "   Description : " + task.getDesc() + "   Complete : " + task.isCompleted() , Style.INFO).show();

    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        Crouton.cancelAllCroutons();
    }


    @Override
    public void setPresenter(TaskPresenter presenter) {
        this.presenter=presenter;
    }
}
