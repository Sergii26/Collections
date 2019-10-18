package com.practice.collectionsandmaps.ui.fragment;

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

import com.practice.collectionsandmaps.R;
import com.practice.collectionsandmaps.dto.Task;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CollectionsAndMapsFragment extends Fragment implements FragmentView, CompoundButton.OnCheckedChangeListener {

    @BindView(R.id.etAmountOfElements)
    EditText etAmountOfElements;
    @BindView(R.id.etAmountOfThreads)
    EditText etAmountOfThreads;
    @BindView(R.id.btnStartOrStop)
    ToggleButton btnStart;
    @BindView(R.id.rvTasks)
    RecyclerView rv;
    private CalculationFragmentPresenter calculationFragmentPresenter;
    private final TasksRecyclerAdapter adapter = new TasksRecyclerAdapter();
    private int indication;
    private Handler mainHandler;
    private Runnable myRunnable;

    public CollectionsAndMapsFragment() {
    }

    public void setIndication(int indication) {
        this.indication = indication;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        calculationFragmentPresenter = new CalculationFragmentPresenter(this, indication);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rv.setAdapter(adapter);
        rv.setLayoutManager(new GridLayoutManager(view.getContext(), calculationFragmentPresenter.getSpanCount()));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_collections_and_maps, container, false);
        ButterKnife.bind(this, v);
        if(adapter.getTasks().size() == 0){
                adapter.setTasks(calculationFragmentPresenter.getEmptyTasksFromFactory());
        }
        btnStart.setWidth((int) btnStart.getPaint().measureText(getString(R.string.btn_in_progress))+btnStart.getPaddingLeft()+btnStart.getPaddingRight());
        btnStart.setOnCheckedChangeListener(this);
        return v;
    }

    public String getText(EditText et){
        return et.getText().toString();
    }

    public boolean isDefaultTime(){
        for(Task task: adapter.getTasks()){
            if(task.getTimeForTask().equals("N/A ms")){
                return false;
            }
        }
        return true;
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if(isChecked) {
            calculationFragmentPresenter.getData(getText(etAmountOfElements), getText(etAmountOfThreads));
            if(calculationFragmentPresenter.inputValidation(getText(etAmountOfElements), getText(etAmountOfThreads))) {
                btnStart.setEnabled(false);
            } else {
                btnStart.setChecked(false);
            }
        }
    }

    @Override
    public void showData(List<Task> tasks) {
        Log.d("MyLog", "In CollectionsFragment at showData()");
        adapter.setTasks(calculationFragmentPresenter.getEmptyTasksFromFactory());
        adapter.setTasks(tasks);
    }

    @Override
    public void updateData() {
        mainHandler = new Handler(Looper.getMainLooper());
        myRunnable = new Runnable() {
            @Override
            public void run() {
                Log.d("MyLog", "In CollectionsFragment at updateData()");
                adapter.notifyDataSetChanged();
                if(isDefaultTime()){
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
