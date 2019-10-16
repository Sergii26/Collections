package com.practice.collectionsandmaps.model;

import android.util.Log;

import com.practice.collectionsandmaps.presenters.CollectionsPresenter;
import com.practice.collectionsandmaps.presenters.CollectionsPresenterCommunicator;
import com.practice.collectionsandmaps.presenters.MapsPresenter;
import com.practice.collectionsandmaps.presenters.MapsPresenterCommunicator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TasksFactory {

    private static ArrayList<Task> tasksForCollections;
    private static ArrayList<Task> tasksForMaps;
    private CollectionsSupplier collectionSupplier;
    private MapsSupplier mapsSupplier;

    public TasksFactory(int amountOfElements, int amountOfThreads, CollectionsPresenter collectionPresenter) {
        collectionSupplier = new CollectionsSupplier(amountOfElements, amountOfThreads);
        tasksForCollections = new ArrayList<>(21);
    }

    public TasksFactory(int amountOfElements, int amountOfThreads, MapsPresenter mapsPresenter) {
        mapsSupplier = new MapsSupplier(amountOfElements, amountOfThreads);
        tasksForMaps = new ArrayList<>(6);
    }

    public ArrayList<Task> getTasks(CollectionsPresenterCommunicator collectionsPresenterCommunicator){
        for (int i = 0; i < 7; i++) {
            Task arrayListTask = new Task(nameOfCollection(collectionSupplier.getArrayList()), i, collectionSupplier.getArrayList(), collectionsPresenterCommunicator);
            Task linkedListTask = new Task(nameOfCollection(collectionSupplier.getLinkedList()), i, collectionSupplier.getLinkedList(), collectionsPresenterCommunicator);
            Task copyOnWriteArrayListTask = new Task(nameOfCollection(collectionSupplier.getCopyOnWriteList()), i, collectionSupplier.getCopyOnWriteList(), collectionsPresenterCommunicator);
            tasksForCollections.add(arrayListTask);
            tasksForCollections.add(linkedListTask);
            tasksForCollections.add(copyOnWriteArrayListTask);
        }
        Log.d("MyLog", "in TasksFactory at getTasks() for collections. Amount of Tasks: " + String.valueOf((tasksForCollections.size())));
        return tasksForCollections;
    }

    public ArrayList<Task> getTasks(MapsPresenterCommunicator mapsPresenterCommunicator){
        for (int i = 7; i < 10; i++) {
            Task hashMapTask = new Task(nameOfCollection(mapsSupplier.getHashMap()), i, mapsSupplier.getHashMap(), mapsPresenterCommunicator);
            Task treeMapTask = new Task(nameOfCollection(mapsSupplier.getTreeMap()), i, mapsSupplier.getTreeMap(), mapsPresenterCommunicator);
            tasksForMaps.add(hashMapTask);
            tasksForMaps.add(treeMapTask);
        }
        Log.d("MyLog", "in TasksFactory getTasks() for maps. Amount of Tasks: " + String.valueOf((tasksForMaps.size())));
        return tasksForMaps;
    }

    public static ArrayList<Task> getEmptyTasks(MapsPresenterCommunicator mapsPresenterCommunicator){
        ArrayList<Task> emptyTasks = new ArrayList<>();
        for (int i = 7; i < 10; i++) {
            Task hashMapTask = new Task(TasksFactory.nameOfCollection(new HashMap<Integer, Integer>()), i, new HashMap<Integer, Integer>());
            hashMapTask.setTimeForTask("N/A ms ");
            Task treeMapTask = new Task(TasksFactory.nameOfCollection(new TreeMap<Integer, Integer>()), i, new TreeMap<Integer, Integer>());
            treeMapTask.setTimeForTask("N/A ms ");
            emptyTasks.add(hashMapTask);
            emptyTasks.add(treeMapTask);
        }
        return emptyTasks;
    }

    public static ArrayList<Task> getEmptyTasks(CollectionsPresenterCommunicator collectionsPresenterCommunicator){
        ArrayList<Task> emptyTasks = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            Task arrayListTask = new Task(TasksFactory.nameOfCollection(new ArrayList<Integer>()), i, new ArrayList<Integer>());
            arrayListTask.setTimeForTask("N/A ms ");
            Task linkedListTask = new Task(TasksFactory.nameOfCollection(new LinkedList<Integer>()), i, new ArrayList<Integer>());
            linkedListTask.setTimeForTask("N/A ms ");
            Task copyOnWriteArrayListTask = new Task(TasksFactory.nameOfCollection(new CopyOnWriteArrayList<Integer>()), i, new ArrayList<Integer>());
            copyOnWriteArrayListTask.setTimeForTask("N/A ms ");
            emptyTasks.add(arrayListTask);
            emptyTasks.add(linkedListTask);
            emptyTasks.add(copyOnWriteArrayListTask);
        }
        return emptyTasks;
    }

    public static String nameOfCollection(Collection<Integer> collection){
        String[] dividedName = collection.getClass().getName().split("\\.");
        return dividedName[dividedName.length - 1];
    }

    public static String nameOfCollection(Map<Integer, Integer> map){
        String[] dividedName = map.getClass().getName().split("\\.");
        return dividedName[dividedName.length - 1];
    }

    public static void doingCollectionsTasks(int amountOfThreads){
        ExecutorService service = Executors.newFixedThreadPool(amountOfThreads);
        for(int i = 0; i < tasksForCollections.size(); i++){
            final Task task = tasksForCollections.get(i);
            service.submit(new Runnable() {
                @Override
                public void run() {
                    task.doTask(task.getTask());
                    Log.d("MyLog", "at doingCollectionsTasks() Time for " + task.getTaskTitle() + task.getTimeForTask());
                }
            });
        }
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
