package com.practice.collectionsandmaps.models.suppliers;

import android.util.Log;

import com.practice.collectionsandmaps.R;
import com.practice.collectionsandmaps.dto.ListTaskData;
import com.practice.collectionsandmaps.dto.Tags;
import com.practice.collectionsandmaps.dto.TaskData;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class CollectionsTasksSupplier implements TasksSupplier {

    public List<TaskData> getTasks(){
        final List<TaskData> tasksForCollections = new ArrayList<>();
        tasksForCollections.add(new ListTaskData(R.string.adding_to_start_array_list, Tags.ADDING_TO_START, new ArrayList<>()));
        tasksForCollections.add(new ListTaskData(R.string.adding_to_start_linked_list, Tags.ADDING_TO_START, new LinkedList<>()));
        tasksForCollections.add(new ListTaskData(R.string.adding_to_start_copy_on_write_list, Tags.ADDING_TO_START, new CopyOnWriteArrayList<>()));
        tasksForCollections.add(new ListTaskData(R.string.adding_to_middle_array_list, Tags.ADDING_TO_MIDDLE, new ArrayList<>()));
        tasksForCollections.add(new ListTaskData(R.string.adding_to_middle_linked_list, Tags.ADDING_TO_MIDDLE, new LinkedList<>()));
        tasksForCollections.add(new ListTaskData(R.string.adding_to_middle_copy_on_write_list, Tags.ADDING_TO_MIDDLE, new CopyOnWriteArrayList<>()));
        tasksForCollections.add(new ListTaskData(R.string.adding_to_end_array_list, Tags.ADDING_TO_END, new ArrayList<>()));
        tasksForCollections.add(new ListTaskData(R.string.adding_to_end_linked_list, Tags.ADDING_TO_END, new LinkedList<>()));
        tasksForCollections.add(new ListTaskData(R.string.adding_to_end_copy_on_write_list, Tags.ADDING_TO_END, new CopyOnWriteArrayList<>()));
        tasksForCollections.add(new ListTaskData(R.string.search_array_list, Tags.SEARCH, new ArrayList<>()));
        tasksForCollections.add(new ListTaskData(R.string.search_linked_list, Tags.SEARCH, new LinkedList<>()));
        tasksForCollections.add(new ListTaskData(R.string.search_copy_on_write_list, Tags.SEARCH, new CopyOnWriteArrayList<>()));
        tasksForCollections.add(new ListTaskData(R.string.removing_from_start_array_list, Tags.REMOVING_FROM_START, new ArrayList<>()));
        tasksForCollections.add(new ListTaskData(R.string.removing_from_start_linked_list, Tags.REMOVING_FROM_START, new LinkedList<>()));
        tasksForCollections.add(new ListTaskData(R.string.removing_from_start_copy_on_write_list, Tags.REMOVING_FROM_START, new CopyOnWriteArrayList<>()));
        tasksForCollections.add(new ListTaskData(R.string.removing_from_middle_array_list, Tags.REMOVING_FROM_MIDDLE, new ArrayList<>()));
        tasksForCollections.add(new ListTaskData(R.string.removing_from_middle_linked_list, Tags.REMOVING_FROM_MIDDLE, new LinkedList<>()));
        tasksForCollections.add(new ListTaskData(R.string.removing_from_middle_copy_on_write_list, Tags.REMOVING_FROM_MIDDLE, new CopyOnWriteArrayList<>()));
        tasksForCollections.add(new ListTaskData(R.string.removing_from_end_array_list, Tags.REMOVING_FROM_END, new ArrayList<>()));
        tasksForCollections.add(new ListTaskData(R.string.removing_from_end_linked_list, Tags.REMOVING_FROM_END, new LinkedList<>()));
        tasksForCollections.add(new ListTaskData(R.string.removing_from_end_copy_on_write_list, Tags.REMOVING_FROM_END, new CopyOnWriteArrayList<>()));
        Log.d("MyLog", "in CollectionsTasksSupplier at getTasks() for collections. Amount of Tasks: " + tasksForCollections.size());
        return tasksForCollections;
    }

    public List<TaskData> getInitialResult() {
        final List<TaskData> results = new ArrayList<>(21);
        for (TaskData td : getTasks()) {
            results.add(td.getResult());
        }
        return results;
    }

    @Override
    public int getCollectionsCount() {
        return 3;
    }
}
