package com.ntg.user.mvpsample.tasks;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.ntg.user.mvpsample.ActivityUtils;
import com.ntg.user.mvpsample.Injection;
import com.ntg.user.mvpsample.R;


public class PocActivity extends AppCompatActivity {

    PocPresenter mPocPresenter;
    DrawerLayout mDrawerLayout;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pocs_act);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);


        PocFragment pocFragment = (PocFragment) getSupportFragmentManager().findFragmentById(R.id.contentFrame);

        if (pocFragment == null) {
            pocFragment = PocFragment.newInstance();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), pocFragment, R.id.contentFrame);
        }
        mPocPresenter = new PocPresenter(Injection.providePocRepo(), pocFragment);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
