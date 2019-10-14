package com.practice.collectionsandmaps.presenters;

import android.util.Log;

import com.practice.collectionsandmaps.fragments.FragmentView;
import com.practice.collectionsandmaps.model.Task;
import com.practice.collectionsandmaps.model.TasksFactory;

import java.util.ArrayList;

public class MapsPresenter implements MapsPresenterCommunicator {

    private TasksFactory tasksFactory;
    private FragmentView view;
    private ArrayList<Task> tasks;


    public MapsPresenter(FragmentView view) {
        this.view = view;
    }

    public void getTasksFromFactory(int amountOfElements, int amountOfThreads){
        Log.d("MyLog", "in MapsPresenter getTasksFromFactory()");
        tasksFactory = new TasksFactory(amountOfElements, amountOfThreads, this);
        tasks = tasksFactory.getTasks(this);
    }

    public ArrayList<Task> getEmptyTasksFromFactory(){
        TasksFactory factory = new TasksFactory();
        return factory.getEmptyTasks(this);
    }

    public void onDataChange(){
        Log.d("MyLog", "in MapsPresenter onDataChange()");
        view.updateData();
    }

    public void getData(){
        Log.d("MyLog", "in MapsPresenter getData()");
        view.showData(tasks);
    }
}
