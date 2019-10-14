package com.practice.collectionsandmaps.model;

import android.util.Log;

import com.practice.collectionsandmaps.presenters.CollectionsPresenterCommunicator;
import com.practice.collectionsandmaps.presenters.MapsPresenterCommunicator;

import java.util.List;
import java.util.Map;

public class Task {

    public static final String ADDING_TO_START_TASK_TITLE = "Adding to start in";
    public static final String ADDING_TO_MIDDLE_TASK_TITLE = "Adding to middle in ";
    public static final String ADDING_TO_END_TASK_TITLE = "Adding to end in ";
    public static final String REMOVING_FROM_START_TASK_TITLE = "Removing from start in ";
    public static final String REMOVING_FROM_MIDDLE_TASK_TITLE = "Removing from middle in ";
    public static final String REMOVING_FROM_END_TASK_TITLE = "Removing from end in ";

    public static final String SEARCH_TASK_TITLE = "Search in ";

    public static final String ADDING_TASK_TITLE = "Adding to ";
    public static final String REMOVING_TASK_TITLE = "Removing from ";

    public static final int ADDING_TO_START = 0;
    public static final int ADDING_TO_MIDDLE = 1;
    public static final int ADDING_TO_END = 2;
    public static final int SEARCH = 3;
    public static final int REMOVING_FROM_START = 4;
    public static final int REMOVING_FROM_MIDDLE = 5;
    public static final int REMOVING_FROM_END = 6;

    public static final int ADDING = 7;
    public static final int SEARCH_IN_MAP = 8;
    public static final int REMOVING = 9;



    private String nameOfTask;
    private String timeForTask;
    private int task;
    private List<Integer> list;
    private Map<Integer, Integer> map;
    private CollectionsPresenterCommunicator collectionsPresenterCommunicator;
    private MapsPresenterCommunicator mapsPresenterCommunicator;

    public Task(String nameOfCollectionOrMap, int task, List<Integer> list, CollectionsPresenterCommunicator collectionsPresenterCommunicator) {
        this.collectionsPresenterCommunicator = collectionsPresenterCommunicator;
        this.list = list;
        this.task = task;
        this.nameOfTask = defineTask(nameOfCollectionOrMap, task);
        timeForTask = "N/A ms";
    }

    public Task(String nameOfCollectionOrMap, int task, Map<Integer, Integer> map, MapsPresenterCommunicator mapsPresenterCommunicator) {
        this.mapsPresenterCommunicator = mapsPresenterCommunicator;
        this.map = map;
        this.task = task;
        this.nameOfTask = defineTask(nameOfCollectionOrMap, task);
        timeForTask = "N/A ms";
    }

    public Task(String nameOfCollectionOrMap, int task, List<Integer> list) {
        this.list = list;
        this.task = task;
        this.nameOfTask = defineTask(nameOfCollectionOrMap, task);
        timeForTask = "N/A ms";
    }

    public Task(String nameOfCollectionOrMap, int task, Map<Integer, Integer> map) {
        this.map = map;
        this.task = task;
        this.nameOfTask = defineTask(nameOfCollectionOrMap, task);
        timeForTask = "N/A ms";
    }



    public int getTask() {
        return task;
    }

    public void setTimeForTask(String timeForTask) {
        this.timeForTask = timeForTask;
    }

    public String getTaskTitle() {
        return nameOfTask;
    }

    public String getTimeForTask() {
        return timeForTask;
    }

    public String defineTask(String nameOfCollection, int task){
        Log.d("MyLog", "In Task.defineTask()");
        String name = null;
        switch(task){
            case ADDING_TO_START:
                name = ADDING_TO_START_TASK_TITLE + nameOfCollection + ":";
                break;
            case ADDING_TO_MIDDLE:
                name = ADDING_TO_MIDDLE_TASK_TITLE + nameOfCollection + ":";
                break;
            case ADDING_TO_END:
                name = ADDING_TO_END_TASK_TITLE + nameOfCollection + ":";
                break;
            case SEARCH:
                name = SEARCH_TASK_TITLE + nameOfCollection + ":";
                break;
            case REMOVING_FROM_START:
                name = REMOVING_FROM_START_TASK_TITLE + nameOfCollection + ":";
                break;
            case REMOVING_FROM_MIDDLE:
                name = REMOVING_FROM_MIDDLE_TASK_TITLE + nameOfCollection + ":";
                break;
            case REMOVING_FROM_END:
                name = REMOVING_FROM_END_TASK_TITLE + nameOfCollection + ":";
                break;
            case ADDING:
                name = ADDING_TASK_TITLE + nameOfCollection + ":";
                break;
            case SEARCH_IN_MAP:
                name = SEARCH_TASK_TITLE + nameOfCollection + ":";
                break;
            case REMOVING:
                name = REMOVING_TASK_TITLE + nameOfCollection + ":";
                break;
        }
        return name;
    }

    public void doTask(int task){
        double timeForTask = 0;
        switch(task){
            case ADDING_TO_START:
                timeForTask = CollectionWorker.addingToStartTask(list);
                break;
            case ADDING_TO_MIDDLE:
                timeForTask = CollectionWorker.addingToMiddleTask(list);
                break;
            case ADDING_TO_END:
                timeForTask = CollectionWorker.addingToEndTask(list);
                break;
            case SEARCH:
                timeForTask = CollectionWorker.searchTask(list);
                break;
            case REMOVING_FROM_START:
                timeForTask = CollectionWorker.removingFromStartTask(list);
                break;
            case REMOVING_FROM_MIDDLE:
                timeForTask = CollectionWorker.removingFromMiddleTask(list);
                break;
            case REMOVING_FROM_END:
                timeForTask = CollectionWorker.removingFromEndTask(list);
                break;
            case ADDING:
                timeForTask = MapWorker.adding(map);
                break;
            case SEARCH_IN_MAP:
                timeForTask = MapWorker.search(map);
                break;
            case REMOVING:
                timeForTask = MapWorker.removing(map);
                break;
        }
        Log.d("MyLog", "In Task.doTask() - before setTimeForTask()");
        setTimeForTask(timeForTask + " ms");
        if(task <= 6) {
            collectionsPresenterCommunicator.onDataChange();
        } else {
            mapsPresenterCommunicator.onDataChange();
        }
    }
}
