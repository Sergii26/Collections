package com.practice.collectionsandmaps.models.factories;


import com.practice.collectionsandmaps.dto.TaskData;
import com.practice.collectionsandmaps.models.suppliers.TasksSupplier;

import java.util.List;

public interface TasksFactory {
    List<TaskData> getTasks(TasksSupplier tasksSupplier);
    int getCollectionsCount();
    List<TaskData> commitTasks(List<TaskData> tasks);
}
