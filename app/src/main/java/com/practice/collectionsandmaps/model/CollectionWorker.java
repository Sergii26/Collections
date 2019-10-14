package com.practice.collectionsandmaps.model;

import android.os.AsyncTask;
import android.util.Log;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class CollectionWorker {

    private static ArrayList<Integer> arrayList = new ArrayList<>();
    private static LinkedList<Integer> linkedList = new LinkedList<>();
    private static CopyOnWriteArrayList<Integer> copyOnWriteArrayList = new CopyOnWriteArrayList<>();
    private static int amountOfElements;
    private static int amountOfThreads;

    public CollectionWorker(int amountOfElements, int amountOfThreads) {
        this.amountOfThreads = amountOfThreads;
        this.amountOfElements = amountOfElements;
        arrayList.clear();
        linkedList.clear();
        copyOnWriteArrayList.clear();
        new FillListsTask().execute(amountOfElements);
        Log.d("MyLog", "in CollectionWorker constructor. Amount of elements = " + amountOfElements + " ALL LISTS "
                + arrayList.size() + " " + linkedList.size() + " " + copyOnWriteArrayList.size());
    }

    public ArrayList<Integer> getArrayList() {
        return arrayList;
    }

    public LinkedList<Integer> getLinkedList() {
        return linkedList;
    }

    public CopyOnWriteArrayList<Integer> getCopyOnWriteList() {
        return copyOnWriteArrayList;
    }

    public static double addingToStartTask(List<Integer> list) {
        Log.d("MyLog", "In addingToStartTask(List<Integer> list). For " + list.getClass().getName());
        double start = System.nanoTime();
        list.add(0, -1);
        return (System.nanoTime() - start) / 1000000;
    }

    public static double addingToMiddleTask(List<Integer> list) {
        Log.d("MyLog", "In addingToMiddleTask(List<Integer> list). For " + list.getClass().getName());
        double start = System.nanoTime();
        list.add(list.size() / 2, -2);
        return (System.nanoTime() - start) / 1000000;
    }

    public static double addingToEndTask(List<Integer> list) {
        Log.d("MyLog", "In addingToEndTask(List<Integer> list). For " + list.getClass().getName());
        if (list.getClass().getName().equals("java.util.LinkedList")) {
            Log.d("MyLog", "Is a Linked List! For " + list.getClass().getName());
            double start = System.nanoTime();
            ((LinkedList<Integer>) list).addLast(-3);
            return (System.nanoTime() - start) / 1000000;
        }
        Log.d("MyLog", "Is not a Linked List!. For " + list.getClass().getName());
        double start = System.nanoTime();
        list.add(list.size() - 1, -3);
        return (System.nanoTime() - start) / 1000000;
    }

    public static double searchTask(List<Integer> list) {
        Log.d("MyLog", "In searchTask(List<Integer> list). For " + list.getClass().getName());
        double start = System.nanoTime();
        list.indexOf(list.size() - 1);
        return (System.nanoTime() - start) / 1000000;
    }

    public static double removingFromStartTask(List<Integer> list) {
        Log.d("MyLog", "In removingFromStartTask(List<Integer> list). For " + list.getClass().getName());
        double start = System.nanoTime();
        list.remove(0);
        return (System.nanoTime() - start) / 1000000;
    }

    public static double removingFromMiddleTask(List<Integer> list) {
        Log.d("MyLog", "In removingFromMiddleTask(List<Integer> list). For " + list.getClass().getName());
        double start = System.nanoTime();
        list.remove(list.size() / 2);
        return (System.nanoTime() - start) / 1000000;
    }

    public static double removingFromEndTask(List<Integer> list) {
        Log.d("MyLog", "In removingFromEndTask(List<Integer> list). For " + list.getClass().getName());
        if (list.getClass().getName().equals("java.util.LinkedList")) {
            Log.d("MyLog", "Is a Linked List! For " + list.getClass().getName());
            double start = System.nanoTime();
            ((LinkedList<Integer>) list).removeLast();
            return (System.nanoTime() - start) / 1000000;
        }
        Log.d("MyLog", "Is not a Linked List!. For " + list.getClass().getName());
        double start = System.nanoTime();
        list.remove(list.size() - 1);
        return (System.nanoTime() - start) / 1000000;
    }

    static class FillListsTask extends AsyncTask<Integer, Void, Void> {

        @Override
        protected Void doInBackground(Integer... integers) {
            if (integers.length > 0) {
                for (int i = 0; i < integers[0]; i++) {
                    Integer integer = i;
                    linkedList.add(integer);
                }
                arrayList.addAll(linkedList);
                copyOnWriteArrayList.addAll(arrayList);
            }
            Log.d("MyLog", "in CollectionWorker asyncTask for copyOnWriteList. Amount of elements = " + amountOfElements + ": Arr - "
                    + arrayList.size() + "; Linked - " + linkedList.size() + "; CopyOnWrite - " + copyOnWriteArrayList.size());
                TasksFactory.doingCollectionsTasks(amountOfThreads);
            return null;
        }
    }
}

