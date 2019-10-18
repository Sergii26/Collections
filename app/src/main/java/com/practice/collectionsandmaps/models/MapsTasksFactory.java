package com.practice.collectionsandmaps.models;

import android.util.Log;

import com.practice.collectionsandmaps.dto.MapsSupplier;
import com.practice.collectionsandmaps.dto.Task;
import com.practice.collectionsandmaps.ui.fragment.PresenterCommunicator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MapsTasksFactory {

    private static ArrayList<Task> tasksForMaps;
    private MapsSupplier mapsSupplier;

    public MapsTasksFactory(int amountOfElements, int amountOfThreads) {
        mapsSupplier = new MapsSupplier(amountOfElements, amountOfThreads);
        tasksForMaps = new ArrayList<>(6);
    }

    public ArrayList<Task> getTasks(PresenterCommunicator presenterCommunicator){
        for (int i = 7; i < 10; i++) {
            Task hashMapTask = new Task(nameOfCollection(mapsSupplier.getHashMap()), i, mapsSupplier.getHashMap(), presenterCommunicator);
            Task treeMapTask = new Task(nameOfCollection(mapsSupplier.getTreeMap()), i, mapsSupplier.getTreeMap(), presenterCommunicator);
            tasksForMaps.add(hashMapTask);
            tasksForMaps.add(treeMapTask);
        }
        Log.d("MyLog", "in CollectionsTasksFactory getTasks() for maps. Amount of Tasks: " + String.valueOf((tasksForMaps.size())));
        return tasksForMaps;
    }

    public static ArrayList<Task> getEmptyTasks(PresenterCommunicator presenterCommunicator){
        ArrayList<Task> emptyTasks = new ArrayList<>();
        for (int i = 7; i < 10; i++) {
            Task hashMapTask = new Task(MapsTasksFactory.nameOfCollection(new HashMap<Integer, Integer>()), i, new HashMap<Integer, Integer>());
            hashMapTask.setTimeForTask("N/A ms ");
            Task treeMapTask = new Task(MapsTasksFactory.nameOfCollection(new TreeMap<Integer, Integer>()), i, new TreeMap<Integer, Integer>());
            treeMapTask.setTimeForTask("N/A ms ");
            emptyTasks.add(hashMapTask);
            emptyTasks.add(treeMapTask);
        }
        return emptyTasks;
    }

    public static String nameOfCollection(Map<Integer, Integer> map){
        String[] dividedName = map.getClass().getName().split("\\.");
        return dividedName[dividedName.length - 1];
    }

    public static void doingMapsTasks(int amountOfThreads){
        ExecutorService service = Executors.newFixedThreadPool(amountOfThreads);
        for(int i = 0; i < tasksForMaps.size(); i++){
            final Task task = tasksForMaps.get(i);
            service.submit(new Runnable() {
                @Override
                public void run() {
                    task.doTask(task.getTask());
                    Log.d("MyLog", "at doingCollectionsTasks() Time for " + task.getTaskTitle() + task.getTimeForTask());
                }
            });
        }
    }

}
