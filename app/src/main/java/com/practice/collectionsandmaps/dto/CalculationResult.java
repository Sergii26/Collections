package com.practice.collectionsandmaps.dto;

public class CalculationResult extends AbstractTaskData{

    public CalculationResult(int nameOfTask, int tag, double timeForTask){
        super(nameOfTask, tag);
        this.setTimeForTask(timeForTask);
    }
}
