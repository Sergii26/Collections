package com.practice.collectionsandmaps.models.factories;

import android.util.Log;

import com.practice.collectionsandmaps.R;
import com.practice.collectionsandmaps.dto.MapTaskData;
import com.practice.collectionsandmaps.dto.Tags;
import com.practice.collectionsandmaps.dto.TaskData;
import com.practice.collectionsandmaps.models.suppliers.TasksSupplier;

import java.util.ArrayList;
import java.util.List;

public class MapsTasksFactory implements TasksFactory {

    private static final List<TaskData> tasksForMaps = new ArrayList<>();
    private TasksSupplier mapsTasksSupplier;

    @Override
    public List<TaskData> getTasks(TasksSupplier tasksSupplier) {
        tasksForMaps.clear();
        mapsTasksSupplier = tasksSupplier;
        tasksForMaps.add(new MapTaskData(R.string.adding_hash_map, Tags.ADDING, mapsTasksSupplier.getHashMap()));
        tasksForMaps.add(new MapTaskData(R.string.adding_tree_map, Tags.ADDING, mapsTasksSupplier.getTreeMap()));
        tasksForMaps.add(new MapTaskData(R.string.search_hash_map, Tags.SEARCH_IN_MAP, mapsTasksSupplier.getHashMap()));
        tasksForMaps.add(new MapTaskData(R.string.search_tree_map, Tags.SEARCH_IN_MAP, mapsTasksSupplier.getTreeMap()));
        tasksForMaps.add(new MapTaskData(R.string.removing_hash_map, Tags.REMOVING, mapsTasksSupplier.getHashMap()));
        tasksForMaps.add(new MapTaskData(R.string.removing_tree_map, Tags.REMOVING, mapsTasksSupplier.getTreeMap()));
        Log.d("MyLog", "in CollectionsTasksFactory getTasks() for maps. Amount of Tasks: " + tasksForMaps.size());
        return tasksForMaps;
    }

    public List<TaskData> commitTasks(List<TaskData> tasks){
        List<TaskData> fixedTasks = new ArrayList<>();
        for(TaskData task: tasks) {
            fixedTasks.add(new MapTaskData(task));
        }
        return fixedTasks;
    }

    @Override
    public int getCollectionsCount() {
        return 2;
    }

}
