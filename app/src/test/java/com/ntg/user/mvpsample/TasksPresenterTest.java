//package com.ntg.user.mvpsample;
//
//import com.ntg.user.mvpsample.remote.Task;
//
//import org.junit.Before;
//import org.junit.Test;
//
//
//import java.util.ArrayList;
//import java.util.List;
//import org.mockito.ArgumentCaptor;
//import org.mockito.Captor;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import static org.mockito.Mockito.verify;
//
///**
// * Created by Sara Elmoghazy on 30/01/2018.
// */
//
//public class TasksPresenterTest {
//
//    @Mock
//    TasksDataSource tasksDataSource;
//    @Mock
//    TasksContract.View view;
//    TaskPresenter taskPresenter;
//
//    @Captor
//    private ArgumentCaptor<TasksDataSource.GetPostsCallBack> getPostsCallBackArgumentCaptor;
//
//    @Before
//    public void setupPresenter() {
//        MockitoAnnotations.initMocks(this);
//        taskPresenter = new TaskPresenter(view, tasksDataSource);
//    }
//
//    @Test
//    public void showTasks() {
//        taskPresenter.getTask();
//        List<Task> tasks = new ArrayList();
//        tasks.add(new Task("Title1", "Description1"));
//        tasks.add(new Task("Title2", "Description2"));
//        List<Task> tasksReult = new ArrayList();
//        tasks.add(new Task("Title1_result", "Description1"));
//        tasks.add(new Task("Title2_result", "Description2"));
//        verify(tasksDataSource).getPosts(getPostsCallBackArgumentCaptor.capture());
//        getPostsCallBackArgumentCaptor.getValue().onPostsLoaded(tasks);
//        verify(view).showTasks(tasksReult);
//    }
//
//    @Test
//    public void showEmptyTasks() {
//        List<Task> tasks = new ArrayList();
//        taskPresenter.getTask();
//        verify(tasksDataSource).getPosts(getPostsCallBackArgumentCaptor.capture());
//        getPostsCallBackArgumentCaptor.getValue().onPostsLoaded(tasks);
//        verify(view).showEmptyTasks();
//    }
//
//    @Test
//    public void showFailedTasksErrorMessage() {
//        taskPresenter.getTask();
//        verify(tasksDataSource).getPosts(getPostsCallBackArgumentCaptor.capture());
//        getPostsCallBackArgumentCaptor.getValue().onPostsFailed("something went wrong");
//        verify(view).showErrorMessage("something went wrong");
//    }
//}
