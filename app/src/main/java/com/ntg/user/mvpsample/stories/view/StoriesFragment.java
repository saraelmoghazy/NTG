package com.ntg.user.mvpsample.stories.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ntg.user.mvpsample.AddStoryActivity;
import com.ntg.user.mvpsample.R;
import com.ntg.user.mvpsample.add_task.view.TasksFragment;
import com.ntg.user.mvpsample.base.BaseFragment;
import com.ntg.user.mvpsample.model.Story;
import com.ntg.user.mvpsample.stories.presenter.StoryPresenter;
import com.ntg.user.mvpsample.story_summary.view.StorySummaryFragment;
import com.ntg.user.mvpsample.util.RecyclerViewEmptySupport;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;

/**
 * @author Sara Elmoghazy
 */
public class StoriesFragment extends BaseFragment implements StoriesViewContract {

    public static final String TAG = StoriesFragment.class.getSimpleName();

    @BindView(R.id.fab_add_story)
    FloatingActionButton fabAddStory;
    @BindView(R.id.rv_stories)
    RecyclerViewEmptySupport rvStories;
    @BindView(R.id.list_empty)
    TextView listEmptyView;

    private StoriesAdapter storiesAdapter;
    private StoryPresenter storyPresenter;
    private Subject<Story> storySummaryObservable = PublishSubject.create();
    private Subject<Story> updateTasksObservable = PublishSubject.create();

    public static StoriesFragment newInstance() {

        return new StoriesFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_stories, container, false);
        ButterKnife.bind(this, view);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(getString(R.string.dashboard));
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        rvStories.setLayoutManager(linearLayoutManager);
        rvStories.setEmptyView(listEmptyView);
        storyPresenter = new StoryPresenter(this);
        fabAddStory.setOnClickListener(v -> addNewStory());

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        storyPresenter.start();
    }

    /**
     * show stories list
     *
     * @param stories
     */
    @Override
    public void showStories(List<Story> stories) {
        storySummaryObservable.subscribe(story ->
                storyPresenter.onStorySummaryClicked(story));
        updateTasksObservable.subscribe(story -> storyPresenter.onUpdateTasksClicked(story));
        storiesAdapter = new StoriesAdapter(stories, storySummaryObservable, updateTasksObservable);
        rvStories.setAdapter(storiesAdapter);
    }

    /**
     * Add new story
     */
    @Override
    public void addNewStory() {
        Intent intent = new Intent(getActivity(), AddStoryActivity.class);
        startActivity(intent);
    }

    /**
     * Navigate to story summary screen
     *
     * @param story
     */
    @Override
    public void navigateToStorySummary(Story story) {
        getFragmentManager().beginTransaction()
                .replace(R.id.container, StorySummaryFragment.newInstance(story))
                .addToBackStack(TAG)
                .commit();
    }

    /**
     * Navigate to update story tasks screen
     *
     * @param story
     */
    @Override
    public void navigateToUpdateTasks(Story story) {
        getFragmentManager().beginTransaction()
                .addToBackStack(TAG)
                .replace(R.id.container, TasksFragment.newInstance(story)).commit();
    }
}
