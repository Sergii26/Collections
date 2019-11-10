package com.practice.collectionsandmaps.ui.fragment;

import android.util.Log;

import com.practice.collectionsandmaps.R;
import com.practice.collectionsandmaps.dto.TaskData;
import com.practice.collectionsandmaps.models.suppliers.TasksSupplier;
import com.practice.collectionsandmaps.models.workers.TimeCalculator;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class CalculationFragmentPresenter implements CalculationFragmentContract.Presenter {

    private TasksSupplier tasksSupplier;
    private TimeCalculator timeCalculator;
    private CalculationFragmentContract.FragmentView view;
    private boolean isValid;
    private CompositeDisposable compositeDisposable;

    @Inject
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
            compositeDisposable = new CompositeDisposable();
            Disposable disposable = Observable.just(new ArrayList<>(tasks))
            .flatMap((Function<List<TaskData>, ObservableSource<TaskData>>) Observable::fromIterable)
            .subscribeOn(Schedulers.from(Executors.newFixedThreadPool(threads)))
            .subscribe(taskData -> {
                tasks.remove(taskData);
                taskData.fill(elements);
                timeCalculator.execAndSetupTime(taskData);
                if (view != null) {
                    view.setupResult(taskData.getResult());
                }
                if (tasks.isEmpty()) {
                    if (view != null) {
                        view.showToast(view.getStringFromResources(R.string.calculation_finished));
                    }
                    stopCalculation(false);
                }
            });
            compositeDisposable.add(disposable);
        }
    }

    public void stopCalculation(boolean showMsg) {
        Log.d("MyLog", "in CalculationFragmentPresenter stopCalculation()");
        if(compositeDisposable == null){
            return;
        }
        compositeDisposable.dispose();
        compositeDisposable = null;
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



