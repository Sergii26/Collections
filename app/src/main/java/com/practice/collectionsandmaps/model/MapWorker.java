package com.practice.collectionsandmaps.model;

import android.os.AsyncTask;
import android.util.Log;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class MapWorker {

    private static int amountOfElements;
    private static int amountOfThreads;
    private static HashMap<Integer, Integer> hashMap = new HashMap<>();
    private static TreeMap<Integer, Integer> treeMap = new TreeMap<>();

    public MapWorker(int amountOfElements, int amountOfThreads) {
        this.amountOfThreads = amountOfThreads;
        this.amountOfElements = amountOfElements;
        hashMap.clear();
        treeMap.clear();
        new MapWorker.FillMapsTask().execute(amountOfElements);
        Log.d("MyLog", "in MapWorker constructor. Amount of elements = " + amountOfElements + " ALL MAPS "
                + hashMap.size() + " " + treeMap.size());
    }

    public static HashMap<Integer, Integer> getHashMap() {
        return hashMap;
    }

    public static TreeMap<Integer, Integer> getTreeMap() {
        return treeMap;
    }

    public static double adding(Map<Integer, Integer> map) {
        Log.d("MyLog", "In addingToStartTask(List<Integer> list). For " + map.getClass().getName());
        double start = System.nanoTime();
        map.put(-1, -1);
        return (System.nanoTime() - start) / 1000000;
    }

    public static double removing(Map<Integer, Integer> map) {
        Log.d("MyLog", "In addingToStartTask(List<Integer> list). For " + map.getClass().getName());
        double start = System.nanoTime();
        map.remove(0);
        return (System.nanoTime() - start) / 1000000;
    }

    public static double search(Map<Integer, Integer> map) {
        Log.d("MyLog", "In addingToStartTask(List<Integer> list). For " + map.getClass().getName());
        double start = System.nanoTime();
        map.get(0);
        return (System.nanoTime() - start) / 1000000;
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
            TasksFactory.doingMapsTasks(amountOfThreads);
            return null;
        }
    }
}
