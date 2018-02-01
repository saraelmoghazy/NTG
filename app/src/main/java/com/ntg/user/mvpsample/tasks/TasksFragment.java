package com.ntg.user.mvpsample.tasks;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ntg.user.mvpsample.R;
import com.ntg.user.mvpsample.add_task.AddTaskActivity;
import com.ntg.user.mvpsample.data.Task;
import com.ntg.user.mvpsample.task_details.TaskDetailsActivity;

import java.util.ArrayList;
import java.util.List;

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
    TasksAdapter tasksAdapter;
    LinearLayoutManager llm;

    public static TasksFragment newInstance() {
        return new TasksFragment();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        addTaskFab = getActivity().findViewById(R.id.add_task_fab);
        addTaskFab.setOnClickListener((view) -> presenter.navigateToAddTaskUI());

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tasks, container, false);
        ButterKnife.bind(this, view);
        llm = new LinearLayoutManager(getContext());
        rvTasks.setLayoutManager(llm);
        tasksAdapter = new TasksAdapter(getContext(),
                new ArrayList<Task>(0),
                itemListener);
        rvTasks.setAdapter(tasksAdapter);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        presenter.start();
    }

    @Override
    public void setPresenter(TasksContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void showTasks(List<Task> tasks) {
        noTasksTxtView.setVisibility(View.GONE);
        tasksAdapter.replaceData(tasks);
    }

    @Override
    public void showNoTasks() {
        noTasksTxtView.setVisibility(View.VISIBLE);
        noTasksTxtView.setText("No Tasks");
    }

    @Override
    public void showNetworkError() {
        noTasksTxtView.setVisibility(View.VISIBLE);
        noTasksTxtView.setText("Server Error: Check Your Connection");
    }

    @Override
    public void showAddNewTaskUI() {
        Intent intent = new Intent(getContext(), AddTaskActivity.class);
        startActivity(intent);
    }

    @Override
    public void showTaskDetailsUI(Task task) {
        Intent intent = new Intent(getContext(), TaskDetailsActivity.class);
        intent.putExtra("task", task);
        startActivity(intent);
    }

    @Override
    public void changeTaskColorUponProgress(boolean isOver90) {

    }

    TasksAdapter.TaskItemListener itemListener = new TasksAdapter.TaskItemListener() {
        @Override
        public void onTaskClick(Task clickedTask) {
            showTaskDetailsUI(clickedTask);
        }

        @Override
        public void onCompleteTaskClick(Task completedTask) {
            presenter.updateTaskStatus(completedTask);
        }

        @Override
        public void onActivateTaskClick(Task activatedTask) {
            presenter.updateTaskStatus(activatedTask);
        }
    };
}
