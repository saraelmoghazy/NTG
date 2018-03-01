package com.ntg.user.mvpsample.story_summary.view;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.transition.TransitionInflater;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.ntg.user.mvpsample.R;
import com.ntg.user.mvpsample.Utils;
import com.ntg.user.mvpsample.base.BaseFragment;
import com.ntg.user.mvpsample.model.Story;
import com.ntg.user.mvpsample.story_summary.presenter.StorySummaryPresenter;
import com.ntg.user.mvpsample.util.ViewUtility;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Fragment show story title , acceptance criteria , progress and tasks summary.
 *
 * @author Sara Elmoghazy
 */
public class StorySummaryFragment extends BaseFragment implements StorySummaryViewContract {

    private static final String STORY = "story";

    @BindView(R.id.ic_story)
    ImageView icStory;
    @BindView(R.id.txt_title)
    TextView txtTitle;
    @BindView(R.id.txt_progress)
    TextView txtProgress;
    @BindView(R.id.txt_acceptance_criteria)
    TextView txtAcceptanceCriteria;
    @BindView(R.id.txtInProgress)
    TextView txtInProgress;
    @BindView(R.id.txtDone)
    TextView txtDone;
    @BindView(R.id.txtInit)
    TextView txtInit;
    @BindView(R.id.partial_story_summary)
    RelativeLayout partialStorySummary;
    private StorySummaryPresenter presenter;

    public static StorySummaryFragment newInstance(Story story) {
        StorySummaryFragment fragment = new StorySummaryFragment();
        Bundle args = new Bundle();
        args.putSerializable(STORY, story);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_story_summary, container, false);
        ButterKnife.bind(this, view);
        ViewUtility.addShadowToView(getActivity(), partialStorySummary);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(getString(R.string.story_summary));
        if (getArguments() != null && getArguments().getSerializable(STORY) != null)
            presenter = new StorySummaryPresenter(this
                    , (Story) getArguments().getSerializable(STORY));
        presenter.start();

        return view;
    }

    /**
     * Show Story icon
     *
     * @param title
     */
    @Override
    public void showIcon(String title) {
        TextDrawable drawable = TextDrawable.builder()
                .buildRoundRect(String.valueOf(title.charAt(0)), Utils.generateColor(), 10);
        icStory.setImageDrawable(drawable);
    }

    /**
     * Show Story title
     *
     * @param title
     */
    @Override
    public void showTitle(String title) {
        txtTitle.setText(title);
    }

    /**
     * Show Story acceptance criteria
     *
     * @param description
     */
    @Override
    public void showDescription(String description) {
        txtAcceptanceCriteria.setText(description);
    }

    /**
     * Show New Tasks count
     *
     * @param newCount
     */
    @Override
    public void showNewTasks(int newCount) {
        txtInit.setText(String.valueOf(newCount));
    }

    /**
     * Show story progress
     *
     * @param progress
     */
    @Override
    public void showStoryProgress(int progress) {
        txtProgress.setText(getString(R.string.progress) + ":" + progress);
    }

    /**
     * Show done tasks count
     *
     * @param doneTasks
     */
    @Override
    public void showDoneTasks(int doneTasks) {
        txtDone.setText(String.valueOf(doneTasks));
    }

    /**
     * Show in progress tasks count
     *
     * @param inProgressTasks
     */
    @Override
    public void showInProgressTasks(int inProgressTasks) {
        txtInProgress.setText(String.valueOf(inProgressTasks));
    }
}
