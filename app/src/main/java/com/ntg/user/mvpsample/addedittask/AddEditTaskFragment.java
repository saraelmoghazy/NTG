package com.ntg.user.mvpsample.addedittask;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ntg.user.mvpsample.R;
import com.ntg.user.mvpsample.Task;
import com.ntg.user.mvpsample.addedittask.data.source.remote.ApiClient;
import com.ntg.user.mvpsample.addedittask.data.source.remote.ApiService;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by mohamed yassin on 1/28/2018.
 */

public class AddEditTaskFragment extends Fragment implements AddEditTaskContract.View {
    public static final String ARGUMENT_EDIT_TASK_ID = "EDIT_TASK_ID";

    private AddEditTaskContract.Presenter mPresenter;
    Task task;

    private TextView mTitle;
    private TextView mDescription;
    private ApiService mAPIService;

    public static AddEditTaskFragment newInstance() {
        return new AddEditTaskFragment();
    }


    @Override
    public void onResume() {
        super.onResume();

        mPresenter.start();
    }

    @Override
    public void setPresenter(@NonNull AddEditTaskContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mAPIService = ApiClient.getClient().create(ApiService.class);
        FloatingActionButton fab =
                (FloatingActionButton) getActivity().findViewById(R.id.fab_edit_task_done);
        fab.setImageResource(R.drawable.ic_done);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = mTitle.getText().toString().trim();
                String body = mDescription.getText().toString().trim();
                mPresenter.saveTask(title, body);
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.addtask_frag, container, false);
        mTitle = (TextView) root.findViewById(R.id.add_task_title);
        mDescription = (TextView) root.findViewById(R.id.add_task_description);
        setHasOptionsMenu(true);
        return root;
    }

    @Override
    public void showEmptyTaskError() {
        Snackbar.make(mTitle, getString(R.string.empty_task_message), Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void showTasksList() {
        getActivity().setResult(Activity.RESULT_OK);
        getActivity().finish();
    }
}
