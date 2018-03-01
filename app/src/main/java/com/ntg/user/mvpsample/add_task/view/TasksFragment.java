package com.ntg.user.mvpsample.add_task.view;

import android.app.FragmentManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.ntg.user.mvpsample.AddStoryActivity;
import com.ntg.user.mvpsample.R;
import com.ntg.user.mvpsample.add_task.presenter.TasksPresenter;
import com.ntg.user.mvpsample.base.BaseFragment;
import com.ntg.user.mvpsample.model.Story;
import com.ntg.user.mvpsample.model.StoryTask;
import com.ntg.user.mvpsample.stories.view.StoriesFragment;
import com.ntg.user.mvpsample.util.RecyclerViewEmptySupport;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;

/**
 * Created by Sara Elmoghazy on 26/02/2018.
 */

public class TasksFragment extends BaseFragment implements TasksViewContract {

    private static final String STORY = "storyId";

    @BindView(R.id.fab_add_task)
    FloatingActionButton fabAddTask;
    @BindView(R.id.rv_tasks)
    RecyclerViewEmptySupport rvTasks;
    @BindView(R.id.list_empty)
    TextView listEmptyView;
    private EditText tvTitle;
    private EditText tvProgress;
    private TextInputLayout inputTitle;
    private Button btnOkAddTask;
    private Button btnCancelAddTask;
    private TaskAdapter taskAdapter;
    private TasksPresenter presenter;
    private Subject<StoryTask> onTaskSelectedObservable = PublishSubject.create();
    private BottomSheetDialog dialog;
    private StoryTask currentTask;
    private Story story;


    public static TasksFragment newInstance(Story story) {
        TasksFragment fragment = new TasksFragment();
        Bundle args = new Bundle();
        args.putSerializable(STORY, story);
        fragment.setArguments(args);

        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_task, container, false);
        ButterKnife.bind(this, view);
        presenter = new TasksPresenter(this);
        fabAddTask.setOnClickListener(v -> presenter.onAddTaskClicked());
        initTaskList();
        createAddTaskDialog();

        return view;
    }

    private void initTaskList() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        rvTasks.setLayoutManager(linearLayoutManager);
        rvTasks.setEmptyView(listEmptyView);
        onTaskSelectedObservable.subscribe(storyTask -> presenter.onTaskClicked(storyTask));
        taskAdapter = new TaskAdapter(getActivity(), new ArrayList<>(), onTaskSelectedObservable);
        rvTasks.setAdapter(taskAdapter);
        if (story.getStoryTasks() != null && story.getStoryTasks().size() > 0) {
            presenter.onStoryTasksExists(story.getStoryTasks());
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        if (getArguments() != null && getArguments().getSerializable(STORY) != null) {
            story = (Story) getArguments().getSerializable(STORY);
            ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(getString(R.string.update_task));
        } else {
            ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(getString(R.string.add_task));
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.done:
                presenter.onSubmitTasks(story.getId());
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        getActivity().getMenuInflater().inflate(R.menu.add_tasks_menu, menu);
    }

    /**
     * Show Add Task bottom Dialog
     */
    @Override
    public void showTaskDialog(StoryTask storyTask) {
        tvTitle.getText().clear();
        tvProgress.getText().clear();
        currentTask = storyTask;
        dialog.show();
        if (storyTask.getTitle() != null && !storyTask.getTitle().isEmpty()) {
            tvTitle.setText(currentTask.getTitle());
        }
        tvProgress.setText(String.valueOf(currentTask.getProgress()));
    }

    @Override
    public void dismissTaskDialog() {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
    }

    private void createAddTaskDialog() {
        dialog = new BottomSheetDialog(getActivity());
        View dialogView = getActivity().getLayoutInflater().inflate(R.layout.dialog_add_task, null);
        dialog.setContentView(dialogView);
        btnOkAddTask = dialogView.findViewById(R.id.btnOk);
        btnCancelAddTask = dialogView.findViewById(R.id.btnCancel);
        tvTitle = dialogView.findViewById(R.id.tv_title);
        tvProgress = dialogView.findViewById(R.id.tv_progress);
        inputTitle = dialogView.findViewById(R.id.input_title);
        btnOkAddTask.setOnClickListener(v ->
        {
            currentTask.setTitle(tvTitle.getText().toString());
            if (tvProgress.getText().toString().isEmpty()) tvProgress.setText(0);
            currentTask.setProgress(Integer.parseInt(tvProgress.getText().toString()));
            presenter.onOkDialogClicked(currentTask);
        });
        btnCancelAddTask.setOnClickListener(v -> presenter.onCancelDialogClicked());
    }

    @Override
    public void updateTaskList(List<StoryTask> storyTasks) {
        taskAdapter.updateTasks(storyTasks);
    }

    @Override
    public void navigateToStories() {
        if (getActivity() instanceof AddStoryActivity)
            getActivity().finish();
        else
            getFragmentManager().popBackStack(StoriesFragment.TAG, FragmentManager.POP_BACK_STACK_INCLUSIVE);
    }

    @Override
    public void showTaskNotValid() {
        inputTitle.setError(getString(R.string.tile_missing_error));
    }
}

