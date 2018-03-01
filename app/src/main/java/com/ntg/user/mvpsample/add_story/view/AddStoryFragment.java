package com.ntg.user.mvpsample.add_story.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.ntg.user.mvpsample.R;
import com.ntg.user.mvpsample.add_story.presenter.AddStoryPresenter;
import com.ntg.user.mvpsample.add_task.view.TasksFragment;
import com.ntg.user.mvpsample.base.BaseFragment;
import com.ntg.user.mvpsample.model.Story;
import com.ntg.user.mvpsample.stories.view.StoriesFragment;
import com.ntg.user.mvpsample.util.ViewUtility;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author Sara Elmoghazy
 */
public class AddStoryFragment extends BaseFragment implements AddStoryViewContract {

    public static final String TAG = StoriesFragment.class.getSimpleName();

    @BindView(R.id.tv_title)
    EditText tvTitle;
    @BindView(R.id.tv_description)
    EditText tvDescription;
    @BindView(R.id.input_title)
    TextInputLayout inputTitle;
    @BindView(R.id.input_description)
    TextInputLayout inputDescription;
    @BindView(R.id.partial_add_story)
    LinearLayout partialAddStory;

    private AddStoryPresenter presenter;

    public static AddStoryFragment newInstance() {
        return new AddStoryFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_add_story, container, false);
        ButterKnife.bind(this, view);
        ViewUtility.addShadowToView(getActivity(), partialAddStory);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(getString(R.string.add_story));
        presenter = new AddStoryPresenter(this);

        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.next:
                presenter.onSubmitStory(tvTitle.getText().toString(), tvDescription.getText().toString());
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        getActivity().getMenuInflater().inflate(R.menu.add_story_menu, menu);
    }

    /**
     * Navigate to add tasks screen
     *
     * @param story
     */
    @Override
    public void navigateToAddTasks(Story story) {
        getFragmentManager().beginTransaction()
                .addToBackStack(TAG)
                .replace(R.id.container, TasksFragment.newInstance(story)).commit();
    }

    /**
     * show missing title error
     */
    @Override
    public void showTitleMissingError() {
        inputTitle.setError(getString(R.string.tile_missing_error));
    }

    /**
     * show missing acceptance criteria error
     */
    @Override
    public void showDescriptionMissingError() {
        inputDescription.setError(getString(R.string.description_missing_error));
    }

}
