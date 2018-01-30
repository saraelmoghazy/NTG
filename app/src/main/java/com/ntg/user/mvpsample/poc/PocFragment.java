package com.ntg.user.mvpsample.poc;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.ntg.user.mvpsample.R;
import com.ntg.user.mvpsample.addedittask.AddEditTaskActivity;
import com.ntg.user.mvpsample.poc.data.Poc;

import java.util.ArrayList;
import java.util.List;

import de.keyboardsurfer.android.widget.crouton.Crouton;
import de.keyboardsurfer.android.widget.crouton.Style;


public class PocFragment extends Fragment  implements PocContract.IPocView, PocItemListener{
    PocPresenter pocPresenter;
    RecyclerView mToDosList;
    RelativeLayout mPocsView;
    LinearLayout mNoPocs;
    PocAdapter pocAdapter;
    ProgressBar progressBar;
    LinearLayoutManager llm;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        FloatingActionButton fab =
                (FloatingActionButton) getActivity().findViewById(R.id.fab_add_task);
        fab.setImageResource(R.drawable.ic_add);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent AddTaskIntent=new Intent(getContext(), AddEditTaskActivity.class);
                startActivity(AddTaskIntent);

            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.pocs_frag, container, false);
        setHasOptionsMenu(true);
        mToDosList =  view.findViewById(R.id.toDos_list);
        mPocsView =  view.findViewById(R.id.pocsLayout);
        mNoPocs = view.findViewById(R.id.noPocsLayout);
        progressBar = view.findViewById(R.id.progressBar);
        pocAdapter = new PocAdapter(new ArrayList<Poc>(0), this);
        llm = new LinearLayoutManager(getContext());
        mToDosList.setLayoutManager(llm);
        mToDosList.setAdapter(pocAdapter);

        return view;
    }
    public static PocFragment newInstance() {
        return new PocFragment();

    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.poc_fragment_menu, menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_local: {
                pocPresenter.getPocs(false);
                return true;
            }
            case R.id.menu_remote: {
                pocPresenter.getPocs(true);

                return true;

            }
        }
        return true;
    }
    @Override
    public void onResume() {
        super.onResume();
        pocPresenter.start();
    }

    @Override
    public void showPocs(List<Poc> pocList) {
        mNoPocs.setVisibility(View.GONE);
        mPocsView.setVisibility(View.VISIBLE);
        pocAdapter.replaceData(pocList);
    }

    @Override
    public void showNoPocs() {
        mNoPocs.setVisibility(View.VISIBLE);
        mPocsView.setVisibility(View.GONE);
    }

    @Override
    public void showMessageError(String msgError) {
        Crouton.makeText(getActivity(), msgError, Style.ALERT).show();

    }

    @Override
    public void showLoadingIndicator(boolean isShown) {
        if (isShown)
            progressBar.setVisibility(View.VISIBLE);
        else
            progressBar.setVisibility(View.GONE);


    }
    @Override
    public void setPresenter(PocPresenter pocPresenter) {
        this.pocPresenter = pocPresenter;

    }

    @Override
    public void onPocClicked(Poc poc) {
        Crouton.makeText(getActivity(), "Selected Item : " + poc.getTitle(), Style.INFO).show();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Crouton.cancelAllCroutons();
    }
}
