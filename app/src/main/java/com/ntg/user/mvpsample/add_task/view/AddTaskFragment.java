package com.ntg.user.mvpsample.add_task.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.ntg.user.mvpsample.R;
import com.ntg.user.mvpsample.add_task.presenter.TaskPresenter;
import com.ntg.user.mvpsample.base.BaseFragment;
import com.ntg.user.mvpsample.model.StoryTask;
import com.ntg.user.mvpsample.stories.view.StoriesFragment;
import com.ntg.user.mvpsample.story_summary.view.StorySummaryFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Sara Elmoghazy on 26/02/2018.
 */

public class AddTaskFragment extends BaseFragment implements AddTaskViewContract {
    private static final String STORY_ID = "storyId";


    @BindView(R.id.fab_add_task)
    FloatingActionButton fabAddTask;
    @BindView(R.id.rv_tasks)
    RecyclerView rvTasks;
    private TaskAdapter taskAdapter;
    private TaskPresenter presenter;
    List<StoryTask> tasks = new ArrayList<>();

    public static AddTaskFragment newInstance(int storyId) {
        AddTaskFragment fragment = new AddTaskFragment();
        Bundle args = new Bundle();
        args.putInt(STORY_ID, storyId);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_task, container, false);
        ButterKnife.bind(this, view);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Add Tasks");

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        rvTasks.setLayoutManager(linearLayoutManager);
        taskAdapter = new TaskAdapter(this.getActivity(), tasks);
        rvTasks.setAdapter(taskAdapter);
        presenter = new TaskPresenter(this);
        fabAddTask.setOnClickListener(v -> showAddNewTaskDialog());

        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.next:
                if (getArguments() != null)
                    presenter.onSubmitTasks(getArguments().getInt(STORY_ID), tasks);
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        getActivity().getMenuInflater().inflate(R.menu.main_menu, menu);
    }

    public void showAddNewTaskDialog() {
        final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(getActivity());
        View dialogView = getActivity().getLayoutInflater().inflate(R.layout.dialog_add_task, null);
        Button btnOk = dialogView.findViewById(R.id.btnOk);
        Button btnCancel = dialogView.findViewById(R.id.btnCancel);
        TextView title = dialogView.findViewById(R.id.tv_title);
        TextView progress = dialogView.findViewById(R.id.tv_progress);
        bottomSheetDialog.setContentView(dialogView);

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(title.getText())) {
                    title.setError("title must be set");
                } else if (TextUtils.isEmpty(progress.getText())) {
                } else {
                    progress.setError("progress must be set");
                    presenter.onAddTask(title.getText().toString(),
                            Integer.parseInt(progress.getText().toString()));
                    bottomSheetDialog.dismiss();
                }
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomSheetDialog.dismiss();
            }
        });
        bottomSheetDialog.show();
    }

    @Override
    public void updateTasks(StoryTask storyTask) {
        tasks.add(storyTask);
        taskAdapter.updateTasks(tasks);
    }

    @Override
    public void navigateToStoriesActivity() {
        getActivity().finish();
    }
}

