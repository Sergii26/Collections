package com.practice.collectionsandmaps.ui.fragment;

import android.util.Log;

import com.practice.collectionsandmaps.models.MapsTasksFactory;
import com.practice.collectionsandmaps.dto.Task;
import com.practice.collectionsandmaps.models.CollectionsTasksFactory;

import java.util.ArrayList;

public class CalculationFragmentPresenter implements PresenterCommunicator {

    private CollectionsTasksFactory collectionsTasksFactory;
    private MapsTasksFactory mapsTasksFactory;
    private FragmentView view;
    private ArrayList<Task> tasks;
    private int indication;


    public CalculationFragmentPresenter(FragmentView view, int indication) {
        this.view = view;
        this.indication = indication;
    }

    public boolean isCollections(){
        if(this.indication == FragmentsIndication.COLLECTION){
            return true;
        }
        return false;
    }

    public int getSpanCount(){
        return isCollections() ? 3 : 2;
    }

    public boolean inputValidation(String amountOfElements, String amountOfThreads){
        return amountOfElements.length() > 0 && amountOfThreads.length() > 0;
    }

    public void getTasksFromFactory(int amountOfElements, int amountOfThreads){
        if(isCollections()){
            Log.d("MyLog", "in CalculationFragmentPresenter getTasksFromFactory() for COLLECTION");
            collectionsTasksFactory = new CollectionsTasksFactory(amountOfElements, amountOfThreads);
            tasks = collectionsTasksFactory.getTasks(this);
        } else {
            Log.d("MyLog", "in CalculationFragmentPresenter getTasksFromFactory() for MAP");
            mapsTasksFactory = new MapsTasksFactory(amountOfElements, amountOfThreads);
            tasks = mapsTasksFactory.getTasks(this);
        }
    }

    public ArrayList<Task> getEmptyTasksFromFactory(){
        if(isCollections()){
            Log.d("MyLog", "in Presenter getEmptyTasksFromFactory() FOR COLLECTION");
            return CollectionsTasksFactory.getEmptyTasks(this);
        }
        Log.d("MyLog", "in Presenter getEmptyTasksFromFactory() FOR MAP");
        return MapsTasksFactory.getEmptyTasks(this);
    }

    public void onDataChange(){
        Log.d("MyLog", "in CalculationFragmentPresenter onDataChange()");
        view.updateData();
    }

    public void getData(String enteredElements, String enteredThreads){
        Log.d("MyLog", "in CalculationFragmentPresenter getData()");
        if(enteredElements.trim().length() > 0 && enteredThreads.trim().length() > 0
                && Integer.parseInt(enteredThreads) > 0 && Integer.parseInt(enteredElements) > 0){
            getTasksFromFactory(Integer.parseInt(enteredElements), Integer.parseInt(enteredThreads));
            view.showData(tasks);
        } else {
            view.showError();
        }
    }
}


