package com.ntg.user.mvpsample.model;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.ntg.user.mvpsample.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ilias on 03/02/2018.
 */

public class SubTasksAdapter extends RecyclerView.Adapter<SubTasksAdapter.SubTasksViewHolder> {

    private List<Subtask> subtasks;

    public SubTasksAdapter(List<Subtask> subtasks) {
        this.subtasks = subtasks;
    }

    @Override
    public SubTasksViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_subtask, parent, false);
        return new SubTasksViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SubTasksViewHolder holder, int position) {
        holder.subtaskTitleTextView.setText(subtasks.get(position).getTitle());
        holder.subTaskProgressBar.setProgress(subtasks.get(position).getProgress());
    }

    @Override
    public int getItemCount() {
        return subtasks.size();
    }

    void addSubTaskToList(Subtask subtask){
        subtasks.add(subtask);
        notifyDataSetChanged();
    }

    List<Subtask> getSubtasks() {
        return subtasks;
    }

    public void setSubtasks(List<Subtask> subtasks) {
        this.subtasks = subtasks;
        notifyDataSetChanged();
    }

    class SubTasksViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_subtask_title)
        TextView subtaskTitleTextView;
        @BindView(R.id.pb_subtask_progress)
        ProgressBar subTaskProgressBar;

        public SubTasksViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public interface SubTaskItemListener {

        void onSubTaskClick(Task clickedTask);
    }
}
