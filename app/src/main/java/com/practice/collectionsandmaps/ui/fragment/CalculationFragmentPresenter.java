package com.practice.collectionsandmaps.ui.fragment;

import android.util.Log;

import com.practice.collectionsandmaps.R;
import com.practice.collectionsandmaps.dto.CalculationResult;
import com.practice.collectionsandmaps.dto.TaskData;
import com.practice.collectionsandmaps.models.suppliers.TasksSupplier;
import com.practice.collectionsandmaps.models.workers.TimeCalculator;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class CalculationFragmentPresenter implements CalculationFragmentContract.Presenter {

    private TasksSupplier tasksSupplier;
    private TimeCalculator timeCalculator;
    private CalculationFragmentContract.FragmentView view;
    private boolean isValid;
    private final CompositeDisposable compositeDisposable = new CompositeDisposable();

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
            final List<TaskData> tasks = tasksSupplier.getTasks();
            compositeDisposable.add(Observable.fromIterable(new ArrayList<>(tasks))
            .subscribeOn(Schedulers.from(Executors.newFixedThreadPool(threads)))
            .flatMap((Function<TaskData, ObservableSource<CalculationResult>>) taskData -> {
                Log.d("MyLog", "in CalculationFragmentPresenter startCalculation() at flatMap() thread = " + Thread.currentThread().getName());
                taskData.fill(elements);
                timeCalculator.execAndSetupTime(taskData);
                return Observable.just(taskData.getResult());
            })
            .observeOn(AndroidSchedulers.mainThread())
            .doFinally(() -> {
                compositeDisposable.clear();
                if (view != null && view.isCheckedBtn()) {
                    view.setBtnChecked(false);
                    view.showToast(view.getStringFromResources(R.string.calculation_finished));
                }

            })
            .subscribe(calculationResult -> {
                if (view != null) {
                    view.setupResult(calculationResult);
                }
            }));
        }
    }

    public void stopCalculation(boolean showMsg) {
        if(compositeDisposable.size() == 0){
            return;
        }
        compositeDisposable.clear();
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



