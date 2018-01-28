package com.ntg.user.mvpsample.tasks;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ntg.user.mvpsample.R;
import com.ntg.user.mvpsample.add_task.AddTaskActivity;
import com.ntg.user.mvpsample.data.Task;
import com.ntg.user.mvpsample.task_details.TaskDetailsActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.support.v4.util.Preconditions.checkNotNull;

/**
 * A placeholder fragment containing a simple view.
 */
public class TasksFragment extends Fragment implements TasksContract.View {

    FloatingActionButton addTaskFab;
    @BindView(R.id.rvTasks)
    RecyclerView rvTasks;
    @BindView(R.id.noTasksTxtView)
    TextView noTasksTxtView;
    private TasksContract.Presenter presenter;
    TasksAdapter tasksAdapter;
    LinearLayoutManager llm;

    public TasksFragment() {
    }

    public static TasksFragment newInstance() {
        return new TasksFragment();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        addTaskFab = getActivity().findViewById(R.id.add_task_fab);
        addTaskFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.addTask();
            }
        });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tasks, container, false);
        ButterKnife.bind(this, view);
        llm = new LinearLayoutManager(getContext());
        rvTasks.setLayoutManager(llm);
        tasksAdapter = new TasksAdapter(new ArrayList<Task>(0), itemListener);
        rvTasks.setAdapter(tasksAdapter);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.start();
    }

    @Override
    public void setPresenter(TasksContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void showTasks(List<Task> tasks) {
        noTasksTxtView.setVisibility(View.GONE);
        tasksAdapter.replaceData(tasks);
    }

    @Override
    public void showNoTasks() {
        noTasksTxtView.setVisibility(View.VISIBLE);
    }

    @Override
    public void showAddNewTaskUI() {
        Intent intent = new Intent(getContext(), AddTaskActivity.class);
        startActivity(intent);
    }

    @Override
    public void showTaskDetailsUI() {
        Intent intent = new Intent(getContext(), TaskDetailsActivity.class);
        startActivity(intent);
    }

    @Override
    public void showTaskAsComplete() {

    }

    @Override
    public void showTaskAsActive() {

    }

    TaskItemListener itemListener = new TaskItemListener() {
        @Override
        public void onTaskClick(Task clickedTask) {
            Intent intent = new Intent(getContext(), TaskDetailsActivity.class);
            intent.putExtra("task", clickedTask);
            startActivity(intent);
        }

        @Override
        public void onCompleteTaskClick(Task completedTask) {
            presenter.updateTaskStatusAsCompleted(completedTask);
        }

        @Override
        public void onActivateTaskClick(Task activatedTask) {
            presenter.updateTaskStatusAsCompleted(activatedTask);
        }
    };

    class TasksAdapter extends RecyclerView.Adapter<TasksAdapter.TasksViewHolder> {
        List<Task> taskList;
        TaskItemListener taskItemListener;

        public TasksAdapter(List<Task> taskList, TaskItemListener taskItemListener) {
            this.taskList = taskList;
            this.taskItemListener = taskItemListener;
        }

        @Override
        public TasksViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.rv_task_item, parent, false);
            return new TasksViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final TasksViewHolder holder, int position) {
            final Task task = taskList.get(position);
            holder.titleRvTxtView.setText(taskList.get(position).getTitle());
            holder.descrptionRvTxtView.setText(taskList.get(position).getDescription());
            holder.taskStatusCheckBox.setChecked(task.isCompleted());
            holder.taskStatusCheckBox.setOnCheckedChangeListener(
                    new CompoundButton.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(CompoundButton compoundButton,
                                                     boolean isChecked) {
                            if (isChecked) {
                                holder.taskLayout.setBackgroundColor(
                                        getResources().getColor(R.color.completeTask));
                                taskItemListener.onCompleteTaskClick(task);
                            } else {
                                holder.taskLayout.setBackgroundColor(
                                        getResources().getColor(R.color.activeTask));
                                taskItemListener.onActivateTaskClick(task);
                            }
                        }
                    });
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    taskItemListener.onTaskClick(task);
                }
            });
        }

        @Override
        public int getItemCount() {
            return taskList.size();
        }

        public void replaceData(List<Task> tasks) {
            setList(tasks);
            notifyDataSetChanged();
        }

        private void setList(List<Task> tasks) {
            if (tasks != null) {
                taskList = tasks;
            }
        }

        class TasksViewHolder extends RecyclerView.ViewHolder {
            @BindView(R.id.titleRvTxtView)
            TextView titleRvTxtView;
            @BindView(R.id.descrptionRvTxtView)
            TextView descrptionRvTxtView;
            @BindView(R.id.taskStatusCheckBox)
            CheckBox taskStatusCheckBox;
            @BindView(R.id.taskLayout)
            LinearLayout taskLayout;

            public TasksViewHolder(View itemView) {
                super(itemView);
                ButterKnife.bind(this, itemView);
            }
        }
    }

    public interface TaskItemListener {

        void onTaskClick(Task clickedTask);

        void onCompleteTaskClick(Task completedTask);

        void onActivateTaskClick(Task activatedTask);
    }
}
