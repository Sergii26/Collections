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
import com.practice.collectionsandmaps.dto.TaskData;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CollectionsAndMapsFragment extends Fragment implements FragmentView, CompoundButton.OnCheckedChangeListener {

    public static final String KEY_INDICATOR = "indicator";

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
    private final Handler  mainHandler = new Handler(Looper.getMainLooper());
    private final Runnable myRunnable = new Runnable() {
        @Override
        public void run() {
            Log.d("MyLog", "In CollectionsFragment at updateData() calculation is running");
            adapter.notifyDataSetChanged();
            if(calculationFragmentPresenter.isNotDefaultTime()){
                Log.d("MyLog", "In CollectionsFragment at updateData() calculation is running isNotDefaultTime() = "
                        + calculationFragmentPresenter.isNotDefaultTime());
                btnStart.setChecked(false);
            }
        }
    };

    public CollectionsAndMapsFragment() {
    }

    public static CollectionsAndMapsFragment newInstance(int indicator) {
        final Bundle b = new Bundle();
        b.putInt(KEY_INDICATOR, indicator);
        final CollectionsAndMapsFragment f = new CollectionsAndMapsFragment();
        f.setArguments(b);
        return f;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        if(getArguments() != null){
            calculationFragmentPresenter = FragmentInjector.createPresenter(this, getArguments().getInt(KEY_INDICATOR));
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        btnStart.setWidth((int) btnStart.getPaint().measureText(getString(R.string.btn_stop_state)) + btnStart.getPaddingLeft() + btnStart.getPaddingRight());
        btnStart.setOnCheckedChangeListener(this);
        rv.setAdapter(adapter);
        rv.setLayoutManager(new GridLayoutManager(view.getContext(), calculationFragmentPresenter.getCollectionsCount()));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_collections_and_maps, container, false);
        if(adapter.getItemCount() == 0){
                adapter.setTasks(calculationFragmentPresenter.getTasksFromFactory());
        }
        return v;
    }

    public String getText(EditText et){
        return et.getText().toString();
    }

    public void hideProgress(){
        adapter.hideProgress();
    }

    public void showProgress(){
        adapter.showProgress();
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if(isChecked) {
            Log.d("MyLog", "In CollectionsAndMapsFragment at onCheckedChanged() \"true branch\"");
            calculationFragmentPresenter.startCalculation(getText(etAmountOfElements), getText(etAmountOfThreads));
        } else {
            Log.d("MyLog", "In CollectionsAndMapsFragment at onCheckedChanged() \"false branch\"");
            calculationFragmentPresenter.stopCalculation();
        }
    }

    @Override
    public void showData(List<TaskData> tasks) {
        Log.d("MyLog", "In CollectionsFragment at showData()");
        adapter.setTasks(tasks);
    }

    @Override
    public void updateData() {
        mainHandler.post(myRunnable);
    }

    @Override
    public void showError(String error) {
        Log.d("MyLog", "In CollectionsFragment at showError()");
        Toast.makeText(getContext(), error, Toast.LENGTH_SHORT).show();
        btnStart.setChecked(false);
    }

    @Override
    public String getStringFromResources(int stringId) {
        return getString(stringId);
    }

    @Override
    public void setBtnChecked(boolean isChecked) {
        btnStart.setChecked(isChecked);
    }
}
