package com.ntg.user.mvpsample;

import com.ntg.user.mvpsample.add_task.presenter.AddTaskPresenter;
import com.ntg.user.mvpsample.network.Task;
import com.ntg.user.mvpsample.tasks.model.GetTasksDataSource;
import com.ntg.user.mvpsample.tasks.model.GetTasksRepository;
import com.ntg.user.mvpsample.tasks.view.ITaskView;
import com.ntg.user.mvpsample.tasks.presenter.TaskPresenter;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;

/**
 * Created by islam on 1/31/2018.
 */

public class TasksPresenterTest {
    @Inject
    GetTasksRepository tasksRepository;

    @Mock
    ITaskView iTaskView;

    TaskPresenter taskPresenter;

    AddTaskPresenter addTaskPresenter;

    @Captor
    private ArgumentCaptor<GetTasksDataSource.LoadTasksCallback> loadTasksCallbackArgumentCaptor;
    @Captor
    private ArgumentCaptor<GetTasksDataSource.SaveTask.AddTaskCallback> addTaskCallbackArgumentCaptor;

    @Before
    public void setupPresenter() {
        MockitoAnnotations.initMocks(this);
    //   NetTestComponent component = DaggerN.builder()
//                .myModule(new TestModule()).build();
//         component.inject(this);
        taskPresenter = new TaskPresenter(tasksRepository, iTaskView);
        //addTaskPresenter = new AddTaskPresenter(addTaskRepo, iAddTaskView);
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
        verify(iTaskView).showErrorMesaage("something went wrong");
    }

    @Test
    public void addTask() {
        Task task = new Task("title", "descrption");
        addTaskPresenter.saveTask(task);
        verify(addTaskRepo).saveTask(eq(task), addTaskCallbackArgumentCaptor.capture());
        addTaskCallbackArgumentCaptor.getValue().onTaskAdded(eq(task));
    }
}
