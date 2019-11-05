package com.practice.collectionsandmaps.ui.fragment;

import android.util.Log;

import com.practice.collectionsandmaps.R;
import com.practice.collectionsandmaps.dto.TaskData;
import com.practice.collectionsandmaps.models.suppliers.TasksSupplier;
import com.practice.collectionsandmaps.models.workers.TimeCalculator;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CalculationFragmentPresenter implements CalculationFragmentContract.Presenter {

    private TasksSupplier tasksSupplier;
    private TimeCalculator timeCalculator;
    private CalculationFragmentContract.FragmentView view;
    private boolean isValid;
    private ExecutorService executorPool;

    public CalculationFragmentPresenter(CalculationFragmentContract.FragmentView view, TasksSupplier tasksSupplier, TimeCalculator timeCalculator){
        isValid = true;
        this.view = view;
        this.tasksSupplier = tasksSupplier;
        this.timeCalculator = timeCalculator;
    }

    public int getCollectionsCount(){
        return tasksSupplier.getCollectionsCount();
    }

    public void startCalculation(String amountOfElements, String amountOfThreads){
        isValid = true;
        if(amountOfElements.isEmpty()) {
            view.setElementsError(view.getStringFromResources(R.string.elements_must_not_be_empty));
            isValid = false;
        } else if("0".equals(amountOfElements)){
            view.setElementsError(view.getStringFromResources(R.string.enter_elements));
            isValid = false;
        } else {
            view.setElementsError(null);
        }

        if(amountOfThreads.isEmpty()){
            view.setThreadsError(view.getStringFromResources(R.string.threads_must_not_be_empty));
            isValid = false;
        } else if("0".equals(amountOfThreads)){
            view.setThreadsError(view.getStringFromResources(R.string.enter_threads));
            isValid = false;
        } else {
            view.setThreadsError(null);
        }

        if (isValid) {
            view.setBtnChecked(true);
            view.showData(getInitialResult());
            view.showProgress();
            final int threads = Integer.parseInt(amountOfThreads);
            final int elements = Integer.parseInt(amountOfElements);
            stopCalculation(false);
            final List<TaskData> tasks = tasksSupplier.getTasks();
            executorPool = Executors.newFixedThreadPool(threads);
            for (TaskData td : new ArrayList<>(tasks)) {
                executorPool.submit(() -> {
                    td.fill(elements);
                    timeCalculator.execAndSetupTime(td);

                    tasks.remove(td);
                    if (view != null) {
                        view.setupResult(td.getResult());
                    }
                    Log.d("MyLog", "in CalculationFragmentPresenter startCalculation() in run(). tasks.size = " + tasks.size());
                    if (tasks.isEmpty()) {
                        Log.d("MyLog", "in CalculationFragmentPresenter startCalculation() in run(). tasks is Empty " + tasks.size());
                        if (view != null) {
                            Log.d("MyLog", "in CalculationFragmentPresenter startCalculation() in run(). tasks is Empty + view != null before toast" );
                            view.showToast(view.getStringFromResources(R.string.calculation_finished));
                            Log.d("MyLog", "in CalculationFragmentPresenter startCalculation() in run(). tasks is Empty + view != null " );
                        }
                        Log.d("MyLog", "in CalculationFragmentPresenter startCalculation() in run(). tasks is Empty AFTER view != null ");
                        stopCalculation(false);
                    }
                });
            }
        }
    }

    public void stopCalculation(boolean showMsg) {
        Log.d("MyLog", "in CalculationFragmentPresenter stopCalculation()");
        if (executorPool == null) {
            return;
        }
        executorPool.shutdownNow();
        executorPool = null;
        if (view != null) {
            view.calculationStopped();
            if (showMsg) {
                view.showToast(view.getStringFromResources(R.string.calculation_stopped));
            }
        }
    }

    public List<TaskData> getInitialResult() {
        Log.d("MyLog", "in CalculationFragmentPresenter getInitialResult()");
        return tasksSupplier.getInitialResult();
    }

}



