package com.practice.collectionsandmaps.dto;

import java.util.List;
import java.util.Map;

public class MapTaskData extends AbstractTaskData {


    private Map<Integer, Integer> map;
    private boolean showProgress;

    public MapTaskData(int nameOfTask, int tag, Map<Integer, Integer> map) {
        super(nameOfTask, tag);
        this.map = map;
        setTimeForTask(DEFAULT_TIME);
    }

    public MapTaskData(TaskData task){
        super(task.getNameOfTask(), task.getTag());
        setTimeForTask(task.getTimeForTask());
    }

    public Map<Integer, Integer> getMap() {
        return map;
    }
}
