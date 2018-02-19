package com.ntg.user.mvpsample.add_subtask;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ntg.user.mvpsample.R;
import com.ntg.user.mvpsample.add_subtask.presenter.SubTaskPresenter;
import com.ntg.user.mvpsample.network.SubTask;

import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author islam
 */

public class SubTaskDialogFragment extends DialogFragment implements SubTaskViewContract {

    @BindView(R.id.subTaskTitle)
    EditText subTaskTitle;
    @BindView(R.id.subTaskDescription)
    EditText subTaskDescription;
    @BindView(R.id.subTaskAddBtn)
    Button addSubTask;
    @BindView(R.id.subTaskProgress)
    EditText subTaskProgress;
    SubTaskPresenter subTaskPresenter;

    public static SubTaskDialogFragment newInstance(String id) {
        SubTaskDialogFragment frag = new SubTaskDialogFragment();
        Bundle args = new Bundle();
        args.putString("id", id);
        frag.setArguments(args);
        return frag;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_subtask_dialog, container, false);
        ButterKnife.bind(this, view);

        subTaskPresenter = new SubTaskPresenter(this);
        addSubTask.setOnClickListener(v -> {
            if (subTaskTitle.getText().toString().equals("")) {
                subTaskTitle.setError(getResources().getString(R.string.fill_data));
            } else if (subTaskDescription.getText().toString().equals("")) {
                subTaskDescription.setError(getResources().getString(R.string.fill_data));
            } else if (subTaskProgress.getText().toString().equals("")) {
                subTaskProgress.setError(getResources().getString(R.string.fill_data));
            } else if (Integer.parseInt(subTaskProgress.getText().toString()) > 100) {
                subTaskProgress.setError(getResources().getString(R.string.less_than_100));
            } else {
                SubTask subTask = new SubTask(UUID.randomUUID().toString()
                        , subTaskTitle.getText().toString()
                        , subTaskDescription.getText().toString()
                        , Integer.parseInt(subTaskProgress.getText().toString()));
                subTaskPresenter.saveSubTask(getArguments().get("id").toString(), subTask);
            }
        });
        return view;
    }

    @Override
    public void showSuccess(String success) {
        Toast.makeText(getActivity(), success, Toast.LENGTH_SHORT).show();
        dismiss();
    }

}
