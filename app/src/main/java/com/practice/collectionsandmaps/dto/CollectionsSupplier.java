package com.practice.collectionsandmaps.dto;

import android.os.AsyncTask;
import android.util.Log;

import com.practice.collectionsandmaps.models.CollectionsTasksFactory;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.concurrent.CopyOnWriteArrayList;

public class CollectionsSupplier {
    private static ArrayList<Integer> arrayList = new ArrayList<>();
    private static LinkedList<Integer> linkedList = new LinkedList<>();
    private static CopyOnWriteArrayList<Integer> copyOnWriteArrayList = new CopyOnWriteArrayList<>();
    private static int amountOfElements;
    private static int amountOfThreads;

    public CollectionsSupplier(int amountOfElements, int amountOfThreads) {
        this.amountOfThreads = amountOfThreads;
        this.amountOfElements = amountOfElements;
        arrayList.clear();
        linkedList.clear();
        copyOnWriteArrayList.clear();
        new CollectionsSupplier.FillListsTask().execute(amountOfElements);
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
            CollectionsTasksFactory.doingCollectionsTasks(amountOfThreads);
            return null;
        }
    }
}
