package com.ntg.user.mvpsample.tasks;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ntg.user.mvpsample.R;
import com.ntg.user.mvpsample.data.Task;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.google.common.base.Preconditions.checkNotNull;



public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder> {
    TaskItemListener taskItemListener;
    List<Task> taskList;
    Context context;
    public TaskAdapter(Context context, List<Task> taskList, TaskItemListener taskItemListener) {
        this.taskList = taskList;
        this.taskItemListener = taskItemListener;
        this.context = context;
    }
    public void replaceData(List<Task> taskList) {
        setTaskList(taskList);
        notifyDataSetChanged();
    }
    private void setTaskList(List<Task> taskList) {
        this.taskList = checkNotNull(taskList);
    }

    @Override
    public TaskViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.task_item, parent, false);
        return new TaskViewHolder(view);

    }

    @Override
    public void onBindViewHolder(final TaskViewHolder holder, final int position) {

                holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                taskItemListener.onTaskClicked(taskList.get(position));
            }
        });
        holder.title.setText(taskList.get(position).getTitle());
        if(taskList.get(position).isCompleted()){
            holder.view.setBackgroundColor(Color.GREEN);

        }else {
            holder.view.setBackgroundColor(Color.RED);
        }

    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }

    public class TaskViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.itemLayout)
        LinearLayout linearLayout;
        @BindView(R.id.title)
        TextView title;
        View view;
        public TaskViewHolder(View itemView) {
            super(itemView);

            view=itemView;
            ButterKnife.bind(this, itemView);


        }
    }
}
