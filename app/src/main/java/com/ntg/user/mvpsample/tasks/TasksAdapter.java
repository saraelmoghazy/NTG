package com.ntg.user.mvpsample.tasks;

import android.content.Context;
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
import io.reactivex.subjects.PublishSubject;

/**
 * Created by ilias on 29/01/2018.
 */

class TasksAdapter extends RecyclerView.Adapter<TasksAdapter.TasksViewHolder> {

    private List<Task> taskList;
    private Context context;
    private PublishSubject<ItemEvent> taskObservable;

    TasksAdapter(Context context, List<Task> taskList, PublishSubject<ItemEvent> taskObservable) {
        this.taskList = taskList;
        this.context = context;
        this.taskObservable = taskObservable;
    }

    @Override
    public TasksViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_task, parent, false);

        return new TasksViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final TasksViewHolder holder, int position) {
        final Task task = taskList.get(position);
        holder.titleRvTxtView.setText(taskList.get(position).getTitle());
        holder.descrptionRvTxtView.setText(taskList.get(position).getDescription());
        holder.taskStatusCheckBox.setChecked(task.isCompleted());
        if (task.isCompleted()) {
            holder.taskLayout.setBackgroundColor(
                    context.getResources().getColor(R.color.completeTask));
        } else {
            holder.taskLayout.setBackgroundColor(
                    context.getResources().getColor(R.color.activeTask));
        }
        holder.taskStatusCheckBox.setOnCheckedChangeListener(
                (compoundButton, isChecked) -> {
                    if (isChecked) {
                        holder.taskLayout.setBackgroundColor(
                                context.getResources().getColor(R.color.completeTask));
                        task.setCompleted(true);
                    } else {
                        holder.taskLayout.setBackgroundColor(
                                context.getResources().getColor(R.color.activeTask));
                        task.setCompleted(false);
                    }
                });
        ItemEvent itemEvent = new ItemEvent(task);
        holder.itemView.setOnClickListener(view -> {
            itemEvent.setEventType(ItemEvent.EventType.CLICK);
            taskObservable.onNext(itemEvent);
        });
        holder.itemView.setOnLongClickListener(view ->
        {
            itemEvent.setEventType(ItemEvent.EventType.LONG_CLICK);
            taskObservable.onNext(itemEvent);
            return true;
        });
    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }

    void replaceData(List<Task> tasks) {
        setList(tasks);
        notifyDataSetChanged();
    }

    private void setList(List<Task> tasks) {
        if (tasks != null) {
            taskList = tasks;
        }
    }

    class TasksViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.titleRvTxtView)
        TextView titleRvTxtView;
        @BindView(R.id.descrptionRvTxtView)
        TextView descrptionRvTxtView;
        @BindView(R.id.taskStatusCheckBox)
        CheckBox taskStatusCheckBox;
        @BindView(R.id.taskLayout)
        LinearLayout taskLayout;

        TasksViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}

