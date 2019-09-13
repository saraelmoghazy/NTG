package com.ntg.user.mvpsample;

import com.ntg.user.mvpsample.model.Task;
import com.ntg.user.mvpsample.model.taskdatasources.TasksDataSource;
import com.ntg.user.mvpsample.tasks.TasksContract;
import com.ntg.user.mvpsample.PresenterLayer.TasksPresenter;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.verify;

/**
 * TasksPresenterTest tests the units of TasksPresenter Class.
 */

public class TasksPresenterTest {

    @Mock
    TasksDataSource tasksDataSource;
    @Mock
    TasksContract.View tasksView;
    TasksPresenter tasksPresenter;

    @Captor
    private ArgumentCaptor<TasksDataSource.GetTasksCallBack> getTasksCallBackArgumentCaptor;

    @Before
    public void setupPresenter() {
        MockitoAnnotations.initMocks(this);
        tasksPresenter = new TasksPresenter(tasksView, tasksDataSource);
    }

    @Test
    public void showTask() {
        tasksPresenter.getTasks();
        List<Task> tasks = new ArrayList<>(0);
        tasks.add(new Task("task 1", "descrption 1"));
        tasks.add(new Task("task2 2", "descrption 2"));
        verify(tasksDataSource).loadData(getTasksCallBackArgumentCaptor.capture());
        getTasksCallBackArgumentCaptor.getValue().onTasksLoaded(tasks);
        verify(tasksView).showTasks(tasks);
    }

    @Test
    public void updateTask(){
        Task task = new Task("title", "descrption");
        tasksPresenter.updateTaskStatus(task);
        verify(tasksDataSource).upDateTask(task);
    }

    @Test
    public void showAddTaskUI(){
        tasksPresenter.navigateToAddTaskUI();
        verify(tasksView).showAddNewTaskUI();
    }
}
