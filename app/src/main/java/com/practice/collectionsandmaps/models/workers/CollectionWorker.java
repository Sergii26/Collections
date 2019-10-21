package com.practice.collectionsandmaps.models.workers;

import android.util.Log;

import java.util.LinkedList;
import java.util.List;

public class CollectionWorker {

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
}

