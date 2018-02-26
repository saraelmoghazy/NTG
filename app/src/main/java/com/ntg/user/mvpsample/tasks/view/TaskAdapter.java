package com.ntg.user.mvpsample.tasks.view;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mikhaellopez.circularprogressbar.CircularProgressBar;
import com.ntg.user.mvpsample.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.subjects.Subject;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder> {

    private List<Task> taskList = new ArrayList<>();
    private Subject<Task> onTaskClicked;

    public TaskAdapter(List<Task> taskList, Subject<Task> onTaskClicked) {
        this.taskList = taskList;
        this.onTaskClicked = onTaskClicked;
    }

    @Override
    public TaskViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.task_item, parent,
                false);
        return new TaskViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TaskViewHolder holder, int position) {
        Task task = taskList.get(position);
        holder.title.setText(task.getTitle());
        holder.description.setText(task.getDescription());
        holder.progressBar.setProgress(task.getProgress());
        initProgressColor(holder, task);
        holder.itemView.setOnClickListener(view -> onTaskClicked.onNext(task));
    }

    private void initProgressColor(TaskViewHolder holder, Task task) {
        if (task.getProgress() == 0)
            holder.progressBar.setColor(Color.RED);
        else if (task.getProgress() > 0 && task.getProgress() < 80)
            holder.progressBar.setColor(Color.YELLOW);
        else if (task.getProgress() > 80)
            holder.progressBar.setColor(Color.GREEN);
    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }

    class TaskViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tvTitle)
        TextView title;
        @BindView(R.id.tvDescription)
        TextView description;
        @BindView(R.id.progressBar)
        CircularProgressBar progressBar;
        @BindView(R.id.tv_progress)
        TextView progress;

        public TaskViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
