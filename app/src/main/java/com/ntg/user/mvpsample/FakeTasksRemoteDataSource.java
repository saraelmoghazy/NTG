/*
 * Copyright 2016, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.ntg.user.mvpsample;

import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;


import com.google.common.collect.Lists;
import com.ntg.user.mvpsample.data.Task;
import com.ntg.user.mvpsample.data.source.TasksDataSource;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Implementation of a remote data source with static access to the data for easy testing.
 */
public class FakeTasksRemoteDataSource implements TasksDataSource {

    private static FakeTasksRemoteDataSource INSTANCE;

    private static final Map<String, Task> TASKS_SERVICE_DATA = new LinkedHashMap<>();

    // Prevent direct instantiation.
    private FakeTasksRemoteDataSource() {}

    public static FakeTasksRemoteDataSource getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new FakeTasksRemoteDataSource();
        }
        return INSTANCE;
    }

    @Override
    public void getTasks(@NonNull TasksDataSource.LoadTasksCallback callback) {
        callback.onTasksLoaded(Lists.newArrayList(TASKS_SERVICE_DATA.values()));
    }

    @Override
    public void getTask(@NonNull String taskId, @NonNull GetTaskCallback callback) {
        Task task = TASKS_SERVICE_DATA.get(taskId);
        callback.onTaskLoaded(task);
    }

    @Override
    public void saveTask(@NonNull Task task) {
       TASKS_SERVICE_DATA.put(task.getUserId(), task);
    }

    @Override
    public void completeTask(@NonNull Task task) {
        Task completedTask = new Task(task.getTitle(), task.getDescription());
        TASKS_SERVICE_DATA.put(task.getUserId(), completedTask);
    }

    @Override
    public void completeTask(@NonNull String taskId) {
        // Not required for the remote data source.
    }

    @Override
    public void activateTask(@NonNull Task task) {
        Task activeTask = new Task(task.getTitle(), task.getDescription());
        TASKS_SERVICE_DATA.put(task.getUserId(), activeTask);
    }

    @Override
    public void activateTask(@NonNull String taskId) {
        // Not required for the remote data source.
    }

    @Override
    public void clearCompletedTasks() {
        Iterator<Map.Entry<String, Task>> it = TASKS_SERVICE_DATA.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, Task> entry = it.next();
            if (entry.getValue().isCompleted()) {
                it.remove();
            }
        }
    }

    public void refreshTasks() {
        // Not required because the {@link TasksRepository} handles the logic of refreshing the
        // tasks from all the available data sources.
    }

    @Override
    public void deleteTask(@NonNull String taskId) {
        TASKS_SERVICE_DATA.remove(taskId);
    }

    @Override
    public void deleteAllTasks() {
        TASKS_SERVICE_DATA.clear();
    }

    @VisibleForTesting
    public void addTasks(Task... tasks) {
        for (Task task : tasks) {
            TASKS_SERVICE_DATA.put(task.getUserId(), task);
        }
    }
}