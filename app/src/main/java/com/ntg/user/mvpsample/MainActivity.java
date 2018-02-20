package com.ntg.user.mvpsample;

import android.app.Activity;
import android.os.Bundle;

import com.ntg.user.mvpsample.tasks.view.TasksFragment;

import de.keyboardsurfer.android.widget.crouton.Crouton;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getFragmentManager().beginTransaction().add(R.id.container,
                TasksFragment.newInstance()).commit();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Crouton.cancelAllCroutons();
    }
}
