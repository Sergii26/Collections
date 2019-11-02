package com.practice.collectionsandmaps.models.suppliers;

import java.util.List;
import java.util.Map;

public interface TasksSupplier {
    void setAmountOfElements(int amountOfElements);
    void fillSuppliedEntities();
    List<Integer> getArrayList();
    List<Integer> getLinkedList();
    List<Integer> getCopyOnWriteList();
    void clearSuppliedEntities();
    Map<Integer, Integer> getHashMap();
    Map<Integer, Integer> getTreeMap();
}
