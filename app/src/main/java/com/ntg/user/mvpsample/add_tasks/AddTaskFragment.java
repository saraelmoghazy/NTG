package com.ntg.user.mvpsample.add_tasks;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import com.ntg.user.mvpsample.Injection;
import com.ntg.user.mvpsample.R;
import com.ntg.user.mvpsample.data.Task;
import java.util.UUID;
import butterknife.BindView;
import butterknife.ButterKnife;

public class AddTaskFragment extends Fragment implements IAddTaskView {

    @BindView(R.id.tv_title)
    EditText title;
    @BindView(R.id.tv_description)
    EditText description;
    @BindView(R.id.btn_addNewTask)
    Button addNewTask;
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
        ButterKnife.bind(this , view);
        addTaskPresenter = new AddTaskPresenter(this,Injection.provideTasksRepository());

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        addNewTask.setOnClickListener(view -> addTaskPresenter.saveTask(new Task(UUID.randomUUID().toString()
                , title.getText().toString()
                ,description.getText().toString(),"false")));
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
