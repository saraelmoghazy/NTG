package com.ntg.user.mvpsample.task;

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
import com.ntg.user.mvpsample.add_tasks.AddTaskFragment;
import com.ntg.user.mvpsample.data.Task;
import com.ntg.user.mvpsample.taskdetail.TaskDetailFragment;

import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;

/**
 * @author islam fragment that present list of tasks
 */

public class TasksFragment extends Fragment implements ITaskView{

    private TaskPresenter taskPresenter;
    @BindView(R.id.btn_addNewTaskActivity)
    FloatingActionButton addNewTask;
    private TaskAdapter taskAdapter;
    @BindView(R.id.rv_tasks)
    RecyclerView tasksRecyclerView;
    @BindView(R.id.loadinIndicator)
    ProgressBar loadingIndicator;
    PublishSubject<Task> taskSubject = PublishSubject.create();

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
        ButterKnife.bind(this , view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        tasksRecyclerView.setLayoutManager(linearLayoutManager);
        taskPresenter = new TaskPresenter(this);
        addNewTask.setOnClickListener(v -> showAddNewTask());
        taskSubject.subscribe(this::navigateToTaskDetails);

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
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void showTasks(List<Task> tasks) {
        taskAdapter = new TaskAdapter(tasks , taskSubject);
        tasksRecyclerView.setAdapter(taskAdapter);
    }

    @Override
    public void showAddNewTask() {
        AddTaskFragment addTaskFragment = AddTaskFragment.newInstance();
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.container, addTaskFragment)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void showErrorMessage(String errMessage){
        Toast.makeText(getActivity() , errMessage , Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoadingIndicator(boolean show) {
        if (show){
            loadingIndicator.setVisibility(View.VISIBLE);
        }else {
            loadingIndicator.setVisibility(View.GONE);
        }
    }

    @Override
    public void navigateToTaskDetails(Task task) {
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.container, TaskDetailFragment.newInstance(task))
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void setPresenter(Object presenter) {
    }
}
