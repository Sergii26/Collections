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
import com.practice.collectionsandmaps.dto.CalculationResult;
import com.practice.collectionsandmaps.dto.TaskData;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

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
    private Unbinder unbinder;

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
        unbinder = ButterKnife.bind(this, view);
        btnStart.setWidth((int) btnStart.getPaint().measureText(getString(R.string.btn_stop_state)) + btnStart.getPaddingLeft() + btnStart.getPaddingRight());
        btnStart.setOnCheckedChangeListener(this);
        rv.setAdapter(adapter);
        rv.setLayoutManager(new GridLayoutManager(view.getContext(), calculationFragmentPresenter.getCollectionsCount()));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_collections_and_maps, container, false);
        if(adapter.getItemCount() == 0){
                adapter.setTasks(calculationFragmentPresenter.getInitialResult());
        }
        return v;
    }

    public String getText(EditText et){
        Log.d("MyLog", "In CollectionsAndMapsFragment at  getText()");
        return et.getText().toString();
    }

    public void hideProgress(){
        Log.d("MyLog", "In CollectionsAndMapsFragment at  hideProgress()");
        adapter.hideProgress();
    }

    public void showProgress(){
        Log.d("MyLog", "In CollectionsAndMapsFragment at  showProgress()");
        adapter.showProgress();
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if(isChecked) {
            Log.d("MyLog", "In CollectionsAndMapsFragment at onCheckedChanged() \"true branch\"");
            calculationFragmentPresenter.startCalculation(getText(etAmountOfElements), getText(etAmountOfThreads));
        } else {
            Log.d("MyLog", "In CollectionsAndMapsFragment at onCheckedChanged() \"false branch\"");
            calculationFragmentPresenter.stopCalculation(true);
        }
    }

    @Override
    public void showData(List<TaskData> tasks) {
        Log.d("MyLog", "In CollectionsFragment at showData()");
        adapter.setTasks(tasks);
    }

    @Override
    public void showToast(String text) {
        Log.d("MyLog", "In CollectionsFragment at showToast() before showing" );
        mainHandler.post(() -> Toast.makeText(getContext(), text, Toast.LENGTH_SHORT).show());
        Log.d("MyLog", "In CollectionsFragment at showToast() after showing" );
    }

    @Override
    public String getStringFromResources(int stringId) {
        Log.d("MyLog", "In CollectionsFragment at getStringFromResources()");
        return getString(stringId);
    }

    @Override
    public void setBtnChecked(boolean isChecked) {
        Log.d("MyLog", "In CollectionsFragment at setBtnChecked()");
        btnStart.setChecked(isChecked);
    }

    @Override
    public void setThreadsError(String error) {
        Log.d("MyLog", "In CollectionsFragment at setThreadsError()");
        etAmountOfThreads.setError(error);
        btnStart.setChecked(false);
    }

    @Override
    public void setElementsError(String error) {
        Log.d("MyLog", "In CollectionsFragment at setElementsError()");
        etAmountOfElements.setError(error);
        btnStart.setChecked(false);
    }

    @Override
    public void setupResult(CalculationResult result) {
        Log.d("MyLog", "In CollectionsFragment at setupResult()");
        mainHandler.post(() -> adapter.updateItem(result));
    }

    @Override
    public void calculationStopped() {
        Log.d("MyLog", "In CollectionsFragment at calculationStopped()");
        mainHandler.post(() -> {
            setBtnChecked(false);
            hideProgress();

        });
    }

    @Override
    public void onDestroy() {
        Log.d("MyLog", "In CollectionsFragment at onDestroy()");
        calculationFragmentPresenter.stopCalculation(false);
        unbinder.unbind();
        super.onDestroy();
    }
}
