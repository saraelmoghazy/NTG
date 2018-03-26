package com.ntg.user.mvpsample.addtask;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
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
import android.widget.TextView;
import android.widget.Toast;

import com.ntg.user.mvpsample.R;
import com.ntg.user.mvpsample.data.Subtask;
import com.ntg.user.mvpsample.data.Task;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by mohamed yassin on 1/28/2018.
 */

public class AddTaskFragment extends Fragment implements AddTaskContract.View{
    @BindView(R.id.add_task_title)
     TextView mTitle;
    @BindView(R.id.add_task_description)
     TextView mDescription;
    @BindView(R.id.rv_subtasks)
    RecyclerView subtasksRecyclerView;
    SubTasksAdapter subTasksAdapter;
    FloatingActionButton saveFab;
    LinearLayoutManager llm;
    private AddTaskContract.Presenter presenter;

    public static AddTaskFragment newInstance() {
        return new AddTaskFragment();
    }

    public AddTaskFragment() {
        // Required empty public constructor
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        saveFab = getActivity().findViewById(R.id.fab_add_task);
        saveFab.setImageResource(R.drawable.ic_done);
        saveFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.saveTask(getTaskFromUser());
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.addtask_frag, container, false);
        ButterKnife.bind(this, root);
        llm = new LinearLayoutManager(getContext());
        subtasksRecyclerView.setLayoutManager(llm);
        subTasksAdapter = new SubTasksAdapter(new ArrayList<Subtask>(0));
        subtasksRecyclerView.setAdapter(subTasksAdapter);
        setHasOptionsMenu(true);
        return root;
    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.men_add_task, menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        final View view = getLayoutInflater().inflate(R.layout.add_subtask, null);
        switch (item.getItemId()) {
            case R.id.action_add_subtask:
                new AlertDialog.Builder(getContext())
                        .setTitle("Add new SubTask")
                        .setView(view)
                        .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                EditText addSubTaskTitleEditText, addSubTaskProgressEditText;
                                addSubTaskTitleEditText =
                                        view.findViewById(R.id.add_subtask_title);
                                addSubTaskProgressEditText =
                                        view.findViewById(R.id.add_subtask_progress);
                                String title = addSubTaskTitleEditText.getText().toString();
                                int progress = Integer.parseInt(
                                        addSubTaskProgressEditText.getText().toString());
                                Subtask subtask = new Subtask(title, progress);
                                subTasksAdapter.addSubTaskToList(subtask);

                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        })
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
    @Override
    public void showTasks() {
        getActivity().finish();
    }
    @Override
    public void showSaveTaskSuccessMsg() {
        Toast.makeText(getContext(), "Task saved successfully", Toast.LENGTH_LONG).show();
    }
    @Override
    public void showSaveTaskFailedMsg() {
        Toast.makeText(getContext(), "Server Error: Failed to save Task",
                Toast.LENGTH_LONG).show();
    }
    Task getTaskFromUser() {

        String title = mTitle.getText().toString();
        String descrption = mDescription.getText().toString();
        List<Subtask> subtasks = subTasksAdapter.getSubtasks();
        if(title.isEmpty()|| descrption.isEmpty() || subtasks.isEmpty()){
            Snackbar.make(mTitle, getString(R.string.empty_task_message), Snackbar.LENGTH_LONG).show();

        }
        return new Task(title, descrption, subtasks);
    }


}
