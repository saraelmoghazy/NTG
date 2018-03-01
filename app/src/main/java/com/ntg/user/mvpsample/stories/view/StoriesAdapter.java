package com.ntg.user.mvpsample.stories.view;

import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.ntg.user.mvpsample.R;
import com.ntg.user.mvpsample.Utils;
import com.ntg.user.mvpsample.model.Story;
import com.ntg.user.mvpsample.util.ViewUtility;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.subjects.Subject;

public class StoriesAdapter extends RecyclerView.Adapter<StoriesAdapter.TaskViewHolder> {

    private List<Story> stories;
    private Subject<Story> onStorySummaryClicked;
    private Subject<Story> onUpdateTasksClicked;

    public StoriesAdapter(List<Story> stories, Subject<Story> onStorySummaryClicked,
                          Subject<Story> onUpdateTasksClicked) {
        this.stories = stories;
        this.onStorySummaryClicked = onStorySummaryClicked;
        this.onUpdateTasksClicked = onUpdateTasksClicked;
    }

    @Override
    public TaskViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.story_item, parent,
                false);

        ViewUtility.addShadowToView(parent.getContext(), view);

        return new TaskViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TaskViewHolder holder, int position) {
        Story story = stories.get(position);
        holder.txtStoryTitle.setText(story.getTitle());
        TextDrawable drawable = TextDrawable.builder()
                .buildRoundRect(String.valueOf(story.getTitle().charAt(0)), Utils.generateColor()
                        , 10);
        holder.txtStoryConsumption.setText(String.valueOf(story.getProgress()));
        holder.icStory.setImageDrawable(drawable);
        holder.progressBar.setProgress(story.getProgress());
        setProgressColor(holder, story.getProgress());
        holder.ivStorySummary.setOnClickListener(view -> onStorySummaryClicked
                .onNext(story));
        holder.ivUpdateTasks.setOnClickListener(view -> onUpdateTasksClicked
                .onNext(story));
    }

    /**
     * set progress color bases on percentage
     *
     * @param holder
     * @param progress
     */
    private void setProgressColor(TaskViewHolder holder, int progress) {
        if (progress < 50) {
            DrawableCompat.setTint(holder.progressBar.getProgressDrawable()
                    , ContextCompat.getColor(holder.progressBar.getContext(), R.color.black_red));
        } else if (progress > 50 && progress < 100) {
            DrawableCompat.setTint(holder.progressBar.getProgressDrawable()
                    , ContextCompat.getColor(holder.progressBar.getContext(), R.color.lemon_yellow));
        } else if (progress == 100) {
            DrawableCompat.setTint(holder.progressBar.getProgressDrawable()
                    , ContextCompat.getColor(holder.progressBar.getContext(), R.color.black_green));
        }
    }


    @Override
    public int getItemCount() {
        return stories.size();
    }

    class TaskViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.ic_story)
        ImageView icStory;
        @BindView(R.id.txt_story_title)
        TextView txtStoryTitle;
        @BindView(R.id.txt_story_consumption)
        TextView txtStoryConsumption;
        @BindView(R.id.progress_story_consumption)
        ProgressBar progressBar;
        @BindView(R.id.iv_story_summery_arrow)
        ImageView ivStorySummary;
        @BindView(R.id.iv_update_tasks_arrow)
        ImageView ivUpdateTasks;

        public TaskViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


}
