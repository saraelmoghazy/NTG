package com.ntg.user.mvpsample.task;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.ntg.user.mvpsample.Injection;
import com.ntg.user.mvpsample.R;
import com.ntg.user.mvpsample.add_tasks.AddTaskFragment;
import com.ntg.user.mvpsample.data.Task;

import java.util.ArrayList;
import java.util.List;


public class TasksFragment extends Fragment implements ITaskView {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private TaskPresenter taskPresenter;
    private FloatingActionButton addNewTask;
    private TaskAdapter taskAdapter;
    private RecyclerView tasksRecyclerView;
    private OnFragmentInteractionListener mListener;

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
        tasksRecyclerView = view.findViewById(R.id.rv_tasks);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        tasksRecyclerView.setLayoutManager(linearLayoutManager);
        taskPresenter = new TaskPresenter(Injection.provideTasksRepository(),this);
        addNewTask = view.findViewById(R.id.btn_addNewTaskActivity);
        addNewTask.setOnClickListener(v -> showAddNewTask());

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        taskPresenter.start();

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }


    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    @Override
    public void showTasks(List<Task> tasks) {
        Log.e("frag", tasks.get(0).getTitle());
        taskAdapter = new TaskAdapter(tasks);
        tasksRecyclerView.setAdapter(taskAdapter);
    }

    @Override
    public void showAddNewTask() {
        AddTaskFragment addTaskFragment = AddTaskFragment.newInstance();
        getFragmentManager().beginTransaction().replace(R.id.container, addTaskFragment).commit();
    }

    @Override
    public void setPresenter(Object presenter) {
    }
    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
