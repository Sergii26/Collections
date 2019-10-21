package com.practice.collectionsandmaps.models.Suppliers;

import android.os.AsyncTask;
import android.util.Log;

import com.practice.collectionsandmaps.models.tasksFactories.MapsTasksFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class MapsSupplier implements Supplier{

    private static int amountOfElements;
    private static int amountOfThreads;
    private static Map<Integer, Integer> hashMap = new HashMap<>();
    private static Map<Integer, Integer> treeMap = new TreeMap<>();

    public MapsSupplier() {
        Log.d("MyLog", "in MapsSupplier constructor. Amount of elements = " + amountOfElements + " ALL MAPS "
                + hashMap.size() + " " + treeMap.size());
    }

    public Map<Integer, Integer> getHashMap() {
        return hashMap;
    }

    public Map<Integer, Integer> getTreeMap() {
        return treeMap;
    }

    @Override
    public void setAmountOfElements(int amountOfElements) {
        MapsSupplier.amountOfElements = amountOfElements;
    }

    @Override
    public void setAmountOfThreads(int amountOfThreads) {
        MapsSupplier.amountOfThreads = amountOfThreads;
    }

    @Override
    public void fillSuppliedEntities() {
        clearSuppliedEntities();
        new MapsSupplier.FillMapsTask().execute(amountOfElements);
    }

    @Override
    public List<Integer> getArrayList() {
        return null;
    }

    @Override
    public List<Integer> getLinkedList() {
        return null;
    }

    @Override
    public List<Integer> getCopyOnWriteList() {
        return null;
    }

    @Override
    public void clearSuppliedEntities() {
        hashMap.clear();
        treeMap.clear();
    }

    static class FillMapsTask extends AsyncTask<Integer, Void, Void> {

        @Override
        protected Void doInBackground(Integer... integers) {
            hashMap = new HashMap<>(integers[0]);
            if (integers.length > 0) {
                for (int i = 0; i < integers[0]; i++) {
                    Integer integer = i;
                    hashMap.put(integer, integer);
                }
                treeMap.putAll(hashMap);
            }
            Log.d("MyLog", "in CollectionSupplier asyncTask. Amount of elements = " + amountOfElements + ": HashMap - "
                    + hashMap.size() + "; TreeMap - " + treeMap.size() );
            MapsTasksFactory.doingMapsTasks(amountOfThreads);
            return null;
        }
    }
}
