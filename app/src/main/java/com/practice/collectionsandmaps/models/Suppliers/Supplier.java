package com.practice.collectionsandmaps.models.Suppliers;

import java.util.List;
import java.util.Map;

public interface Supplier {
    void setAmountOfElements(int amountOfElements);
    void setAmountOfThreads(int amountOfThreads);
    void fillSuppliedEntities();
    List<Integer> getArrayList();
    List<Integer> getLinkedList();
    List<Integer> getCopyOnWriteList();
    void clearSuppliedEntities();
    Map<Integer, Integer> getHashMap();
    Map<Integer, Integer> getTreeMap();
}
