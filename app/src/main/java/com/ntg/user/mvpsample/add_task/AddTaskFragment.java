package com.ntg.user.mvpsample.add_task;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ntg.user.mvpsample.R;
import com.ntg.user.mvpsample.add_task.presenter.AddTaskPresenter;
import com.ntg.user.mvpsample.network.SubTask;
import com.ntg.user.mvpsample.network.Task;
import com.ntg.user.mvpsample.tasks.view.TasksFragment;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AddTaskFragment extends android.app.Fragment implements IAddTaskView {

    @BindView(R.id.tv_title)
    EditText title;
    @BindView(R.id.tv_description)
    EditText description;
    @BindView(R.id.btn_addNewTask)
    Button addNewTask;
    AddTaskPresenter addTaskPresenter;
    TasksFragment tasksFragment;

    public static AddTaskFragment newInstance() {
        AddTaskFragment fragment = new AddTaskFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_add_task, container, false);
        ButterKnife.bind(this, view);
        List<SubTask> subTasks = new ArrayList<>();


        description.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                return false;
            }
        });
        addTaskPresenter = new AddTaskPresenter(this);
        tasksFragment = TasksFragment.newInstance();
        addNewTask.setOnClickListener(v -> {
            if (title.getText().toString().equals("") ||
                    description.getText().toString().equals("")) {
                title.setError(getResources().getString(R.string.fill_data));
            } else {
                addTaskPresenter.saveTask(new Task(UUID.randomUUID().toString()
                        , title.getText().toString()
                        , description.getText().toString(), subTasks));
                getFragmentManager()
                        .beginTransaction().replace(R.id.container, tasksFragment).commit();
            }
        });

        return view;
    }


    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();

    }

    @Override
    public void showAddTaskSuccess(String s) {
        Toast.makeText(getActivity(), s, Toast.LENGTH_SHORT).show();
    }
}
