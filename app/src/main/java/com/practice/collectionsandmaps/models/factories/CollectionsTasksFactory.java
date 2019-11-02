package com.practice.collectionsandmaps.models.factories;

import android.util.Log;

import com.practice.collectionsandmaps.R;
import com.practice.collectionsandmaps.dto.ListTaskData;
import com.practice.collectionsandmaps.dto.Tags;
import com.practice.collectionsandmaps.dto.TaskData;
import com.practice.collectionsandmaps.models.suppliers.TasksSupplier;

import java.util.ArrayList;
import java.util.List;

public class CollectionsTasksFactory implements TasksFactory {

    private static final List<TaskData> tasksForCollections = new ArrayList<>();
    private TasksSupplier collectionTasksSupplier;

    public List<TaskData> getTasks(TasksSupplier tasksSupplier){
        tasksForCollections.clear();
        collectionTasksSupplier = tasksSupplier;
        tasksForCollections.add(new ListTaskData(R.string.adding_to_start_array_list, Tags.ADDING_TO_START, collectionTasksSupplier.getArrayList()));
        tasksForCollections.add(new ListTaskData(R.string.adding_to_start_linked_list, Tags.ADDING_TO_START, collectionTasksSupplier.getLinkedList()));
        tasksForCollections.add(new ListTaskData(R.string.adding_to_start_copy_on_write_list, Tags.ADDING_TO_START, collectionTasksSupplier.getCopyOnWriteList()));
        tasksForCollections.add(new ListTaskData(R.string.adding_to_middle_array_list, Tags.ADDING_TO_MIDDLE, collectionTasksSupplier.getArrayList()));
        tasksForCollections.add(new ListTaskData(R.string.adding_to_middle_linked_list, Tags.ADDING_TO_MIDDLE, collectionTasksSupplier.getLinkedList()));
        tasksForCollections.add(new ListTaskData(R.string.adding_to_middle_copy_on_write_list, Tags.ADDING_TO_MIDDLE, collectionTasksSupplier.getCopyOnWriteList()));
        tasksForCollections.add(new ListTaskData(R.string.adding_to_end_array_list, Tags.ADDING_TO_END, collectionTasksSupplier.getArrayList()));
        tasksForCollections.add(new ListTaskData(R.string.adding_to_end_linked_list, Tags.ADDING_TO_END, collectionTasksSupplier.getLinkedList()));
        tasksForCollections.add(new ListTaskData(R.string.adding_to_end_copy_on_write_list, Tags.ADDING_TO_END, collectionTasksSupplier.getCopyOnWriteList()));
        tasksForCollections.add(new ListTaskData(R.string.search_array_list, Tags.SEARCH, collectionTasksSupplier.getArrayList()));
        tasksForCollections.add(new ListTaskData(R.string.search_linked_list, Tags.SEARCH, collectionTasksSupplier.getLinkedList()));
        tasksForCollections.add(new ListTaskData(R.string.search_copy_on_write_list, Tags.SEARCH, collectionTasksSupplier.getCopyOnWriteList()));
        tasksForCollections.add(new ListTaskData(R.string.removing_from_start_array_list, Tags.REMOVING_FROM_START, collectionTasksSupplier.getArrayList()));
        tasksForCollections.add(new ListTaskData(R.string.removing_from_start_linked_list, Tags.REMOVING_FROM_START, collectionTasksSupplier.getLinkedList()));
        tasksForCollections.add(new ListTaskData(R.string.removing_from_start_copy_on_write_list, Tags.REMOVING_FROM_START, collectionTasksSupplier.getCopyOnWriteList()));
        tasksForCollections.add(new ListTaskData(R.string.removing_from_middle_array_list, Tags.REMOVING_FROM_MIDDLE, collectionTasksSupplier.getArrayList()));
        tasksForCollections.add(new ListTaskData(R.string.removing_from_middle_linked_list, Tags.REMOVING_FROM_MIDDLE, collectionTasksSupplier.getLinkedList()));
        tasksForCollections.add(new ListTaskData(R.string.removing_from_middle_copy_on_write_list, Tags.REMOVING_FROM_MIDDLE, collectionTasksSupplier.getCopyOnWriteList()));
        tasksForCollections.add(new ListTaskData(R.string.removing_from_end_array_list, Tags.REMOVING_FROM_END, collectionTasksSupplier.getArrayList()));
        tasksForCollections.add(new ListTaskData(R.string.removing_from_end_linked_list, Tags.REMOVING_FROM_END, collectionTasksSupplier.getLinkedList()));
        tasksForCollections.add(new ListTaskData(R.string.removing_from_end_copy_on_write_list, Tags.REMOVING_FROM_END, collectionTasksSupplier.getCopyOnWriteList()));
        Log.d("MyLog", "in CollectionsTasksFactory at getTasks() for collections. Amount of Tasks: " + tasksForCollections.size());
        return tasksForCollections;
    }

    public List<TaskData> commitTasks(List<TaskData> tasks){
        List<TaskData> fixedTasks = new ArrayList<>();
        for(TaskData task: tasks) {
            fixedTasks.add(new ListTaskData(task));
        }
        return fixedTasks;
    }

    @Override
    public int getCollectionsCount() {
        return 3;
    }
}
