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
    private int indicator;
    private final Handler  mainHandler = new Handler(Looper.getMainLooper());
    private final Runnable myRunnable = new Runnable() {
        @Override
        public void run() {
            Log.d("MyLog", "In CollectionsFragment at updateData() calculation is running");
            adapter.notifyDataSetChanged();
            if(calculationFragmentPresenter.isDefaultTime()){
                Log.d("MyLog", "In CollectionsFragment at updateData() calculation is running isDefaultTime() = "
                        + calculationFragmentPresenter.isDefaultTime());
                btnStart.setChecked(false);
            }
        }
    };

    public CollectionsAndMapsFragment() {
    }

    public static CollectionsAndMapsFragment newInstance(int indicator) {
        final Bundle b = new Bundle();
        b.putInt(FragmentsIndication.KEY_INDICATOR, indicator);
        final CollectionsAndMapsFragment f = new CollectionsAndMapsFragment();
        f.setArguments(b);
        return f;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        if(getArguments() != null){
            indicator = getArguments().getInt(FragmentsIndication.KEY_INDICATOR);
        }
        calculationFragmentPresenter = new CalculationFragmentPresenter(this, indicator);
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
        if(adapter.getItemCount() == 0){
                adapter.setTasks(calculationFragmentPresenter.getEmptyTasksFromFactory());
        }
        btnStart.setWidth((int) btnStart.getPaint().measureText(getString(R.string.btn_stop_state)) + btnStart.getPaddingLeft() + btnStart.getPaddingRight());
        btnStart.setOnCheckedChangeListener(this);
        return v;
    }

    public String getText(EditText et){
        return et.getText().toString();
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if(isChecked) {
            Log.d("MyLog", "In CollectionsFragment at onCheckedChanged() \"true branch\"");
            adapter.setStopCalculation(false);
            calculationFragmentPresenter.startCalculation(getText(etAmountOfElements), getText(etAmountOfThreads));
        } else {
            Log.d("MyLog", "In CollectionsFragment at onCheckedChanged() \"false branch\"");
            if(calculationFragmentPresenter.isStopped()) {
                adapter.setStopCalculation(true);
                calculationFragmentPresenter.stopCalculation();
                btnStart.setChecked(false);
            }
        }
    }

    @Override
    public void showData(List<Task> tasks) {
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
}
