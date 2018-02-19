package com.ntg.user.mvpsample.tasks.view;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.ntg.user.mvpsample.R;
import com.ntg.user.mvpsample.TaskItemListener;
import com.ntg.user.mvpsample.add_task.AddTaskFragment;
import com.ntg.user.mvpsample.base.BaseFragment;
import com.ntg.user.mvpsample.base.BaseView;
import com.ntg.user.mvpsample.network.Task;
import com.ntg.user.mvpsample.tasks.presenter.TaskPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author islam fragment that present list of tasks
 */

public class TasksFragment extends BaseFragment implements ITaskView, TaskItemListener {


    @BindView(R.id.btn_addNewTaskActivity)
    FloatingActionButton addNewTask;
    @BindView(R.id.rv_tasks)
    RecyclerView tasksRecyclerView;
    private TaskAdapter taskAdapter;
    private TaskPresenter taskPresenter;
    private TaskItemListener taskItemListener;

    public static TasksFragment newInstance() {
        TasksFragment fragment = new TasksFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tasks, container, false);
        ButterKnife.bind(this, view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        tasksRecyclerView.setLayoutManager(linearLayoutManager);
        taskPresenter = new TaskPresenter(this);
        addNewTask.setOnClickListener(v -> showAddNewTask());

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        taskPresenter.start();
    }

    @Override
    public void onAttach(Context activity) {
        super.onAttach(activity);
        if (activity instanceof TaskItemListener) {
            taskItemListener = (TaskItemListener) activity;
        } else {
            throw new ClassCastException(activity.toString() + "must implement");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void showTasks(List<Task> tasks) {
        taskAdapter = new TaskAdapter(tasks, this);
        tasksRecyclerView.setAdapter(taskAdapter);
    }

    @Override
    public void showAddNewTask() {
        AddTaskFragment addTaskFragment = AddTaskFragment.newInstance();
        getFragmentManager().beginTransaction().replace(R.id.container, addTaskFragment)
                .addToBackStack(null).commit();
    }

    @Override
    public void onTaskClicked(Task task) {
        taskItemListener.onTaskClicked(task);
    }
}
