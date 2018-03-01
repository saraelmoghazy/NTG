package com.ntg.user.mvpsample.add_task.view;

import android.content.Context;
import android.graphics.Color;
import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;
import com.ntg.user.mvpsample.R;
import com.ntg.user.mvpsample.Utils;
import com.ntg.user.mvpsample.model.StoryTask;
import com.ntg.user.mvpsample.util.ViewUtility;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskHolder> {

    private List<StoryTask> storyTasks = new ArrayList<>();
    Context context;
    private Subject<StoryTask> onTaskSelectedObservable = PublishSubject.create();


    public TaskAdapter(Context context, List<StoryTask> storyTasks,
                       Subject<StoryTask> onTaskSelectedObservable) {
        this.storyTasks = storyTasks;
        this.context = context;
        this.onTaskSelectedObservable = onTaskSelectedObservable;
    }

    @Override
    public TaskHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.task_item, parent,
                false);
        ViewUtility.addShadowToView(parent.getContext(), view);

        return new TaskHolder(view);
    }

    @Override
    public void onBindViewHolder(TaskHolder holder, int position) {
        StoryTask storyTask = storyTasks.get(position);
        holder.title.setText(storyTask.getTitle());
        TextDrawable drawable = TextDrawable.builder()
                .buildRound(String.valueOf(storyTask.getTitle().charAt(0)),
                        Utils.generateColor());
        holder.icTask.setImageDrawable(drawable);
        initProgressColor(holder, storyTask);
        holder.taskLayout.setOnClickListener(v -> onTaskSelectedObservable.onNext(storyTask));


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
        storyTasks.clear();
        storyTasks.addAll(tasks);
        notifyDataSetChanged();
    }


    class TaskHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.task_layout)
        RelativeLayout taskLayout;
        @BindView(R.id.ic_task)
        ImageView icTask;
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
