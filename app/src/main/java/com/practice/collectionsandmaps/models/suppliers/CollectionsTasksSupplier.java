package com.practice.collectionsandmaps.models.suppliers;

import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CollectionsTasksSupplier implements TasksSupplier {
    private static List<Integer> arrayList = new ArrayList<>();
    private static List<Integer> linkedList = new LinkedList<>();
    private static List<Integer> copyOnWriteArrayList = new CopyOnWriteArrayList<>();
    private static int amountOfElements;

    public CollectionsTasksSupplier(){
        Log.d("MyLog", "in CollectionSupplier constructor. Amount of elements = " + amountOfElements + " ALL LISTS "
                + arrayList.size() + " " + linkedList.size() + " " + copyOnWriteArrayList.size());
    }

    public void setAmountOfElements(int amountOfElements) {
        CollectionsTasksSupplier.amountOfElements = amountOfElements;
    }

    public void fillSuppliedEntities(){
        clearSuppliedEntities();
        final ExecutorService service = Executors.newSingleThreadExecutor();
        service.submit(() ->  {
                linkedList.addAll(Collections.nCopies(amountOfElements, 1));
                arrayList.addAll(linkedList);
                copyOnWriteArrayList.addAll(arrayList);
            Log.d("MyLog", "in CollectionSupplier fillSuppliedEntities(). Amount of elements = " + amountOfElements + ": Arr - "
                    + arrayList.size() + "; Linked - " + linkedList.size() + "; CopyOnWrite - " + copyOnWriteArrayList.size());

        });
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
}
