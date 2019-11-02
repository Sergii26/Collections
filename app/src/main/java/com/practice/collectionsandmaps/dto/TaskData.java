package com.practice.collectionsandmaps.dto;

public interface TaskData {
    int getTag();
    void setTimeForTask(String timeForTask);
    int getNameOfTask();
    String getTimeForTask();
    boolean isDefaultTime();
    void setShowProgress(boolean showProgress);
    boolean getShowProgress();
}
