package com.ntg.user.mvpsample.task;
import android.os.Looper;
import android.util.Log;
import com.ntg.user.mvpsample.data.Task;
import com.ntg.user.mvpsample.data.sourse.TasksDataSource;
import com.ntg.user.mvpsample.data.sourse.TasksRepository;
import java.util.List;

/**
 * @author islam class task presenter
 */

public class TaskPresenter implements ITaskPresenter {

    public static final String TAG = TaskPresenter.class.getSimpleName();

    private final TasksRepository tasksRepository;
    private final ITaskView iTaskView;

    public TaskPresenter(TasksRepository tasksRepository, ITaskView iTaskView) {
        this.tasksRepository = tasksRepository;
        this.iTaskView = iTaskView;
    }

    /**
     *
     */
    @Override
    public void loadTasks() {
        tasksRepository.getTasks(new TasksDataSource.LoadTasksCallback() {
            @Override
            public void onTasksLoaded(List<Task> tasks) {
                    iTaskView.showTasks(tasks);

            }
            @Override
            public void onTaskLoadedFail(String errMesg) {
                iTaskView.showErrorMesaage(errMesg);
            }
        });
    }

    @Override
    public void start() {
        loadTasks();
    }
}
