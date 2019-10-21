package com.practice.collectionsandmaps.models.Suppliers;

import android.os.AsyncTask;
import android.util.Log;

import com.practice.collectionsandmaps.models.tasksFactories.CollectionsTasksFactory;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

public class CollectionsSupplier implements Supplier{
    private static List<Integer> arrayList = new ArrayList<>();
    private static List<Integer> linkedList = new LinkedList<>();
    private static List<Integer> copyOnWriteArrayList = new CopyOnWriteArrayList<>();
    private static int amountOfElements;
    private static int amountOfThreads;

    public CollectionsSupplier(){
        Log.d("MyLog", "in CollectionSupplier constructor. Amount of elements = " + amountOfElements + " ALL LISTS "
                + arrayList.size() + " " + linkedList.size() + " " + copyOnWriteArrayList.size());
    }

    public void setAmountOfElements(int amountOfElements) {
        CollectionsSupplier.amountOfElements = amountOfElements;
    }

    public void setAmountOfThreads(int amountOfThreads) {
        CollectionsSupplier.amountOfThreads = amountOfThreads;
    }

    public void fillSuppliedEntities(){
        clearSuppliedEntities();
        new CollectionsSupplier.FillListsTask().execute(amountOfElements);
    }

    public List<Integer> getArrayList() {
        return arrayList;
    }

    public List<Integer> getLinkedList() {
        return linkedList;
    }

    public List<Integer> getCopyOnWriteList() {
        return copyOnWriteArrayList;
    }

    @Override
    public void clearSuppliedEntities() {
        arrayList.clear();
        linkedList.clear();
        copyOnWriteArrayList.clear();
    }

    @Override
    public Map<Integer, Integer> getHashMap() {
        return null;
    }

    @Override
    public Map<Integer, Integer> getTreeMap() {
        return null;
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
            Log.d("MyLog", "in CollectionSupplier asyncTask. Amount of elements = " + amountOfElements + ": Arr - "
                    + arrayList.size() + "; Linked - " + linkedList.size() + "; CopyOnWrite - " + copyOnWriteArrayList.size());
            CollectionsTasksFactory.doingCollectionsTasks(amountOfThreads);
            return null;
        }
    }
}
