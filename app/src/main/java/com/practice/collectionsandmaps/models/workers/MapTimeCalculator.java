package com.practice.collectionsandmaps.models.workers;

import android.util.Log;

import com.practice.collectionsandmaps.dto.Tags;
import com.practice.collectionsandmaps.dto.TaskData;

import java.util.Map;

public class MapTimeCalculator implements TimeCalculator {

    public void execAndSetupTime(TaskData task) {
        switch(task.getTag()){
            case Tags.ADDING:{
                final double start = System.nanoTime();
                adding(task.getMap());
                task.setTimeForTask((System.nanoTime() - start) / 1000000);
                }
                break;
            case Tags.SEARCH_IN_MAP:{
                final double start = System.nanoTime();
                search(task.getMap());
                task.setTimeForTask((System.nanoTime() - start) / 1000000);
                }
                break;
            case Tags.REMOVING:{
                final double start = System.nanoTime();
                removing(task.getMap());
                task.setTimeForTask((System.nanoTime() - start) / 1000000);
                }
                break;
        }
        Log.d("MyLog", "In Task.doTask() - after setTimeForTask()");
    }

    public static void adding(Map<Integer, Integer> map) {
        Log.d("MyLog", "In addingToStartTask(List<Integer> list). For " + map.getClass().getName());
        map.put(-1, -1);
    }

    public static void removing(Map<Integer, Integer> map) {
        Log.d("MyLog", "In addingToStartTask(List<Integer> list). For " + map.getClass().getName());
        map.remove(0);
    }

    public static void search(Map<Integer, Integer> map) {
        Log.d("MyLog", "In addingToStartTask(List<Integer> list). For " + map.getClass().getName());
        map.get(0);
    }
}
