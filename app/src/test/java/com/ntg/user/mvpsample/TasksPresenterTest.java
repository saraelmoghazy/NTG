package com.ntg.user.mvpsample;

import com.ntg.user.mvpsample.add_tasks.AddTaskPresenter;
import com.ntg.user.mvpsample.add_tasks.IAddTaskView;
import com.ntg.user.mvpsample.data.Task;
import com.ntg.user.mvpsample.data.sourse.TasksDataSource;
import com.ntg.user.mvpsample.data.sourse.TasksRepository;
import com.ntg.user.mvpsample.add_tasks.AddTaskRepo;
import com.ntg.user.mvpsample.task.ITaskView;
import com.ntg.user.mvpsample.task.TaskComponent;
import com.ntg.user.mvpsample.task.TaskPresenter;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by islam on 1/31/2018.
 */

public class TasksPresenterTest {
    TasksRepository tasksRepository;

    @Mock
    AddTaskRepo addTaskRepo;

    @Mock
    ITaskView iTaskView;

    TaskPresenter taskPresenter;

    @Mock
    IAddTaskView iAddTaskView;

    AddTaskPresenter addTaskPresenter;

    @Mock
    TaskComponent taskComponent;


    @Captor
    private ArgumentCaptor<TasksDataSource.LoadTasksCallback> loadTasksCallbackArgumentCaptor;
    @Captor
    private ArgumentCaptor<TasksDataSource.SaveTask.AddTaskCallback> addTaskCallbackArgumentCaptor;

    @Before
    public void setupPresenter() {
        MockitoAnnotations.initMocks(this);
        taskPresenter = new TaskPresenter(iTaskView);
        when(taskPresenter.getTaskComponent()).thenReturn(taskComponent);
        addTaskPresenter = new AddTaskPresenter(iAddTaskView);
    }

    @Test
    public void showTask() {
        taskPresenter.loadTasks();
        List<Task> tasks = new ArrayList();
        tasks.add(new Task("title", "description"));
        tasks.add(new Task("title", "description"));
        verify(tasksRepository).getTasks(loadTasksCallbackArgumentCaptor.capture());
        loadTasksCallbackArgumentCaptor.getValue().onTasksLoaded(tasks);
        verify(iTaskView).showTasks(tasks);
    }

    @Test
    public void showFailedTasksErrorMessage() {
        taskPresenter.loadTasks();
        verify(tasksRepository).getTasks(loadTasksCallbackArgumentCaptor.capture());
        loadTasksCallbackArgumentCaptor.getValue().onTaskLoadedFail("something went wrong");
        verify(iTaskView).showErrorMessage("something went wrong");
    }

    @Test
    public void addTask() {
        Task task = new Task("title", "descrption");
        addTaskPresenter.saveTask(task);
        verify(addTaskRepo).saveTask(eq(task), addTaskCallbackArgumentCaptor.capture());
        addTaskCallbackArgumentCaptor.getValue().onTaskAdded(eq(task));
    }
}
