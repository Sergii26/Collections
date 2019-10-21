package com.practice.collectionsandmaps.ui.fragment;

import android.util.Log;

import com.practice.collectionsandmaps.R;
import com.practice.collectionsandmaps.dto.Task;
import com.practice.collectionsandmaps.models.Suppliers.CollectionsSupplier;
import com.practice.collectionsandmaps.models.Suppliers.MapsSupplier;
import com.practice.collectionsandmaps.models.Suppliers.Supplier;
import com.practice.collectionsandmaps.models.tasksFactories.CollectionsTasksFactory;
import com.practice.collectionsandmaps.models.tasksFactories.MapsTasksFactory;
import com.practice.collectionsandmaps.models.tasksFactories.TasksFactory;

import java.util.ArrayList;
import java.util.List;

public class CalculationFragmentPresenter implements PresenterCommunicator {

    private TasksFactory tasksFactory;
    private Supplier supplier;
    private FragmentView view;
    private List<Task> tasks;
    private List<Task> tasksAfterStop;
    private int indication;
    private boolean stopCalculation;
    private boolean isValid;

    public CalculationFragmentPresenter(FragmentView view, int indication) {
        isValid = false;
        this.view = view;
        this.indication = indication;
        if(isCollections()){
            tasksFactory = new CollectionsTasksFactory();
            supplier = new CollectionsSupplier();
        } else {
            tasksFactory = new MapsTasksFactory();
            supplier = new MapsSupplier();
        }
    }

    public boolean isCollections(){
        if(this.indication == FragmentsIndication.COLLECTION){
            return true;
        }
        return false;
    }

    public void startCalculation(String amountOfElements, String amountOfThreads){
        stopCalculation = false;
        getData(amountOfElements, amountOfThreads);
    }

    public void stopCalculation(){
        stopCalculation = true;
        getDataAfterStop();
        stopCalculation = false;
    }

    public boolean isDefaultTime(){
        for(Task task: tasks){
            if(task.getTimeForTask().equals(Task.DEFAULT_TIME)){
                return false;
            }
        }
        return true;
    }

    public boolean isStopped(){
        return !isDefaultTime();
    }

    public int getSpanCount(){
        return tasksFactory.getSpanCount();
    }

    public void getTasksFromFactory(int amountOfElements, int amountOfThreads){
            Log.d("MyLog", "in CalculationFragmentPresenter getTasksFromFactory()");
            tasks = tasksFactory.getTasks(supplier, this);
    }

    public List<Task> getEmptyTasksFromFactory(){
            Log.d("MyLog", "in Presenter getEmptyTasksFromFactory()");
            return tasksFactory.getEmptyTasks(this);
    }

    public void onDataChange(){
        Log.d("MyLog", "in CalculationFragmentPresenter onDataChange()");
        if(!stopCalculation) {
            view.updateData();
        }
    }

    public void getDataAfterStop(){
        Log.d("MyLog", "in CalculationFragmentPresenter getDataAfterStop() stopCalculation = " + stopCalculation);
        tasksAfterStop = new ArrayList<>();
        for(Task task: tasks) {
            tasksAfterStop.add(new Task(task));
        }
        view.showData(tasksAfterStop);
    }

    public void getData(String enteredElements, String enteredThreads){
       Log.d("MyLog", "in CalculationFragmentPresenter getData()");
       isValid = false;
       if(enteredElements.isEmpty()){
           view.showError(view.getStringFromResources(R.string.elements_must_not_be_empty));
           isValid = true;
       }
       if(enteredThreads.isEmpty()) {
           view.showError(view.getStringFromResources(R.string.threads_must_not_be_empty));
           isValid = true;
       }
       if(isValid) {
           isValid = false;
       } else {
           final int elementsInt = Integer.parseInt(enteredElements);
           final int threadsInt = Integer.parseInt(enteredThreads);
           if (elementsInt == 0) {
               view.showError(view.getStringFromResources(R.string.enter_elements));
               isValid = true;
           }
           if (threadsInt == 0) {
               view.showError(view.getStringFromResources(R.string.enter_threads));
               isValid = true;
           }
           if (isValid) {
               isValid = false;
           } else {
               supplier.setAmountOfElements(Integer.parseInt(enteredElements));
               supplier.setAmountOfThreads(Integer.parseInt(enteredThreads));
               supplier.fillSuppliedEntities();
               getTasksFromFactory(Integer.parseInt(enteredElements), Integer.parseInt(enteredThreads));
               view.showData(tasks);
           }
       }
    }
}



