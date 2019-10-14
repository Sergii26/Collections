package com.practice.collectionsandmaps.fragments;


import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.practice.collectionsandmaps.R;
import com.practice.collectionsandmaps.adapters.CollectionsRecyclerAdapter;
import com.practice.collectionsandmaps.model.Task;
import com.practice.collectionsandmaps.presenters.CollectionsPresenter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


public class CollectionsFragment extends android.support.v4.app.Fragment implements FragmentView {

    @BindView(R.id.etAmountOfElements)
    EditText etAmountOfElements;
    @BindView(R.id.etAmountOfThreads)
    EditText etAmountOfThreads;
    @BindView(R.id.btnStartOrStop)
    Button btnStart;
    @BindView(R.id.rvCollectionsFragment)
    RecyclerView rv;
    private CollectionsPresenter presenter;
    private CollectionsRecyclerAdapter adapter;

    public CollectionsFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_collections, container, false);
        setRetainInstance(true);
        ButterKnife.bind(this, v);
        adapter = new CollectionsRecyclerAdapter();
        presenter = new CollectionsPresenter(this);
        rv.setAdapter(adapter);
        rv.setLayoutManager(new GridLayoutManager(container.getContext(), 3));
        if(savedInstanceState != null && savedInstanceState.containsKey("savedTimeForTasks")){
            String[] savedTimeForTasks = savedInstanceState.getStringArray("savedTimeForTasks");
            adapter.setTasks(presenter.getEmptyTasksFromFactory());
            for(int i = 0; i < adapter.getTasks().size(); i++) {
                adapter.getTasks().get(i).setTimeForTask(savedTimeForTasks[i]);
            }
            adapter.notifyDataSetChanged();
        } else {
            adapter.setTasks(presenter.getEmptyTasksFromFactory());
            adapter.notifyDataSetChanged();
        }
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(etAmountOfElements.getText().toString().trim().length() > 0 && etAmountOfThreads.getText().toString().trim().length() > 0
                        && Integer.parseInt(etAmountOfThreads.getText().toString()) > 0 && Integer.parseInt(etAmountOfElements.getText().toString()) > 0) {
                    adapter.setTasks(presenter.getEmptyTasksFromFactory());
                    presenter.getTasksFromFactory(Integer.parseInt(etAmountOfElements.getText().toString()), Integer.parseInt(etAmountOfThreads.getText().toString()));
                    presenter.getData();
                } else {
                    Toast.makeText(container.getContext(), getString(R.string.enter_elements_and_threads), Toast.LENGTH_SHORT).show();
                }
            }
        });
        return v;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d("MyLog", "In CollectionsFragment at onSaveInstanceState()");
        String [] timeForTasks = new String[adapter.getTasks().size()];
        for(int i = 0; i < adapter.getTasks().size(); i ++){
            adapter.getTasks().get(i).getTimeForTask();
            timeForTasks[i] = adapter.getTasks().get(i).getTimeForTask();
        }
        outState.putStringArray("savedTimeForTasks", timeForTasks);
    }

    @Override
    public void showData(ArrayList<Task> tasks) {
        Log.d("MyLog", "In CollectionsFragment at showData()");
        adapter.setTasks(tasks);
    }

    @Override
    public void updateData() {
        Handler mainHandler = new Handler(Looper.getMainLooper());
        Runnable myRunnable = new Runnable() {
            @Override
            public void run() {
                Log.d("MyLog", "In CollectionsFragment at updateData()");
                adapter.notifyDataSetChanged();
            }
        };
        mainHandler.post(myRunnable);
    }
}
