package com.practice.collectionsandmaps.suppliers;

import com.practice.collectionsandmaps.dto.ListTaskData;
import com.practice.collectionsandmaps.dto.TaskData;
import com.practice.collectionsandmaps.models.suppliers.MapsTasksSupplier;
import com.practice.collectionsandmaps.models.suppliers.TasksSupplier;

import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;
import java.util.TreeSet;

import static org.junit.Assert.assertEquals;

public class MapsTasksSupplierTest {

    private static TasksSupplier supplier;


    @BeforeClass
    public static void initSupplier(){
        supplier = new MapsTasksSupplier();
    }

    @Test
    public void getTasks_isCorrectAmountOfTasks(){
        final List<TaskData> tasks = supplier.getTasks();
        assertEquals(6, tasks.size());
    }

    @Test
    public void getTasks_everyTaskHasDifferentName(){
        final TreeSet<Integer> tasksWithoutRepeatedElements = new TreeSet<>();
        for(TaskData task: supplier.getTasks()){
            tasksWithoutRepeatedElements.add(task.getNameOfTask());
        }
        assertEquals(6, tasksWithoutRepeatedElements.size());
    }

    @Test
    public void getTasks_everyTwoTaskHasDifferentTag(){
        final List<TaskData> tasks = supplier.getTasks();
        final TreeSet<Integer> tasksWithoutRepeatedElements = new TreeSet<>();
        for (int i = 2; i < tasks.size(); i+=3){
            tasksWithoutRepeatedElements.add(tasks.get(i).getTag());
        }
        assertEquals(2, tasksWithoutRepeatedElements.size());
    }

    @Test
    public void getTasks_everyTwoTasksHaveTheSameTag(){
        final List<TaskData> tasks = supplier.getTasks();
        int counterOfTheSameParts = 0;
        for(int i = 1; i < tasks.size() ; i+=2){
            if(tasks.get(i).getTag() == tasks.get(i-1).getTag()){
                counterOfTheSameParts++;
            }
        }
        assertEquals(3, counterOfTheSameParts);
    }

    @Test
    public void getInitialResult_isCorrectAmountOfTasks(){
        final List<TaskData> initialTasks = supplier.getInitialResult();
        assertEquals(6, initialTasks.size());
    }

    @Test
    public void getInitialResult_IsCorrectDefaultTimeOfTasks(){
        final List<TaskData> initialTasks = supplier.getInitialResult();
        for(TaskData task: initialTasks){
            assertEquals(-1, task.getTimeForTask(), 0);
        }
    }

    @Test
    public void getCollectionsCount_isCorrectCount(){
        assertEquals(2, supplier.getCollectionsCount());
    }
}
