package com.ntg.user.mvpsample.model;

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

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * AddFragmentClass show UI with title textView, title editText,
 * descrption textView, descrption editText for entering task attribute
 * and floatingButton for saving
 */
public class AddTaskFragment extends Fragment implements AddTaskContract.View {

    @BindView(R.id.titleEditText)
    EditText titleEditText;
    @BindView(R.id.descrptionEditText)
    EditText descrptionEditText;
    @BindView(R.id.rv_subtasks)
    RecyclerView subtasksRecyclerView;
    FloatingActionButton saveTaskFab;
    private AddTaskContract.Presenter presenter;
    SubTasksAdapter subTasksAdapter;
    LinearLayoutManager llm;

    public static AddTaskFragment newInstance() {
        return new AddTaskFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        saveTaskFab = getActivity().findViewById(R.id.save_task_fabe);
        saveTaskFab.setOnClickListener(view -> presenter.saveTask(getTaskFromUser()));
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
    public void setPresenter(AddTaskContract.Presenter presenter) {
        this.presenter = presenter;
    }

    /**
     * This method return to tasks activity to show tasks after saving task operation
     */
    @Override
    public void showTasks() {
        getActivity().finish();
    }

    /**
     * This method show Toast with a message indicating that saving task operation success
     */
    @Override
    public void showSaveTaskSuccessMsg() {
        Toast.makeText(getContext(), "Task saved successfully", Toast.LENGTH_LONG).show();
    }

    /**
     * This method show Toast with a message indicating that saving task operation failed
     */
    @Override
    public void showSaveTaskFailedMsg() {
        Toast.makeText(getContext(), "Server Error: Failed to save Task",
                Toast.LENGTH_LONG).show();
    }

    /**
     * This method is used to get task attributes(title, descrption) from user.
     *
     * @return Task instance with title and descrption from inputs.
     */
    Task getTaskFromUser() {
        String title = titleEditText.getText().toString();
        String descrption = descrptionEditText.getText().toString();
        List<Subtask> subtasks = subTasksAdapter.getSubtasks();
        return new Task(title, descrption, subtasks);
    }
}
