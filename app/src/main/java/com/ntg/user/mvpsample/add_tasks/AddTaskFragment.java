package com.ntg.user.mvpsample.add_tasks;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.ntg.user.mvpsample.R;



public class AddTaskFragment extends Fragment implements IAddTaskView {

    private EditText title , description;
    private Button addNewTask;
    AddTaskPresenter addTaskPresenter;
    public AddTaskFragment() {

    }
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

        title = view.findViewById(R.id.tv_title);
        description = view.findViewById(R.id.tv_description);
        addNewTask = view.findViewById(R.id.btn_addNewTask);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        addNewTask.setOnClickListener(view -> addTaskPresenter.saveTask(title.getText().toString()
                , description.getText().toString()));
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
    public void setPresenter(Object presenter) {

    }

    @Override
    public void setTitle(String title) {

    }

    @Override
    public void setDescription(String description) {

    }
}
