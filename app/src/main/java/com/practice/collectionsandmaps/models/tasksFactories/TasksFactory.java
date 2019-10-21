package com.practice.collectionsandmaps.models.tasksFactories;

import com.practice.collectionsandmaps.dto.Task;
import com.practice.collectionsandmaps.models.Suppliers.Supplier;
import com.practice.collectionsandmaps.ui.fragment.PresenterCommunicator;

import java.util.List;

public interface TasksFactory {
    List<Task> getTasks(Supplier supplier, PresenterCommunicator presenterCommunicator);
    int getSpanCount();
    List<Task> getEmptyTasks(PresenterCommunicator presenterCommunicator);
}
