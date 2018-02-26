package com.ntg.user.mvpsample.task_details;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ntg.user.mvpsample.R;
import com.ntg.user.mvpsample.add_subtask.view.SubTaskDialogFragment;
import com.ntg.user.mvpsample.base.BaseFragment;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author Sara Elmoghazy
 */
public class TaskDetailsFragment extends BaseFragment implements TaskDetailsViewContract {

    public static final String TASK = "task";

    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.tvDescription)
    TextView tvDescription;
    @BindView(R.id.tvStatus)
    TextView tvState;
    @BindView(R.id.rvSubTasks)
    RecyclerView rvSubTask;

    private SubTasksAdapter subTasksAdapter;
    private TaskDetailsPresenter presenter;
    private Task task;

    public static TaskDetailsFragment newInstance(Task task) {
        TaskDetailsFragment fragment = new TaskDetailsFragment();
        Bundle args = new Bundle();
        args.putSerializable(TASK, task);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_task_details, container, false);
        ButterKnife.bind(this, view);
        initSubTaskList();
        if (getArguments() != null) {
            task = (Task) getArguments().get(TASK);
            presenter = new TaskDetailsPresenter(this, task);
            presenter.start();
        }

        return view;
    }

    @OnClick(R.id.btn_addSubTask)
    void addSubTask() {
        navigateToSubTasksFragment();
    }

    private void initSubTaskList() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        rvSubTask.setLayoutManager(linearLayoutManager);
    }

    @Override
    public void showTitle(String title) {
        tvTitle.setText(title);
    }

    @Override
    public void showDescription(String description) {
        tvDescription.setText(description);
    }

    @Override
    public void showSubTasks(List<SubTask> subTasks) {
        subTasksAdapter = new SubTasksAdapter(subTasks);
        rvSubTask.setAdapter(subTasksAdapter);
    }

    @Override
    public void navigateToSubTasksFragment() {
        SubTaskDialogFragment subTaskDialogFragment = SubTaskDialogFragment.newInstance(task.getId());
        subTaskDialogFragment.show(getFragmentManager(), TASK);
        getFragmentManager().executePendingTransactions();
        Dialog scalableDialog = subTaskDialogFragment.getDialog();
        scalableDialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
    }
}
