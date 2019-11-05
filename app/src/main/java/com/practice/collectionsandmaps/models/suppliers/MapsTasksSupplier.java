package com.practice.collectionsandmaps.models.suppliers;

import android.util.Log;

import com.practice.collectionsandmaps.R;
import com.practice.collectionsandmaps.dto.MapTaskData;
import com.practice.collectionsandmaps.dto.Tags;
import com.practice.collectionsandmaps.dto.TaskData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;

public class MapsTasksSupplier implements TasksSupplier {

    @Override
    public List<TaskData> getTasks() {
        final List<TaskData> tasksForMaps = new ArrayList<>();
        tasksForMaps.add(new MapTaskData(R.string.adding_hash_map, Tags.ADDING, new HashMap<>()));
        tasksForMaps.add(new MapTaskData(R.string.adding_tree_map, Tags.ADDING, new TreeMap<>()));
        tasksForMaps.add(new MapTaskData(R.string.search_hash_map, Tags.SEARCH_IN_MAP, new HashMap<>()));
        tasksForMaps.add(new MapTaskData(R.string.search_tree_map, Tags.SEARCH_IN_MAP, new TreeMap<>()));
        tasksForMaps.add(new MapTaskData(R.string.removing_hash_map, Tags.REMOVING, new HashMap<>()));
        tasksForMaps.add(new MapTaskData(R.string.removing_tree_map, Tags.REMOVING, new TreeMap<>()));
        Log.d("MyLog", "in CollectionsTasksSupplier getTasks() for maps. Amount of Tasks: " + tasksForMaps.size());
        return tasksForMaps;
    }

    public List<TaskData> getInitialResult() {
        final List<TaskData> results = new ArrayList<>(6);
        for (TaskData td : getTasks()) {
            results.add(td.getResult());
        }
        return results;
    }

    @Override
    public int getCollectionsCount() {
        return 2;
    }

}
