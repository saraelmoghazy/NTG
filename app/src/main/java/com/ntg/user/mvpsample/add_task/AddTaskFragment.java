package com.ntg.user.mvpsample.add_task;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.ntg.user.mvpsample.R;
import com.ntg.user.mvpsample.data.Task;

import butterknife.BindView;
import butterknife.ButterKnife;

/** AddFragmentClass show UI with title textView, title editText,
 * descrption textView, descrption editText for entering task attribute
 * and floatingButton for saving
 */
public class AddTaskFragment extends Fragment implements AddTaskContract.View {

    @BindView(R.id.titleEditText)
    EditText titleEditText;
    @BindView(R.id.descrptionEditText)
    EditText descrptionEditText;
    FloatingActionButton saveTaskFab;
    private AddTaskContract.Presenter presenter;

    public static AddTaskFragment newInstance() {
        return new AddTaskFragment();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        saveTaskFab = getActivity().findViewById(R.id.save_task_fab);
        saveTaskFab.setOnClickListener(view -> presenter.saveTask(getTaskFromUser()));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_task, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void setPresenter(AddTaskContract.Presenter presenter) {
        this.presenter = presenter;
    }

    /**This method return to tasks activity to show tasks after saving task operation
     */
    @Override
    public void showTasks() {
        getActivity().finish();
    }

    /**This method show Toast with a message indicating that saving task operation success
     */
    @Override
    public void showSaveTaskSuccessMsg() {
        Toast.makeText(getContext(), "Task saved successfully", Toast.LENGTH_LONG).show();
    }

    /**This method show Toast with a message indicating that saving task operation failed
     */
    @Override
    public void showSaveTaskFailedMsg() {
        Toast.makeText(getContext(), "Server Error: Failed to save Task",
                Toast.LENGTH_LONG).show();
    }

    /**This method is used to get task attributes(title, descrption) from user.
     * @return Task instance with title and descrption from inputs.
     */
    Task getTaskFromUser() {
        String title = titleEditText.getText().toString();
        String descrption = descrptionEditText.getText().toString();

        return new Task(title, descrption);
    }
}
