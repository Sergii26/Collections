package com.practice.collectionsandmaps.models;

import android.util.Log;

import java.util.Map;

public class MapWorker {

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
}
