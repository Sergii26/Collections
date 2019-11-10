package com.practice.collectionsandmaps.dto;

import java.util.Collections;
import java.util.List;

public class ListTaskData extends AbstractTaskData {

    private List<Integer> list;

    public ListTaskData(int nameOfTask, int tag, List<Integer> list) {
        super(nameOfTask, tag);
        this.list = list;
    }

    public void fill(int amountOfElements){
        list.addAll(Collections.nCopies(amountOfElements, 1));
    }

    @Override
    public List<Integer> getList() {
        return list;
    }
}
