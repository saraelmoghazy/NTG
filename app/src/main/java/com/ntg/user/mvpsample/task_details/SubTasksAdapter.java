package com.ntg.user.mvpsample.task_details;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mikhaellopez.circularprogressbar.CircularProgressBar;
import com.ntg.user.mvpsample.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author Sara Elmoghazy
 */
public class SubTasksAdapter extends RecyclerView.Adapter<SubTasksAdapter.SubTaskViewHolder> {

    private List<SubTask> subTasks;

    public SubTasksAdapter(List<SubTask> subTasks) {
        this.subTasks = subTasks;
    }

    @Override
    public SubTaskViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.task_item , parent , false);

        return new SubTaskViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SubTaskViewHolder holder, int position) {
        SubTask subTask = subTasks.get(position);
        holder.title.setText(subTask.getTitle());
        holder.description.setText(subTask.getDescription());
        holder.progressBar.setProgress(subTask.getProgress());
        holder.progress.setText(String.valueOf(subTask.getProgress()));
    }

    @Override
    public int getItemCount() {
        return subTasks.size();
    }

    class SubTaskViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.tvTitle)
        TextView title;
        @BindView(R.id.tvDescription)
        TextView description;
        @BindView(R.id.progressBar)
        CircularProgressBar progressBar;
        @BindView(R.id.tv_progress)
        TextView progress;

        public SubTaskViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this , itemView);
        }
    }
}
