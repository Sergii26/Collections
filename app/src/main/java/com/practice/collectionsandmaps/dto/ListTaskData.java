package com.practice.collectionsandmaps.dto;

import java.util.List;
import java.util.Map;

public class ListTaskData extends AbstractTaskData {

    private List<Integer> list;

    public ListTaskData(int nameOfTask, int tag, List<Integer> list) {
        super(nameOfTask, tag);
        this.list = list;
    }

    public ListTaskData(TaskData task){
        super(task.getNameOfTask(), task.getTag());
        setTimeForTask(task.getTimeForTask());
    }

    public List<Integer> getList() {
        return list;
    }

}
