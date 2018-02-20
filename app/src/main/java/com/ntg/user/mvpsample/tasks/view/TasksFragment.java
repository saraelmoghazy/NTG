package com.ntg.user.mvpsample.tasks.view;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ntg.user.mvpsample.R;
import com.ntg.user.mvpsample.add_task.AddTaskFragment;
import com.ntg.user.mvpsample.base.BaseFragment;
import com.ntg.user.mvpsample.network.Task;
import com.ntg.user.mvpsample.task_details.TaskDetailsFragment;
import com.ntg.user.mvpsample.tasks.presenter.TaskPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;

/**
 * @author Sara Elmoghazy
 */
public class TasksFragment extends BaseFragment implements TaskViewContract {

    @BindView(R.id.btn_addNewTaskActivity)
    FloatingActionButton addNewTask;
    @BindView(R.id.rv_tasks)
    RecyclerView tasksRecyclerView;
    private TaskAdapter taskAdapter;
    private TaskPresenter taskPresenter;
    private Subject<Task> onTaskClicked = PublishSubject.create();


    public static TasksFragment newInstance() {

        return new TasksFragment();
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
    public void showTasks(List<Task> tasks) {
        onTaskClicked.subscribe(task -> taskPresenter.onTaskClicked(task));
        taskAdapter = new TaskAdapter(tasks, onTaskClicked);
        tasksRecyclerView.setAdapter(taskAdapter);
    }


    @Override
    public void showAddNewTask() {
        getFragmentManager().beginTransaction()
                .addToBackStack(null)
                .replace(R.id.container, AddTaskFragment.newInstance()).commit();
    }

    @Override
    public void navigateToTaskDetails(Task task) {
        getFragmentManager().beginTransaction()
                .addToBackStack(null)
                .replace(R.id.container, TaskDetailsFragment.newInstance(task)).commit();
    }
}
