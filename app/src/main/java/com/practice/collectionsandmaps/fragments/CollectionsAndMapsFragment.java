package com.practice.collectionsandmaps.fragments;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.practice.collectionsandmaps.MyPagerAdapter;
import com.practice.collectionsandmaps.R;
import com.practice.collectionsandmaps.adapters.TasksRecyclerAdapter;
import com.practice.collectionsandmaps.model.Task;
import com.practice.collectionsandmaps.presenters.CollectionsPresenter;
import com.practice.collectionsandmaps.presenters.MapsPresenter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CollectionsAndMapsFragment extends Fragment implements FragmentView {


    @BindView(R.id.etAmountOfElements)
    EditText etAmountOfElements;
    @BindView(R.id.etAmountOfThreads)
    EditText etAmountOfThreads;
    @BindView(R.id.btnStartOrStop)
    ToggleButton btnStart;
    @BindView(R.id.rvTasks)
    RecyclerView rv;
    private CollectionsPresenter collectionsPresenter;
    private MapsPresenter mapsPresenter;
    private final TasksRecyclerAdapter adapter = new TasksRecyclerAdapter();
    private int indication;

    public CollectionsAndMapsFragment() {
    }

    public void setIndication(int indication) {
        this.indication = indication;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        collectionsPresenter = new CollectionsPresenter(this);
        mapsPresenter = new MapsPresenter(this);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rv.setAdapter(adapter);
        if (indication == MyPagerAdapter.COLLECTION) {
            rv.setLayoutManager(new GridLayoutManager(view.getContext(), 3));
        } else {
            rv.setLayoutManager(new GridLayoutManager(view.getContext(), 2));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_collections_and_maps, container, false);

        ButterKnife.bind(this, v);
        if(adapter.getTasks().size() == 0){
            if(indication == MyPagerAdapter.COLLECTION){
                adapter.setTasks(collectionsPresenter.getEmptyTasksFromFactory());
            } else {
                adapter.setTasks(mapsPresenter.getEmptyTasksFromFactory());
            }
        }
        btnStart.setWidth((int) btnStart.getPaint().measureText("In progress")+btnStart.getPaddingLeft()+btnStart.getPaddingRight());
        btnStart.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    if(indication == MyPagerAdapter.COLLECTION){
                        collectionsPresenter.getData(etAmountOfElements.getText().toString(), etAmountOfThreads.getText().toString());
                    } else {
                        mapsPresenter.getData(etAmountOfElements.getText().toString(), etAmountOfThreads.getText().toString());
                    }

                    if(etAmountOfElements.getText().toString().trim().length() > 0 && etAmountOfThreads.getText().toString().trim().length() > 0) {
                        btnStart.setEnabled(false);
                    } else {
                        btnStart.setChecked(false);
                    }
                }
            }
        });
        return v;
    }

    public boolean checkTasksCompleted(){
        for(Task task: adapter.getTasks()){
            if(task.getTimeForTask().equals("N/A ms")){
                return false;
            }
        }
        return true;
    }

    @Override
    public void showData(ArrayList<Task> tasks) {
        Log.d("MyLog", "In CollectionsFragment at showData()");
        if(indication == MyPagerAdapter.COLLECTION){
            adapter.setTasks(collectionsPresenter.getEmptyTasksFromFactory());
        } else {
            adapter.setTasks(mapsPresenter.getEmptyTasksFromFactory());
        }
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
                if(checkTasksCompleted()){
                    btnStart.setEnabled(true);
                    btnStart.setChecked(false);
                }
            }
        };
        mainHandler.post(myRunnable);
    }

    @Override
    public void showError() {
        Toast.makeText(getContext(), getString(R.string.enter_elements_and_threads), Toast.LENGTH_SHORT).show();
    }
}
