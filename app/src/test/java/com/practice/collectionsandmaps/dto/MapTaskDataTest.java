package com.practice.collectionsandmaps.dto;

import com.practice.collectionsandmaps.dto.MapTaskData;
import com.practice.collectionsandmaps.dto.TaskData;

import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashMap;
import java.util.TreeMap;

import static org.junit.Assert.assertEquals;

public class MapTaskDataTest {

    private static TaskData taskForHashMap;
    private static TaskData taskForTreeMap;

    @BeforeClass
    public static void initList() {
        taskForHashMap = new MapTaskData(1, 1, new HashMap<>());
        taskForTreeMap = new MapTaskData(1, 1, new TreeMap<>());
    }

    @Test
    public void fill_isCorrectWithHashMap(){
        final int elements = 1000;
        taskForHashMap.fill(elements);
        assertEquals(1000, taskForHashMap.getMap().size());
    }

    @Test
    public void fill_isCorrectWithTreeMap(){
        final int elements = 1000;
        taskForTreeMap.fill(elements);
        assertEquals(1000, taskForTreeMap.getMap().size());
    }

}
