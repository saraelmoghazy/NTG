package com.ntg.user.mvpsample.task_details;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ntg.user.mvpsample.R;
import com.ntg.user.mvpsample.add_edit_task.AddEditTaskActivity;
import com.ntg.user.mvpsample.add_edit_task.SubTasksAdapter;
import com.ntg.user.mvpsample.data.Task;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class TaskDetailFragment extends TaskDetailsContract.View {

    public static final int EDIT_TASK_REQUEST_CODE = 10;
    @BindView(R.id.titleContentTxtView)
    TextView titleContentTxtView;
    @BindView(R.id.descrptionContentTxtView)
    TextView descrptionContentTxtView;
    @BindView(R.id.rv_subtasks_detail)
    RecyclerView subtasksRecyclerView;
    @BindView(R.id.task_details_layout)
    ConstraintLayout taskDetailsLayout;
    LinearLayoutManager llm;
    SubTasksAdapter subTasksAdapter;
    TaskDetailsContract.Presenter presenter;
    Task task;

    public static TaskDetailFragment newInstance() {
        return new TaskDetailFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_task_detail, container, false);
        ButterKnife.bind(this, view);
        llm = new LinearLayoutManager(getContext());
        subtasksRecyclerView.setLayoutManager(llm);
        subTasksAdapter = new SubTasksAdapter(new ArrayList<>(0));
        subtasksRecyclerView.setAdapter(subTasksAdapter);
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Intent intent = getActivity().getIntent();
        task = intent.getParcelableExtra("task");
    }

    @Override
    public void onResume() {
        super.onResume();

        presenter.start();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == EDIT_TASK_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            task = data.getParcelableExtra("task");
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.mi_edit:
                navigateToEditTextUI();
                return true;
            default:
                return false;
        }
    }

    @Override
    public void setPresenter(TaskDetailsContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void showTaskDetails() {
        setTaskDetailsToViews(task);
    }

    @Override
    public void navigateToEditTextUI() {
        Intent intent = new Intent(getContext(), AddEditTaskActivity.class);
        intent.putExtra("task", task);
        intent.putExtra("requestCode", EDIT_TASK_REQUEST_CODE);
        startActivityForResult(intent, EDIT_TASK_REQUEST_CODE);
    }

    void setTaskDetailsToViews(Task taskToView) {
        if (taskToView != null) {
            titleContentTxtView.setText(taskToView.getTitle());
            descrptionContentTxtView.setText(taskToView.getDescription());
            subTasksAdapter.setSubtasks(taskToView.getSubtasks());
            if (taskToView.isCompleted()) {
                taskDetailsLayout.setBackgroundColor(
                        getContext().getResources().getColor(R.color.completeTask));
            } else {
                taskDetailsLayout.setBackgroundColor(
                        getContext().getResources().getColor(R.color.activeTask));
            }
        }
    }
}

