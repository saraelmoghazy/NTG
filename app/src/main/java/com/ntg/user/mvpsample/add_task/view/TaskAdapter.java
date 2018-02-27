package com.ntg.user.mvpsample.add_task.view;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ntg.user.mvpsample.R;
import com.ntg.user.mvpsample.model.StoryTask;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskHolder> {

    private List<StoryTask> storyTasks = new ArrayList<>();
    Context context;

    public TaskAdapter(Context context, List<StoryTask> storyTasks) {
        this.storyTasks = storyTasks;
        this.context = context;
    }

    @Override
    public TaskHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.task_item, parent,
                false);
        return new TaskHolder(view);
    }

    @Override
    public void onBindViewHolder(TaskHolder holder, int position) {
        StoryTask storyTask = storyTasks.get(position);
        holder.title.setText(storyTask.getTitle());
        initProgressColor(holder, storyTask);

    }

    private void initProgressColor(TaskHolder holder, StoryTask storyTask) {
        if (storyTask.getProgress() == 0) {
            holder.txtTaskProgress.setTextColor(Color.RED);
            holder.txtTaskProgress.setText(context.getString(R.string.state_not_started));
        } else if (storyTask.getProgress() > 0 && storyTask.getProgress() < 100) {
            holder.txtTaskProgress.setTextColor(Color.YELLOW);
            holder.txtTaskProgress.setText(context.getString(R.string.state_in_progress) +
                    ":" + storyTask.getProgress());
        } else if (storyTask.getProgress() == 100) {
            holder.txtTaskProgress.setTextColor(Color.GREEN);
            holder.txtTaskProgress.setText(context.getString(R.string.state_done));

        }
    }

    @Override
    public int getItemCount() {
        return storyTasks.size();
    }

    public void updateTasks(List<StoryTask> tasks) {
        storyTasks = tasks;
        notifyDataSetChanged();
    }


    class TaskHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.txtTitle)
        TextView title;
        @BindView(R.id.txtProgress)
        TextView txtTaskProgress;

        public TaskHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }
    }
}