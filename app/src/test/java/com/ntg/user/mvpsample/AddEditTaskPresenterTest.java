package com.ntg.user.mvpsample;

import com.ntg.user.mvpsample.add_edit_task.AddEditTaskContract;
import com.ntg.user.mvpsample.add_edit_task.AddEditTaskPresenter;
import com.ntg.user.mvpsample.data.Task;
import com.ntg.user.mvpsample.data.source.TasksDataSource;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;

/**
 * Created by ilias on 04/02/2018.
 */

public class AddEditTaskPresenterTest {

    @Mock
    TasksDataSource tasksDataSource;
    @Mock
    AddEditTaskContract.View view;
    AddEditTaskPresenter presenter;

    @Captor
    ArgumentCaptor<TasksDataSource.SaveTaskCallBack> saveTaskCallBackArgumentCaptor;

    @Before
    public void setupPresenter() {
        MockitoAnnotations.initMocks(this);
        presenter = new AddEditTaskPresenter(view, tasksDataSource);
    }

    @Test
    public void addNewTaskSuccessfully() {
        Task task = new Task("title", "descrption");
        presenter.saveTask(task);
        verify(tasksDataSource).saveTask(eq(task), saveTaskCallBackArgumentCaptor.capture());
        saveTaskCallBackArgumentCaptor.getValue().onTaskSaved();
        verify(view).showSaveTaskSuccessMsg();
        verify(view).showTasks();
    }

    @Test
    public void addNewTaskFailing() {
        Task task = new Task("title", "descrption");
        presenter.saveTask(task);
        verify(tasksDataSource).saveTask(eq(task), saveTaskCallBackArgumentCaptor.capture());
        saveTaskCallBackArgumentCaptor.getValue().onTaskFailed("error");
        verify(view).showSaveTaskFailedMsg();
        verify(view).showTasks();
    }

    @Test
    public void updateTaskSuccessfully() {
        Task task = new Task("title", "descrption");
        presenter.editTask(task);
        verify(tasksDataSource).upDateTask(eq(task), saveTaskCallBackArgumentCaptor.capture());
        saveTaskCallBackArgumentCaptor.getValue().onTaskSaved();
        verify(view).showSaveTaskSuccessMsg();
        verify(view).showTaskDetail(task);
    }

    @Test
    public void updateTaskFailing() {
        Task task = new Task("title", "descrption");
        presenter.editTask(task);
        verify(tasksDataSource).upDateTask(eq(task), saveTaskCallBackArgumentCaptor.capture());
        saveTaskCallBackArgumentCaptor.getValue().onTaskFailed("error");
        verify(view).showSaveTaskFailedMsg();
        verify(view).showTaskDetail(task);
    }
}
