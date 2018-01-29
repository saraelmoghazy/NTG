package com.ntg.user.mvpsample.task;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ntg.user.mvpsample.R;
import com.ntg.user.mvpsample.data.Task;

import java.util.ArrayList;
import java.util.List;


public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder> {

    private List<Task> taskList = new ArrayList<>();

    public TaskAdapter(List<Task> taskList) {
        this.taskList = taskList;
    }

    @Override
    public TaskViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.task_item, parent, false);
        return new TaskViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TaskViewHolder holder, int position) {

        Log.e("size", taskList.size() + " size ");
        Task task = taskList.get(position);
        Log.e("title", task.getTitle());
        holder.title.setText(task.getTitle());
        holder.description.setText(task.getDescription());
    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }



    class TaskViewHolder extends RecyclerView.ViewHolder {
        TextView title, description;

        public TaskViewHolder(View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.tvTitle);
            description = itemView.findViewById(R.id.tvDescription);
        }
    }
}
