package com.ntg.user.mvpsample.task_details;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ntg.user.mvpsample.R;
import com.ntg.user.mvpsample.data.Task;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class TaskDetailFragment extends Fragment implements TaskDetailsContract.View {

    @BindView(R.id.titleTxtViewDetail)
    TextView titleTxtViewDetail;
    @BindView(R.id.descrptionTxtViewDetail)
    TextView descrptionTxtViewDetail;
    @BindView(R.id.completeTxtViewDetail)
    TextView completeTxtViewDetail;
    @BindView(R.id.titleContentTxtView)
    TextView titleContentTxtView;
    @BindView(R.id.descrptionContentTxtView)
    TextView descrptionContentTxtView;
    @BindView(R.id.completeStatusTxtView)
    TextView completeStatusTxtView;
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

        return view;
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
    public void setPresenter(TaskDetailsContract.Presenter presenter) {
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
            if (taskToView.isCompleted()) {
                completeStatusTxtView.setText("complete");
            } else {
                completeStatusTxtView.setText("Active");
            }
        }
    }
}

