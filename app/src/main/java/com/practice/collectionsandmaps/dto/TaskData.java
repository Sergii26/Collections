package com.practice.collectionsandmaps.dto;

import java.util.List;
import java.util.Map;

public interface TaskData {
    int getTag();
    void setTimeForTask(double timeForTask);
    int getNameOfTask();
    double getTimeForTask();
    boolean isDefaultTime();
    void setShowProgress(boolean showProgress);
    boolean getShowProgress();
    List<Integer> getList();
    Map<Integer, Integer> getMap();
    CalculationResult getResult();
    void fill(int amountOfElements);

}
