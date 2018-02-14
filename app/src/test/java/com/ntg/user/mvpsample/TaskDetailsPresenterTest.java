package com.ntg.user.mvpsample;

import com.ntg.user.mvpsample.data.source.TasksDataSource;
import com.ntg.user.mvpsample.task_details.TaskDetailsContract;
import com.ntg.user.mvpsample.task_details.TaskDetailsPresenter;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;

/**
 * Created by ilias on 04/02/2018.
 */

public class TaskDetailsPresenterTest {

    @Mock
    TasksDataSource tasksDataSource;
    @Mock
    TaskDetailsContract.View taskDetailsView;
    TaskDetailsPresenter taskDetailsPresenter;

    @Before
    public void setupPresenter() {
        MockitoAnnotations.initMocks(this);
        taskDetailsPresenter = new TaskDetailsPresenter(taskDetailsView, tasksDataSource);
    }

    @Test
    public void showTaskDetails(){
     taskDetailsPresenter.start();
     verify(taskDetailsView).showTaskDetails();
    }
}
