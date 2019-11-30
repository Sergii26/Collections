package com.practice.collectionsandmaps.dto;

import com.practice.collectionsandmaps.dto.ListTaskData;
import com.practice.collectionsandmaps.dto.TaskData;

import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.concurrent.CopyOnWriteArrayList;

public class ListTaskDataTest {

    private static TaskData taskForArrayList;
    private static TaskData taskForLinkedList;
    private static TaskData taskForCopyOnWriteList;

    @BeforeClass
    public static void initList() {
        taskForArrayList = new ListTaskData(1, 1, new ArrayList<>());
        taskForLinkedList = new ListTaskData(1, 1, new LinkedList<>());
        taskForCopyOnWriteList = new ListTaskData(1, 1, new CopyOnWriteArrayList<>());
    }

    @Test
    public void fill_isCorrectWithArrayList(){
        final int elements = 1000;
        taskForArrayList.fill(elements);
        assertEquals(1000, taskForArrayList.getList().size());
    }

    @Test
    public void fill_isCorrectWithLinkedList(){
        final int elements = 1000;
        taskForLinkedList.fill(elements);
        assertEquals(1000, taskForLinkedList.getList().size());
    }

    @Test
    public void fill_isCorrectWithCopyOnWriteList(){
        final int elements = 1000;
        taskForCopyOnWriteList.fill(elements);
        assertEquals(1000, taskForCopyOnWriteList.getList().size());
    }
}
