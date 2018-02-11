package com.ntg.user.mvpsample.add_edit_task;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.ntg.user.mvpsample.R;
import com.ntg.user.mvpsample.data.Subtask;
import com.ntg.user.mvpsample.data.Task;
import com.ntg.user.mvpsample.data.source.remote.network.ErrorType;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.ntg.user.mvpsample.task_details.TaskDetailFragment.EDIT_TASK_REQUEST_CODE;
import static com.ntg.user.mvpsample.task_details.TaskDetailFragment.TASK_DETAIL_INTENT_KEY;

/**
 * AddFragmentClass show UI with title textView, title editText,
 * descrption textView, descrption editText for entering task attribute
 * and floatingButton for saving
 */
public class AddEditTaskFragment extends AddEditTaskContract.View {

    @BindView(R.id.titleEditText)
    EditText titleEditText;
    @BindView(R.id.descrptionEditText)
    EditText descrptionEditText;
    @BindView(R.id.rv_subtasks)
    RecyclerView subtasksRecyclerView;
    FloatingActionButton saveTaskFab;
    private AddEditTaskContract.Presenter presenter;
    SubTasksAdapter subTasksAdapter;
    LinearLayoutManager llm;
    Task taskToEdit;

    public static AddEditTaskFragment newInstance() {
        return new AddEditTaskFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        saveTaskFab = getActivity().findViewById(R.id.save_task_fab);
        int requestCode = getActivity().getIntent().getIntExtra("requestCode", 0);
        if (requestCode == EDIT_TASK_REQUEST_CODE) {
            taskToEdit = getActivity().getIntent().getParcelableExtra("task");
            setTaskToEdit(taskToEdit);
        }
        saveTaskFab.setOnClickListener(view -> {
            if (requestCode == EDIT_TASK_REQUEST_CODE) {
                presenter.editTask(getUpdatedTaskFromUser(taskToEdit));
            } else {
                presenter.saveTask(getNewTaskFromUser());
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_task, container, false);
        ButterKnife.bind(this, view);
        llm = new LinearLayoutManager(getContext());
        subtasksRecyclerView.setLayoutManager(llm);
        subTasksAdapter = new SubTasksAdapter(new ArrayList<>(0));
        subtasksRecyclerView.setAdapter(subTasksAdapter);

        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        View view = getLayoutInflater().inflate(R.layout.add_subtask_dialog, null);
        switch (item.getItemId()) {
            case R.id.action_add_subtask:
                new AlertDialog.Builder(getContext())
                        .setTitle("Add new SubTask")
                        .setView(view)
                        .setPositiveButton("Add", (dialogInterface, i) -> {
                            EditText addSubTaskTitleEditText, addSubTaskProgressEditText;
                            addSubTaskTitleEditText =
                                    view.findViewById(R.id.et_add_subtask_title);
                            addSubTaskProgressEditText =
                                    view.findViewById(R.id.et_add_subtask_progress);
                            String title = addSubTaskTitleEditText.getText().toString();
                            int progress = Integer.parseInt(
                                    addSubTaskProgressEditText.getText().toString());
                            Subtask subtask = new Subtask(title, progress);
                            subTasksAdapter.addSubTaskToList(subtask);

                        })
                        .setNegativeButton("Cancel",
                                ((dialogInterface, i) -> dialogInterface.cancel()))
                        .show();

                return true;
            default:
                return false;
        }
    }

    @Override
    public void setPresenter(AddEditTaskContract.Presenter presenter) {
        this.presenter = presenter;
    }

    /**
     * This method return to tasks activity to show tasks after saving task operation
     */
    @Override
    public void showTasks() {
        getActivity().setResult(Activity.RESULT_OK);
        getActivity().finish();
    }

    @Override
    public void showTaskDetail(Task task) {
        Intent intent = new Intent();
        intent.putExtra(TASK_DETAIL_INTENT_KEY, task);
        getActivity().setResult(Activity.RESULT_OK, intent);
        getActivity().finish();
    }

    /**
     * This method show Toast with a message indicating that saving task operation success
     */
    @Override
    public void showSaveTaskSuccessMsg() {
        if (getContext() != null) {
            Toast.makeText(getContext(), "Task saved successfully", Toast.LENGTH_LONG).show();
        }
    }


    /**
     * This method show Toast with a message indicating that saving task operation failed
     */
    @Override
    public void showSaveTaskFailedMsg() {
        if (getContext() != null) {
            Toast.makeText(getContext(), "Server Error: Failed to save Task",
                    Toast.LENGTH_LONG).show();
        }
    }

    private void setTaskToEdit(Task taskToEdit) {
        if (taskToEdit != null) {
            titleEditText.setText(taskToEdit.getTitle());
            descrptionEditText.setText(taskToEdit.getDescription());
            subTasksAdapter.setSubtasks(taskToEdit.getSubtasks());
        }
    }

    /**
     * This method is used to get task attributes(title, descrption) from user.
     *
     * @return Task instance with title and descrption from inputs.
     */
    Task getNewTaskFromUser() {
        String title = titleEditText.getText().toString();
        String descrption = descrptionEditText.getText().toString();
        List<Subtask> subtasks = subTasksAdapter.getSubtasks();
        return new Task(title, descrption, subtasks);
    }

    Task getUpdatedTaskFromUser(Task task) {
        task.setTitle(titleEditText.getText().toString());
        task.setDescription(descrptionEditText.getText().toString());
        task.setSubtasks(subTasksAdapter.getSubtasks());
        return task;
    }
}
