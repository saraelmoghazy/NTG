package com.ntg.user.mvpsample;


import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.ntg.user.mvpsample.remote.SubTask;
import com.ntg.user.mvpsample.remote.Task;

import java.util.ArrayList;
import java.util.List;

public class SubTaskAdapter extends RecyclerView.Adapter<SubTaskAdapter.TaskHolder> {

    List<SubTask> subTasks;
Context context;
    public SubTaskAdapter(Context context
            ,List<SubTask> subTasks) {
        this.subTasks = subTasks;
        this.context=context;
    }

    @Override
    public TaskHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View row = LayoutInflater.from(parent.getContext()).inflate(R.layout.sub_task_row,parent,false);
        return new TaskHolder(row);
    }

    @Override
    public void onBindViewHolder(TaskHolder holder, int position) {
        holder.et1.setText(subTasks.get(position).getSubTitle());
        holder.et2.setText(subTasks.get(position).getProgress());
        
        Toast.makeText(context, "", Toast.LENGTH_SHORT).show();

    }
    
    public List<SubTask> getSubTasks(){
        return subTasks;
    }
    
    @Override
    public int getItemCount() {
        return subTasks.size();
    }

    class TaskHolder extends RecyclerView.ViewHolder {
        EditText et1,et2;

        public TaskHolder(View itemView) {
            super(itemView);
            et1=itemView.findViewById(R.id.subTaskTitle);
            et2=itemView.findViewById(R.id.subTaskProgress);

        }
    }
    final TextWatcher Watcher = new TextWatcher() {
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
        public void onTextChanged(CharSequence s, int start, int before, int count) {}
        public void afterTextChanged(Editable s) {
        }
    };

}



