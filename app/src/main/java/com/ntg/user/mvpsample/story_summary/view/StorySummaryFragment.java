package com.ntg.user.mvpsample.story_summary.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ntg.user.mvpsample.R;
import com.ntg.user.mvpsample.Utils;
import com.ntg.user.mvpsample.base.BaseFragment;
import com.ntg.user.mvpsample.model.Story;
import com.ntg.user.mvpsample.story_summary.presenter.StorySummaryPresenter;
import com.ntg.user.mvpsample.util.ViewUtility;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author Sara Elmoghazy
 */
public class StorySummaryFragment extends BaseFragment implements StorySummaryViewContract {

    private static final String STORY = "story";

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
        if (getArguments() != null && getArguments().getSerializable(STORY) != null)
            presenter = new StorySummaryPresenter(this
                    , (Story) getArguments().getSerializable(STORY));
        presenter.start();

        return view;
    }


    @Override
    public void showTitle(String title) {
        txtTitle.setText(title);

    }

    @Override
    public void showDescription(String description) {
        txtAcceptanceCriteria.setText(description);
    }

    @Override
    public void showNotStartedSubTasks(int notStartedCount) {
        txtInit.setText("" + notStartedCount);
    }

    @Override
    public void showProgress(int progress) {
        txtProgress.setText(getString(R.string.progress) + ":" + progress);
    }

    @Override
    public void showDoneSubTasks(int doneSubTasks) {
        txtDone.setText("" + doneSubTasks);
    }

    @Override
    public void showInProgressSubTasks(int inProgressSubTasks) {
        txtInProgress.setText("" + inProgressSubTasks);
    }
}
