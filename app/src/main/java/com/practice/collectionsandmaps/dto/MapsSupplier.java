package com.practice.collectionsandmaps.dto;

import android.os.AsyncTask;
import android.util.Log;

import com.practice.collectionsandmaps.models.MapsTasksFactory;

import java.util.HashMap;
import java.util.TreeMap;

public class MapsSupplier {

    private static int amountOfElements;
    private static int amountOfThreads;
    private static HashMap<Integer, Integer> hashMap = new HashMap<>();
    private static TreeMap<Integer, Integer> treeMap = new TreeMap<>();

    public MapsSupplier(int amountOfElements, int amountOfThreads) {
        this.amountOfThreads = amountOfThreads;
        this.amountOfElements = amountOfElements;
        hashMap.clear();
        treeMap.clear();
        new MapsSupplier.FillMapsTask().execute(amountOfElements);
        Log.d("MyLog", "in MapWorker constructor. Amount of elements = " + amountOfElements + " ALL MAPS "
                + hashMap.size() + " " + treeMap.size());
    }

    public static HashMap<Integer, Integer> getHashMap() {
        return hashMap;
    }

    public static TreeMap<Integer, Integer> getTreeMap() {
        return treeMap;
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
            Log.d("MyLog", "in CollectionWorker asyncTask for copyOnWriteList. Amount of elements = " + amountOfElements + ": HashMap - "
                    + hashMap.size() + "; TreeMap - " + treeMap.size() );
            MapsTasksFactory.doingMapsTasks(amountOfThreads);
            return null;
        }
    }
}
