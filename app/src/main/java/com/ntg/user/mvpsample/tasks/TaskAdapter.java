package com.ntg.user.mvpsample.tasks;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ntg.user.mvpsample.R;
import com.ntg.user.mvpsample.data.Task;

import java.util.List;

/**
 * Created by devsaad on 1/29/2018.
 */

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder>{

    private List<Task> tasks;

    public TaskAdapter(List<Task> tasks) {
        this.tasks = tasks;
    }

    void replaceData(List<Task> taskList){
        if (taskList != null){
            tasks = taskList;
        }
        notifyDataSetChanged();
    }

    @Override
    public TaskViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.task_item , parent ,false);
        return new TaskViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TaskViewHolder holder, int position) {
        Task task = tasks.get(position);
        holder.title.setText(task.getTittle());
        holder.description.setText(task.getDescribtion());
    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }

    class TaskViewHolder extends RecyclerView.ViewHolder{
    TextView title,description;
        public TaskViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            description=itemView.findViewById(R.id.Describtion);
        }
    }
}
