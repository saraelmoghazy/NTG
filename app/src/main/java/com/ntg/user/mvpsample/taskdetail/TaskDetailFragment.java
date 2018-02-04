package com.ntg.user.mvpsample.taskdetail;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;
import com.ntg.user.mvpsample.R;
import com.ntg.user.mvpsample.add_subtask.SubTaskDialogFragment;
import com.ntg.user.mvpsample.data.SubTask;
import com.ntg.user.mvpsample.data.Task;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author Islam Eldsoke
 * Fragment that represent Task Details
 */
public class TaskDetailFragment extends android.app.Fragment implements ITaskDetailsView{

    @BindView(R.id.tv_titleDetail)
    TextView titleTv;
    @BindView(R.id.tv_descriptionDetail)
    TextView descriptionTv;
    @BindView(R.id.tv_stateDetail)
    TextView stateTv;
    @BindView(R.id.btn_addSubTask)
    FloatingActionButton navigateToSubTaskDialog;
    @BindView(R.id.rv_subTasks)
    RecyclerView subTaskRecycler;
    private SubTasksAdapter subTasksAdapter;

    ITaskDetailsPresenter iTaskDetailsPresenter;
    String id;

    public static TaskDetailFragment newInstance(Task task) {
        TaskDetailFragment fragment = new TaskDetailFragment();
        Bundle args = new Bundle();
        args.putSerializable("task" , task);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_task_detail, container, false);
        ButterKnife.bind(this , view);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        subTaskRecycler.setLayoutManager(linearLayoutManager);
        if (getArguments() != null) {
            Task task = (Task) getArguments().get("task");
            id = task.getId();
            iTaskDetailsPresenter = new TaskDetailsPresenter(this , task);
            iTaskDetailsPresenter.start();
        }
        navigateToSubTaskDialog.setOnClickListener(v -> navigateToSubTasksFragment());
        return view;
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
    public void showTitle(String title) {
        titleTv.setText(title);
    }

    @Override
    public void showDescription(String description) {
        descriptionTv.setText(description);

    }

    @Override
    public void showSubTasks(List<SubTask> subTasks){
        subTasksAdapter = new SubTasksAdapter(subTasks);
        subTaskRecycler.setAdapter(subTasksAdapter);
    }

    @Override
    public void navigateToSubTasksFragment() {
        SubTaskDialogFragment dialogFragment = SubTaskDialogFragment.newInstance(id);
        dialogFragment.show(getFragmentManager(),"islam");
        this.getActivity().getFragmentManager().executePendingTransactions();
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
        layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
        Dialog yourDialog = dialogFragment.getDialog();
        yourDialog.getWindow().setAttributes(layoutParams);
    }

}
