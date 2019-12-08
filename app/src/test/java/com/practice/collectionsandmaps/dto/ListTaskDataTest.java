package com.practice.collectionsandmaps.dto;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.concurrent.CopyOnWriteArrayList;

import static org.junit.Assert.assertEquals;

public class ListTaskDataTest {

    private TaskData taskForArrayList;
    private TaskData taskForLinkedList;
    private TaskData taskForCopyOnWriteList;

    @Before
    public void initList() {
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
