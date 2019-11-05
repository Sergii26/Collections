package com.practice.collectionsandmaps.models.workers;


import android.util.Log;

import com.practice.collectionsandmaps.dto.Tags;
import com.practice.collectionsandmaps.dto.TaskData;

import java.util.LinkedList;
import java.util.List;

public class CollectionTimeCalculator implements TimeCalculator {

    public void execAndSetupTime(TaskData task) {
        Log.d("MyLog", "In CollectionTimeCalculator at execAndSetupTime()");
        switch(task.getTag()){
            case Tags.ADDING_TO_START: {
                final double start = System.nanoTime();
                addingToStartTask(task.getList());
                task.setTimeForTask((System.nanoTime() - start) / 1000000);
                }
                break;
            case Tags.ADDING_TO_MIDDLE: {
                final double start = System.nanoTime();
                addingToMiddleTask(task.getList());
                task.setTimeForTask((System.nanoTime() - start) / 1000000);
                }
                break;
            case Tags.ADDING_TO_END: {
                final double start = System.nanoTime();
                addingToEndTask(task.getList());
                task.setTimeForTask((System.nanoTime() - start) / 1000000);
                }
                break;
            case Tags.SEARCH: {
                final double start = System.nanoTime();
                searchTask(task.getList());
                task.setTimeForTask((System.nanoTime() - start) / 1000000);
                }
                break;
            case Tags.REMOVING_FROM_START: {
                final double start = System.nanoTime();
                removingFromStartTask(task.getList());
                task.setTimeForTask((System.nanoTime() - start) / 1000000);
                }
                break;
            case Tags.REMOVING_FROM_MIDDLE: {
                final double start = System.nanoTime();
                removingFromMiddleTask(task.getList());
                task.setTimeForTask((System.nanoTime() - start) / 1000000);
                }
                break;
            case Tags.REMOVING_FROM_END: {
                final double start = System.nanoTime();
                removingFromEndTask(task.getList());
                task.setTimeForTask((System.nanoTime() - start) / 1000000);
                }
                break;
        }
    }


    public void addingToStartTask(List<Integer> list) {
        Log.d("MyLog", "In addingToStartTask(List<Integer> list). For " + list.getClass().getName());
        list.add(0, -1);
    }

    public void addingToMiddleTask(List<Integer> list) {
        list.add(list.size() / 2, -2);
    }

    public void addingToEndTask(List<Integer> list) {
        Log.d("MyLog", "In addingToEndTask(List<Integer> list). For " + list.getClass().getName());
        list.add(-3);
    }

    public void searchTask(List<Integer> list) {
        Log.d("MyLog", "In searchTask(List<Integer> list). For " + list.getClass().getName());
        list.indexOf(list.size() - 1);
    }

    public void removingFromStartTask(List<Integer> list) {
        Log.d("MyLog", "In removingFromStartTask(List<Integer> list). For " + list.getClass().getName());
        list.remove(0);
    }

    public void removingFromMiddleTask(List<Integer> list) {
        Log.d("MyLog", "In removingFromMiddleTask(List<Integer> list). For " + list.getClass().getName());
        list.remove(list.size() / 2);
    }

    public void removingFromEndTask(List<Integer> list) {
        Log.d("MyLog", "In removingFromEndTask(List<Integer> list). For " + list.getClass().getName());
        if (list.getClass().getName().equals("java.util.LinkedList")) {
            Log.d("MyLog", "Is a Linked List! For " + list.getClass().getName());
            ((LinkedList<Integer>) list).removeLast();
        }
        Log.d("MyLog", "Is not a Linked List!. For " + list.getClass().getName());
        list.remove(list.size() - 1);
    }

}

