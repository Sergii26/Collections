package com.practice.collectionsandmaps.dto;

import java.util.List;
import java.util.Map;

public class AbstractTaskData implements TaskData {

    private final double DEFAULT_TIME = -1;

    private int tag;
    private int nameOfTask;
    private double timeForTask;
    private boolean showProgress;

    public AbstractTaskData(int nameOfTask, int tag){
        this.nameOfTask = nameOfTask;
        this.tag = tag;
        showProgress = false;
        timeForTask = DEFAULT_TIME;
    }

    @Override
    public int getTag() {
        return tag;
    }

    @Override
    public void setTimeForTask(double timeForTask) {
        this.timeForTask = timeForTask;
    }

    @Override
    public int getNameOfTask() {
        return nameOfTask;
    }

    @Override
    public double getTimeForTask() {
        return timeForTask;
    }

    @Override
    public boolean isDefaultTime() {
        return timeForTask == DEFAULT_TIME;
    }

    @Override
    public void setShowProgress(boolean showProgress) {
        this.showProgress = showProgress;
    }

    @Override
    public boolean getShowProgress(){
        return showProgress;
    }

    @Override
    public List<Integer> getList() {
        return null;
    }

    @Override
    public Map<Integer, Integer> getMap() {
        return null;
    }

    @Override
    public CalculationResult getResult() {
        return new CalculationResult(nameOfTask, tag, timeForTask);
    }

    @Override
    public void fill(int amountOfElements) {

    }
}
