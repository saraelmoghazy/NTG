package com.ntg.user.mvpsample.tasks;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.ntg.user.mvpsample.R;
import com.ntg.user.mvpsample.remote.Task;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskHolder> {

    List<Task> taskList;

    public TaskAdapter(List<Task> tasksList) {
        this.taskList = tasksList;
    }

    @Override
    public TaskHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View row = LayoutInflater.from(parent.getContext()).inflate(R.layout.row,parent,false);
        TaskHolder holder = new TaskHolder(row);
        return holder;
    }

    @Override
    public void onBindViewHolder(TaskHolder holder, int position) {

        Task task = taskList.get(position);
        holder.taskName.setText(task.getTitle()+" :-: "+task.getBody());


    }
    void replaceData(List<Task> tasks){
        if (tasks != null){
            taskList =tasks;
        }
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }

    class TaskHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.task) TextView taskName;

        public TaskHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

}



