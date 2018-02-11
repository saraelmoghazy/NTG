package com.ntg.user.mvpsample.tasks;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.ntg.user.mvpsample.R;
import com.ntg.user.mvpsample.add_edit_task.AddEditTaskActivity;
import com.ntg.user.mvpsample.data.Task;
import com.ntg.user.mvpsample.task_details.TaskDetailsActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.subjects.PublishSubject;

import static com.ntg.user.mvpsample.task_details.TaskDetailFragment.TASK_DETAIL_INTENT_KEY;

/**
 * A placeholder fragment containing a simple view.
 */
public class TasksFragment extends TasksContract.View {

    public static int ADD_TASK_REQUEST_CODE = 20;
    FloatingActionButton addTaskFab;
    @BindView(R.id.rvTasks)
    RecyclerView rvTasks;
    @BindView(R.id.pb_load_tasks)
    ProgressBar loadTasksProgressBar;
    @BindView(R.id.noTasksTxtView)
    TextView noTasksTxtView;
    private TasksContract.Presenter presenter;
    TasksAdapter tasksAdapter;
    LinearLayoutManager llm;
    ActionMode actionMode;
    Task taskFromAdapter;
    PublishSubject<ItemEvent> taskObservable = PublishSubject.create();

    public static TasksFragment newInstance() {
        return new TasksFragment();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        addTaskFab = getActivity().findViewById(R.id.add_task_fab);
        addTaskFab.setOnClickListener((view) -> presenter.navigateToAddTaskUI());

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tasks, container, false);
        ButterKnife.bind(this, view);
        llm = new LinearLayoutManager(getContext());
        rvTasks.setLayoutManager(llm);
        tasksAdapter = new TasksAdapter(getContext(),
                new ArrayList<>(0), taskObservable);
        rvTasks.setAdapter(tasksAdapter);
        taskObservable.subscribe(itemEvent -> {
            switch (itemEvent.getEventType()) {
                case ItemEvent.EventType.CLICK:
                    showTaskDetailsUI(itemEvent.getTask());
                    break;
                case ItemEvent.EventType.LONG_CLICK:
                    showActionMode(itemEvent.getTask());
                    break;
            }
        });

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        presenter.start();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ADD_TASK_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            presenter.getTasks();
        }
    }

    @Override
    public void setPresenter(TasksContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void showErrorMsg(String errorMsg) {
        noTasksTxtView.setVisibility(View.VISIBLE);
        loadTasksProgressBar.setVisibility(View.INVISIBLE);
        noTasksTxtView.setText(errorMsg);
    }

    @Override
    public void showTasks(List<Task> tasks) {
        noTasksTxtView.setVisibility(View.GONE);
        loadTasksProgressBar.setVisibility(View.GONE);
        tasksAdapter.replaceData(tasks);
    }

    @Override
    public void showNoTasks() {
        noTasksTxtView.setVisibility(View.VISIBLE);
        noTasksTxtView.setText(R.string.no_tasks_string);
    }

    @Override
    public void showLoadingIndicator() {
        loadTasksProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void showAddNewTaskUI() {
        Intent intent = new Intent(getContext(), AddEditTaskActivity.class);
        startActivityForResult(intent, ADD_TASK_REQUEST_CODE);
    }

    @Override
    public void showTaskDetailsUI(Task task) {
        Intent intent = new Intent(getContext(), TaskDetailsActivity.class);
        intent.putExtra(TASK_DETAIL_INTENT_KEY, task);
        startActivity(intent);
    }

    ActionMode.Callback callback = new ActionMode.Callback() {
        @Override
        public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
            actionMode.getMenuInflater().inflate(R.menu.menu_action, menu);
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
            return false;
        }

        @Override
        public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.mi_delete:
                    presenter.deleteTask(taskFromAdapter);
                    actionMode.finish();
                    return true;
                default:
                    return false;
            }
        }

        @Override
        public void onDestroyActionMode(ActionMode actionMode) {
            TasksFragment.this.actionMode = null;
        }
    };

    void showActionMode(Task longClickedTask) {
        taskFromAdapter = longClickedTask;
        if (actionMode != null) {
            return;
        }
        actionMode = getActivity().startActionMode(callback);
    }
}
