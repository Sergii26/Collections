package com.practice.collectionsandmaps.dto;

import java.util.List;
import java.util.Map;

public class AbstractTaskData implements TaskData{

    public static final String DEFAULT_TIME = "N/A ms";

    private int tag;
    private int nameOfTask;
    private String timeForTask;
    private boolean showProgress;

    public AbstractTaskData(int nameOfTask, int tag){
        this.nameOfTask = nameOfTask;
        this.tag = tag;
        showProgress = false;
        timeForTask = DEFAULT_TIME;
    }

    public int getTag() {
        return tag;
    }

    public void setTimeForTask(String timeForTask) {
        this.timeForTask = timeForTask;
    }

    public int getNameOfTask() {
        return nameOfTask;
    }

    public String getTimeForTask() {
        return timeForTask;
    }

    public boolean isDefaultTime(){
        return this.timeForTask.equals(DEFAULT_TIME);
    }

    public void setShowProgress(boolean showProgress) {
        this.showProgress = showProgress;
    }

    public boolean getShowProgress(){
        return showProgress;
    }
}
