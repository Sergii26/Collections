package com.practice.collectionsandmaps.models.tasksFactories;

import android.util.Log;

import com.practice.collectionsandmaps.dto.Task;
import com.practice.collectionsandmaps.models.Suppliers.Supplier;
import com.practice.collectionsandmaps.ui.fragment.PresenterCommunicator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MapsTasksFactory implements TasksFactory {

    private static final List<Task> tasksForMaps = new ArrayList<>();
    private List<Task> emptyTasks;
    private Supplier mapsSupplier;
    private final int SPAN_COUNT = 2;

    @Override
    public List<Task> getTasks(Supplier supplier, PresenterCommunicator presenterCommunicator) {
        tasksForMaps.clear();
        mapsSupplier = supplier;
        for (int i = 7; i < 10; i++) {
            Task hashMapTask = new Task(nameOfCollection(mapsSupplier.getHashMap()), i, mapsSupplier.getHashMap(), presenterCommunicator);
            Task treeMapTask = new Task(nameOfCollection(mapsSupplier.getTreeMap()), i, mapsSupplier.getTreeMap(), presenterCommunicator);
            tasksForMaps.add(hashMapTask);
            tasksForMaps.add(treeMapTask);
        }
        Log.d("MyLog", "in CollectionsTasksFactory getTasks() for maps. Amount of Tasks: " + String.valueOf((tasksForMaps.size())));
        return tasksForMaps;
    }

    @Override
    public int getSpanCount() {
        return SPAN_COUNT;
    }

    public List<Task> getEmptyTasks(PresenterCommunicator presenterCommunicator){
        emptyTasks = new ArrayList<>();
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
