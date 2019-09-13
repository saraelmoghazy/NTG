package com.ntg.user.mvpsample.show_task;


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
import com.ntg.user.mvpsample.model.SubTasksAdapter;
import com.ntg.user.mvpsample.model.Task;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class ShowTaskFragment extends Fragment implements ShowTaskInterFace.View {

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
    ShowTaskInterFace.Presenter presenter;
    Task task;

    public static ShowTaskFragment newInstance() {
        return new ShowTaskFragment();
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
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void setPresenter(ShowTaskInterFace.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void showTaskDetails() {
        setTaskDetailsToViews(task);
    }

    void setTaskDetailsToViews(Task taskToView) {
        if (taskToView != null) {
            titleContentTxtView.setText(taskToView.getTitle());
            descrptionContentTxtView.setText(taskToView.getDescription());
            subTasksAdapter.setSubtasks(taskToView.getSubtasks());
            if (taskToView.isCompleted()) {
                taskDetailsLayout.setBackgroundColor(
                        getContext().getResources().getColor(R.color.completeTask));
            }else {
                taskDetailsLayout.setBackgroundColor(
                        getContext().getResources().getColor(R.color.activeTask));
            }
        }
    }
}

