package com.practice.collectionsandmaps.dto;

import java.util.Map;

public class MapTaskData extends AbstractTaskData {

    private Map<Integer, Integer> map;

    public MapTaskData(int nameOfTask, int tag, Map<Integer, Integer> map) {
        super(nameOfTask, tag);
        this.map = map;
    }

    @Override
    public Map<Integer, Integer> getMap() {
        return map;
    }

    @Override
    public void fill(int amountOfElements) {
        for (int i = 0; i < amountOfElements; i++) {
            Integer integer = i;
            map.put(integer, integer);
        }
    }
}
