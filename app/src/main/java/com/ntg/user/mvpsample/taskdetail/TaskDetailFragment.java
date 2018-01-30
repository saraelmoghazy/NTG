package com.ntg.user.mvpsample.taskdetail;

import android.content.Context;
import android.os.Bundle;
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
 * @author Islam Eldsoke
 * Fragment that represent Task Details
 */
public class TaskDetailFragment extends Fragment{

    @BindView(R.id.tv_titleDetail)
    TextView titleTv;
    @BindView(R.id.tv_descriptionDetail)
    TextView descriptionTv;
    @BindView(R.id.tv_stateDetail)
    TextView stateTv;
    Task task;


    public TaskDetailFragment() {
    }


    public static TaskDetailFragment newInstance() {
        TaskDetailFragment fragment = new TaskDetailFragment();

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

        String title = task.getTitle();
        titleTv.setText(title);
        String description = task.getDescription();
        descriptionTv.setText(description);
        String state = task.getCompleted();
        stateTv.setText(state);

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

    /**
     *
     * @param task param that returned when click task
     */
    public void getTask(Task task){
        this.task=task;
    }
}
