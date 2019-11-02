package com.practice.collectionsandmaps.ui.fragment;

import android.util.Log;

import com.practice.collectionsandmaps.R;
import com.practice.collectionsandmaps.dto.TaskData;
import com.practice.collectionsandmaps.models.factories.TasksFactory;
import com.practice.collectionsandmaps.models.suppliers.TasksSupplier;
import com.practice.collectionsandmaps.models.workers.TimeCalculator;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CalculationFragmentPresenter {

    private TasksFactory tasksFactory;
    private TasksSupplier tasksSupplier;
    private TimeCalculator timeCalculator;
    private FragmentView view;
    private List<TaskData> tasks;
    private boolean isValid;
    private ExecutorService executorPool;

    public CalculationFragmentPresenter(FragmentView view, TasksSupplier tasksSupplier, TasksFactory tasksFactory, TimeCalculator timeCalculator){
        isValid = true;
        this.view = view;
        this.tasksSupplier = tasksSupplier;
        this.tasksFactory = tasksFactory;
        this.timeCalculator = timeCalculator;
    }

    public void startCalculation(String amountOfElements, String amountOfThreads){
        getData(amountOfElements, amountOfThreads);
        if(isValid) {
            view.showProgress();
            doTasks(Integer.parseInt(amountOfThreads));
        } else {
            view.hideProgress();
        }
    }

    public void stopCalculation(){
        stopTasks();
        showDataAfterStop();
        view.setBtnChecked(false);
    }

    public void doTasks(int amountOfThreads){
        executorPool = Executors.newFixedThreadPool(amountOfThreads);
        for(int i = 0; i < tasks.size(); i++){
            final TaskData task = tasks.get(i);
            executorPool.submit(() -> {
                timeCalculator.execAndSetupTime(task);
                Log.d("MyLog", "at doingCollectionsTasks() Time for " + task.getNameOfTask() + task.getTimeForTask());
                task.setShowProgress(false);
                onDataChange();
            });
        }
    }

    public void stopTasks(){
        executorPool.shutdownNow();
        executorPool = null;
    }

    public boolean isNotDefaultTime(){
        for(TaskData task: tasks){
            if(task.isDefaultTime()){
                return false;
            }
        }
        return true;
    }

    public int getCollectionsCount(){
        return tasksFactory.getCollectionsCount();
    }

    public List<TaskData> getTasksFromFactory(){
        Log.d("MyLog", "in CalculationFragmentPresenter getTasksFromFactory()");
        tasks = tasksFactory.getTasks(tasksSupplier);
        return tasks;
    }

    public void onDataChange(){
        Log.d("MyLog", "in CalculationFragmentPresenter onDataChange()");
        view.updateData();
    }

    public void showDataAfterStop(){
        view.showData(tasksFactory.commitTasks(tasks));
    }

    public void getData(String enteredElements, String enteredThreads){
       Log.d("MyLog", "in CalculationFragmentPresenter getData()");
       isValid = true;
       if(enteredElements.isEmpty()){
           view.showError(view.getStringFromResources(R.string.elements_must_not_be_empty));
           isValid = false;
           Log.d("MyLog", "in CalculationFragmentPresenter getData() error branch isValid = " + isValid);
       }
       if(enteredThreads.isEmpty()) {
           view.showError(view.getStringFromResources(R.string.threads_must_not_be_empty));
           isValid = false;
       }
       if(isValid) {
           final int elementsInt = Integer.parseInt(enteredElements);
           final int threadsInt = Integer.parseInt(enteredThreads);
           if (elementsInt == 0) {
               view.showError(view.getStringFromResources(R.string.enter_elements));
               isValid = false;
           }
           if (threadsInt == 0) {
               view.showError(view.getStringFromResources(R.string.enter_threads));
               isValid = false;
           }
           if (isValid) {
               tasksSupplier.setAmountOfElements(Integer.parseInt(enteredElements));
               tasksSupplier.fillSuppliedEntities();
               view.showData(getTasksFromFactory());
           }
       }
    }
}



