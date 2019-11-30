package com.practice.collectionsandmaps.ui.fragment.tests;

import com.practice.collectionsandmaps.R;
import com.practice.collectionsandmaps.dto.MapTaskData;
import com.practice.collectionsandmaps.dto.Tags;
import com.practice.collectionsandmaps.dto.TaskData;
import com.practice.collectionsandmaps.models.suppliers.TasksSupplier;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;

public class MockTasksSupplier implements TasksSupplier {
    @Override
    public List<TaskData> getTasks() {
        List<TaskData> tasksForMaps = new ArrayList<>();
        tasksForMaps.add(new MapTaskData(R.string.adding_hash_map, 7, new HashMap<>()));
        tasksForMaps.add(new MapTaskData(R.string.adding_tree_map, 7, new TreeMap<>()));
        tasksForMaps.add(new MapTaskData(R.string.search_hash_map, 8, new HashMap<>()));
        tasksForMaps.add(new MapTaskData(R.string.search_tree_map, 8, new TreeMap<>()));
        tasksForMaps.add(new MapTaskData(R.string.removing_hash_map, 9, new HashMap<>()));
        tasksForMaps.add(new MapTaskData(R.string.removing_tree_map, 9, new TreeMap<>()));
        return tasksForMaps;
    }

    @Override
    public int getCollectionsCount() {
        return 0;
    }

    @Override
    public List<TaskData> getInitialResult() {
        return null;
    }
}
