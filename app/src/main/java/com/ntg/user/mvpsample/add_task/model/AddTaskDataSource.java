package com.ntg.user.mvpsample.add_task.model;

import com.ntg.user.mvpsample.model.StoryTask;

import java.util.List;

import io.reactivex.Observable;

/**
 * @author Sara Elmoghazy
 */

public interface AddTaskDataSource {

    Observable<Void> saveTasks(int storyId, List<StoryTask> storyTasks);
}
