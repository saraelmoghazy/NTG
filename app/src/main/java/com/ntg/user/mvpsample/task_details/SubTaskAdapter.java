package com.ntg.user.mvpsample.task_details;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ntg.user.mvpsample.R;
import com.ntg.user.mvpsample.remote.SubTask;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SubTaskAdapter extends RecyclerView.Adapter<SubTaskAdapter.TaskHolder> {
    
    List<SubTask> subTasks;
    
    public SubTaskAdapter(
            List<SubTask> subTasks) {
            this.subTasks = subTasks;
    }
    
    @Override
    public TaskHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View row = LayoutInflater.from(parent.getContext()).inflate(R.layout.sub_task_row, parent, false);
        return new TaskHolder(row);
    }
    
    @Override
    public void onBindViewHolder(TaskHolder holder, int position) {
        holder.tv1.setText(subTasks.get(position).getSubTitle());
        holder.tv2.setText(subTasks.get(position).getProgress());
    }
    
    void addSubTask(SubTask subTask) {
        subTasks.add(subTask);
        notifyDataSetChanged();
    }
    
    public List<SubTask> getSubTasks()
    {
        return subTasks;
    }
    
    @Override
    public int getItemCount() {
        return subTasks.size();
    }
    
    class TaskHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.subTaskTitle)TextView tv1;
        @BindView(R.id.subTaskProgress)TextView tv2;
        
        public TaskHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
    
}



