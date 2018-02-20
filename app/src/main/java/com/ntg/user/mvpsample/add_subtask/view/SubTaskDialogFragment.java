package com.ntg.user.mvpsample.add_subtask.view;

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
import com.ntg.user.mvpsample.base.BaseDialogFragment;
import com.shawnlin.numberpicker.NumberPicker;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author Sara Elmoghazy
 */

public class SubTaskDialogFragment extends BaseDialogFragment implements SubTaskViewContract {
    public static final String TASK_ID = "id";

    @BindView(R.id.tvTitle)
    EditText tvTitle;
    @BindView(R.id.tvDescription)
    EditText tvDescription;
    @BindView(R.id.btnAddSubTask)
    Button btnAddSubTask;
    @BindView(R.id.npProgress)
    NumberPicker npProgress;
    private SubTaskPresenter presenter;

    public static SubTaskDialogFragment newInstance(int id) {
        SubTaskDialogFragment frag = new SubTaskDialogFragment();
        Bundle args = new Bundle();
        args.putInt(TASK_ID, id);
        frag.setArguments(args);

        return frag;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_subtask_dialog, container, false);
        ButterKnife.bind(this, view);

        presenter = new SubTaskPresenter(this);

        btnAddSubTask.setOnClickListener(v -> {
            presenter.onAddSubTaskClicked(tvTitle.getText().toString(),
                    tvDescription.getText().toString(), npProgress.getValue(),
                    getArguments().getInt(TASK_ID));
        });

        return view;
    }

    @Override
    public void showAddSubTaskSuccess(String success) {
        dismiss();
    }

    @Override
    public void showTitleMissedError() {
        tvTitle.setError(getString(R.string.tile_missing_error));
    }

    @Override
    public void showDescriptionMissedError() {
        tvDescription.setError(getString(R.string.description_missing_error));
    }
}
