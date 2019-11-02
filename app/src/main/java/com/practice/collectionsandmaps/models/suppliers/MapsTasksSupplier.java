package com.practice.collectionsandmaps.models.suppliers;

import android.util.Log;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MapsTasksSupplier implements TasksSupplier {

    private static int amountOfElements;
    private static Map<Integer, Integer> hashMap = new HashMap<>();
    private static Map<Integer, Integer> treeMap = new TreeMap<>();

    public MapsTasksSupplier() {
        Log.d("MyLog", "in MapsTasksSupplier constructor. Amount of elements = " + amountOfElements + " ALL MAPS "
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
        MapsTasksSupplier.amountOfElements = amountOfElements;
    }

    @Override
    public void fillSuppliedEntities() {
        clearSuppliedEntities();
        final ExecutorService service = Executors.newSingleThreadExecutor();
        service.submit(() -> {
                hashMap = new HashMap<>(amountOfElements);
                for (int i = 0; i < amountOfElements; i++) {
                    Integer integer = i;
                    hashMap.put(integer, integer);
                }
                treeMap.putAll(hashMap);
                Log.d("MyLog", "in CollectionSupplier asyncTask. Amount of elements = " + amountOfElements + ": HashMap - "
                        + hashMap.size() + "; TreeMap - " + treeMap.size() );
        });
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
}
