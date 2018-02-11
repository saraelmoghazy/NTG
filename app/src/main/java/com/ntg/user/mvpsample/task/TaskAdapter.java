package com.ntg.user.mvpsample.task;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.mikhaellopez.circularprogressbar.CircularProgressBar;
import com.ntg.user.mvpsample.R;
import com.ntg.user.mvpsample.TaskItemListener;
import com.ntg.user.mvpsample.data.Task;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.subjects.PublishSubject;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder> {

    private List<Task> taskList = new ArrayList<>();
    private PublishSubject<Task> taskPublishSubject;

    public TaskAdapter(List<Task> taskList
            , PublishSubject<Task> taskPublishSubject) {
        this.taskList = taskList;
        this.taskPublishSubject = taskPublishSubject;
    }

    @Override
    public TaskViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.task_item, parent, false);
        return new TaskViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TaskViewHolder holder, int position) {
        Task task = taskList.get(position);
        holder.title.setText(task.getTitle());
        holder.description.setText(task.getDescription());
        holder.progressBar.setProgress(task.getProgress());
        holder.progress.setText(String.valueOf(task.getProgress()));
        //holder.itemView.setOnClickListener(view -> taskItemListener.onTaskClicked(task));
        holder.itemView.setOnClickListener(view -> taskPublishSubject.onNext(task));
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
            ButterKnife.bind(this , itemView);
        }
    }
}
