package com.ntg.user.mvpsample.add_task;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.ntg.user.mvpsample.R;
import com.ntg.user.mvpsample.add_task.presenter.AddTaskPresenter;
import com.ntg.user.mvpsample.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.keyboardsurfer.android.widget.crouton.Crouton;
import de.keyboardsurfer.android.widget.crouton.Style;

/**
 * @author Sara Elmoghazy
 */
public class AddTaskFragment extends BaseFragment implements AddTaskViewContract {

    @BindView(R.id.tv_title)
    EditText tvTitle;
    @BindView(R.id.tv_description)
    EditText tvDescription;
    @BindView(R.id.btn_addNewTask)
    Button btnAddNewTask;
    private AddTaskPresenter presenter;

    public static AddTaskFragment newInstance() {
        return new AddTaskFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_add_task, container, false);
        ButterKnife.bind(this, view);
        presenter = new AddTaskPresenter(this);
        btnAddNewTask.setOnClickListener(v -> {
            presenter.onTaskClicked(tvTitle.getText().toString(), tvDescription.getText().toString());
        });

        return view;
    }

    @Override
    public void showAddTaskSuccess(String taskTitle) {
        Crouton.makeText(getActivity(), taskTitle + " " +
                getString(R.string.task_added_successfully), Style.CONFIRM).show();
    }

    @Override
    public void navigateToTasksFragments() {
        getFragmentManager().popBackStack();
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
