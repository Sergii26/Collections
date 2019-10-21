package com.practice.collectionsandmaps.models.tasksFactories;

import android.util.Log;

import com.practice.collectionsandmaps.dto.Task;
import com.practice.collectionsandmaps.models.Suppliers.Supplier;
import com.practice.collectionsandmaps.ui.fragment.PresenterCommunicator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CollectionsTasksFactory implements TasksFactory {

    private static final List<Task> tasksForCollections = new ArrayList<>();
    private List<Task> emptyTasks;
    private Supplier collectionSupplier;
    private final int SPAN_COUNT = 3;

    public List<Task> getTasks(Supplier supplier, PresenterCommunicator presenterCommunicator){
        tasksForCollections.clear();
        collectionSupplier = supplier;
        for (int i = 0; i < 7; i++) {
            Task arrayListTask = new Task(nameOfCollection(collectionSupplier.getArrayList()), i, collectionSupplier.getArrayList(), presenterCommunicator);
            Task linkedListTask = new Task(nameOfCollection(collectionSupplier.getLinkedList()), i, collectionSupplier.getLinkedList(), presenterCommunicator);
            Task copyOnWriteArrayListTask = new Task(nameOfCollection(collectionSupplier.getCopyOnWriteList()), i, collectionSupplier.getCopyOnWriteList(), presenterCommunicator);
            tasksForCollections.add(arrayListTask);
            tasksForCollections.add(linkedListTask);
            tasksForCollections.add(copyOnWriteArrayListTask);
        }
        Log.d("MyLog", "in CollectionsTasksFactory at getTasks() for collections. Amount of Tasks: " + String.valueOf((tasksForCollections.size())));
        return tasksForCollections;
    }

    @Override
    public int getSpanCount() {
        return SPAN_COUNT;
    }

    public List<Task> getEmptyTasks(PresenterCommunicator presenterCommunicator){
        emptyTasks = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            Task arrayListTask = new Task(CollectionsTasksFactory.nameOfCollection(new ArrayList<Integer>()), i, new ArrayList<Integer>());
            arrayListTask.setTimeForTask("N/A ms ");
            Task linkedListTask = new Task(CollectionsTasksFactory.nameOfCollection(new LinkedList<Integer>()), i, new ArrayList<Integer>());
            linkedListTask.setTimeForTask("N/A ms ");
            Task copyOnWriteArrayListTask = new Task(CollectionsTasksFactory.nameOfCollection(new CopyOnWriteArrayList<Integer>()), i, new ArrayList<Integer>());
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
}
