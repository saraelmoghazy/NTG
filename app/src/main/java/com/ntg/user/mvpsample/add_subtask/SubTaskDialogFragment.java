package com.ntg.user.mvpsample.add_subtask;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.ntg.user.mvpsample.Injection;
import com.ntg.user.mvpsample.R;
import com.ntg.user.mvpsample.data.SubTask;

import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by islam on 2/1/2018.
 */

public class SubTaskDialogFragment extends DialogFragment implements ISubTaskView {
    @BindView(R.id.subTaskTitle)
    EditText subTaskTitle;
    @BindView(R.id.subTaskDescription)
    EditText subTaskDescription;
    @BindView(R.id.subTaskAddBtn)
    Button addSubTask;
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

        View view = inflater.inflate(R.layout.fragment_subtask_dialog , container , false);
        ButterKnife.bind(this,view);


        subTaskPresenter = new SubTaskPresenter(Injection.provideAddSubTaskRepository(), this);
        addSubTask.setOnClickListener(v -> {
            SubTask subTask = new SubTask(UUID.randomUUID().toString()
                    ,subTaskTitle.getText().toString()
                    ,subTaskDescription.getText().toString()
                    ,50);
            subTaskPresenter.saveSubTask(getArguments().get("id").toString(),subTask);});
        return view;
    }

    @Override
    public void showSuccess() {

    }

    @Override
    public void showFail() {

    }
}
