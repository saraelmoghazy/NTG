package com.ntg.user.mvpsample;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.ntg.user.mvpsample.add_story.view.AddStoryFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.keyboardsurfer.android.widget.crouton.Crouton;

public class AddStoryActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);


        getFragmentManager().beginTransaction().add(R.id.container,
                AddStoryFragment.newInstance()).commit();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
