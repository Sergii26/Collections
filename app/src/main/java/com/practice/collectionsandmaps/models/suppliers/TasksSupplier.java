package com.practice.collectionsandmaps.models.suppliers;


import com.practice.collectionsandmaps.dto.TaskData;

import java.util.List;

public interface TasksSupplier {
    List<TaskData> getTasks();
    int getCollectionsCount();
    List<TaskData> getInitialResult();
}
