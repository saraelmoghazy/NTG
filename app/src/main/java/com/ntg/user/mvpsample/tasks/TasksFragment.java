package com.ntg.user.mvpsample.tasks;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ntg.user.mvpsample.R;
import com.ntg.user.mvpsample.add_task.AddTaskActivity;
import com.ntg.user.mvpsample.task_details.TaskDetailsActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A placeholder fragment containing a simple view.
 */
public class TasksFragment extends Fragment implements TasksContract.View {

    FloatingActionButton addTaskFab;
    @BindView(R.id.rvTasks)
    RecyclerView rvTasks;
    @BindView(R.id.noTasksTxtView)
    TextView noTasksTxtView;
    private TasksContract.Presenter presenter;

    public TasksFragment() {
    }

    public static TasksFragment newInstance() {
        return new TasksFragment();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        addTaskFab = getActivity().findViewById(R.id.add_task_fab);
        addTaskFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.addTask();
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tasks, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void setPresenter(TasksContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void showTasks() {
        noTasksTxtView.setVisibility(View.GONE);
    }

    @Override
    public void showNoTasks() {
        noTasksTxtView.setVisibility(View.VISIBLE);
    }

    @Override
    public void showAddNewTaskUI() {
        Intent intent = new Intent(getContext(), AddTaskActivity.class);
        startActivity(intent);
    }

    @Override
    public void showTaskDetailsUI() {
        Intent intent = new Intent(getContext(), TaskDetailsActivity.class);
        startActivity(intent);
    }

    @Override
    public void showTaskAsComplete() {

    }

    @Override
    public void showTaskAsActive() {

    }
}
