package com.practice.collectionsandmaps.ui.fragment;

import android.util.Log;

import com.practice.collectionsandmaps.R;
import com.practice.collectionsandmaps.dto.CalculationResult;
import com.practice.collectionsandmaps.dto.TaskData;
import com.practice.collectionsandmaps.models.suppliers.TasksSupplier;
import com.practice.collectionsandmaps.models.workers.TimeCalculator;

import java.util.List;
import java.util.concurrent.Executors;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class CalculationFragmentPresenter implements CalculationFragmentContract.Presenter {

    private TasksSupplier tasksSupplier;
    private TimeCalculator timeCalculator;
    private CalculationFragmentContract.FragmentView view;
    private boolean isValid;
    private final CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Inject
    public CalculationFragmentPresenter(CalculationFragmentContract.FragmentView view, TasksSupplier tasksSupplier, TimeCalculator timeCalculator) {
        isValid = true;
        this.view = view;
        this.tasksSupplier = tasksSupplier;
        this.timeCalculator = timeCalculator;
    }

    public int getCollectionsCount() {
        return tasksSupplier.getCollectionsCount();
    }

    public void startCalculation(String amountOfElements, String amountOfThreads) {
        isValid = true;
        if (amountOfElements.isEmpty()) {
            view.setElementsError(view.getStringFromResources(R.string.elements_must_not_be_empty));
            isValid = false;
        } else if ("0".equals(amountOfElements)) {
            view.setElementsError(view.getStringFromResources(R.string.enter_elements));
            isValid = false;
        } else {
            view.setElementsError(null);
        }

        if (amountOfThreads.isEmpty()) {
            view.setThreadsError(view.getStringFromResources(R.string.threads_must_not_be_empty));
            isValid = false;
        } else if ("0".equals(amountOfThreads)) {
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
            final Scheduler pool = Schedulers.from(Executors.newFixedThreadPool(threads));
            compositeDisposable.add(Observable.fromIterable(tasksSupplier.getTasks())
                    .flatMap((Function<TaskData, ObservableSource<CalculationResult>>) taskData -> {
                        Log.d("MyLogThreads", "in CalculationFragmentPresenter startCalculation() at flatMap() thread = "
                                + Thread.currentThread().getName());
                        return Observable.just(taskData)
                                .subscribeOn(pool)
                                .map(td -> {
                                    Log.d("MyLogThreads", "in CalculationFragmentPresenter startCalculation() at Map() thread = "
                                            + Thread.currentThread().getName());
                                    td.fill(elements);
                                    timeCalculator.execAndSetupTime(td);
                                    return td.getResult();
                                })
                                .observeOn(AndroidSchedulers.mainThread());
                    })
                    .doFinally(() -> {
                        Log.d("MyLogThreads", "in CalculationFragmentPresenter startCalculation() at doFinally() thread = "
                                + Thread.currentThread().getName());
                        stopCalculation(false);
                        if (view != null) {
                            view.setBtnChecked(false);
                            view.showToast(view.getStringFromResources(R.string.calculation_finished));
                        }
                    })
                    .subscribe(calculationResult -> {
                        Log.d("MyLogThreads", "in CalculationFragmentPresenter startCalculation() at subscribe() where result are settled thread = "
                                + Thread.currentThread().getName());
                        if (view != null) {
                            Log.d("MyLog", "in CalculationFragmentPresenter setupResult()" + calculationResult.getTimeForTask());
                            view.setupResult(calculationResult);
                        }
                    }));
        }
    }

    public void stopCalculation(boolean showMsg) {
        if (compositeDisposable.size() == 0) {
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



