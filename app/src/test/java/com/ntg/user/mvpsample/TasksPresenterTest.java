package com.ntg.user.mvpsample;

import com.ntg.user.mvpsample.add_tasks.AddTaskPresenter;
import com.ntg.user.mvpsample.add_tasks.IAddTaskView;
import com.ntg.user.mvpsample.data.Task;
import com.ntg.user.mvpsample.data.sourse.TasksDataSource;
import com.ntg.user.mvpsample.data.sourse.TasksRepository;
import com.ntg.user.mvpsample.data.sourse.remote.AddTaskRepo;
import com.ntg.user.mvpsample.task.ITaskView;
import com.ntg.user.mvpsample.task.TaskPresenter;

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
 * Created by islam on 1/31/2018.
 */

public class TasksPresenterTest {
    @Mock
    TasksRepository tasksRepository;
    AddTaskRepo addTaskRepo;

    @Mock
    ITaskView iTaskView;
    TaskPresenter taskPresenter;

    @Mock
    IAddTaskView iAddTaskView;
    AddTaskPresenter addTaskPresenter;

    @Captor
    private ArgumentCaptor<TasksDataSource.LoadTasksCallback> loadTasksCallbackArgumentCaptor;
    private ArgumentCaptor<Task> taskArgumentCaptor;

    @Before
    public void setupPresenter() {
        MockitoAnnotations.initMocks(this);
        taskPresenter = new TaskPresenter(tasksRepository, iTaskView);
    }

    @Test
    public void showTask(){
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
        verify(iTaskView).showErrorMesaage("something went wrong");
    }

    @Test
    public void addTask(){
        
    }
}
