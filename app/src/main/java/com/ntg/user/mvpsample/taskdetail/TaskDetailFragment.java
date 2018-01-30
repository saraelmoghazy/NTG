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
public class TaskDetailFragment extends Fragment implements ITaskDetailsView{

    @BindView(R.id.tv_titleDetail)
    TextView titleTv;
    @BindView(R.id.tv_descriptionDetail)
    TextView descriptionTv;
    @BindView(R.id.tv_stateDetail)
    TextView stateTv;
    ITaskDetailsPresenter iTaskDetailsPresenter;

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
        if (getArguments() != null) {
            Task task = (Task) getArguments().get("task");
            iTaskDetailsPresenter = new TaskDetailsPresenter(this , task);
            iTaskDetailsPresenter.start();
        }
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
    public void showState(String state) {
        stateTv.setText(state);
    }
}
