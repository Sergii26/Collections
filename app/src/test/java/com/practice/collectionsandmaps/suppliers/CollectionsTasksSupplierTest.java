package com.practice.collectionsandmaps.suppliers;

import com.practice.collectionsandmaps.dto.TaskData;
import com.practice.collectionsandmaps.models.suppliers.CollectionsTasksSupplier;
import com.practice.collectionsandmaps.models.suppliers.TasksSupplier;

import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.TreeSet;

public class CollectionsTasksSupplierTest {

    private static TasksSupplier supplier;

    @BeforeClass
    public static void initSupplier(){
        supplier = new CollectionsTasksSupplier();
    }

    @Test
    public void getTasks_isCorrectAmountOfTasks(){
        final List<TaskData> tasks = supplier.getTasks();
        assertEquals(21, tasks.size());
    }

    @Test
    public void getTasks_everyTaskHasDifferentName(){
        final TreeSet<Integer> tasksWithoutRepeatedElements = new TreeSet<>();
        for(TaskData task: supplier.getTasks()){
            tasksWithoutRepeatedElements.add(task.getNameOfTask());
        }
        assertEquals(21, tasksWithoutRepeatedElements.size());
    }

    @Test
    public void getTasks_everyThreeTaskHasDifferentTag(){
        final List<TaskData> tasks = supplier.getTasks();
        final TreeSet<Integer> tasksWithoutRepeatedElements = new TreeSet<>();
        for (int i = 2; i < tasks.size(); i+=3){
            tasksWithoutRepeatedElements.add(tasks.get(i).getTag());
        }
        assertEquals(7, tasksWithoutRepeatedElements.size());
    }

    @Test
    public void getTasks_everyThreeTasksHaveTheSameTag(){
        final List<TaskData> tasks = supplier.getTasks();
        int counterOfTheSameParts = 0;
        for(int i = 2; i < tasks.size() ; i+=3){
           if(tasks.get(i).getTag() == tasks.get(i-1).getTag() && tasks.get(i-1).getTag() == tasks.get(i-2).getTag()){
               counterOfTheSameParts++;
           }
        }
        assertEquals(7, counterOfTheSameParts);
    }

    @Test
    public void getInitialResult_isCorrectAmountOfTasks(){
        final List<TaskData> initialTasks = supplier.getInitialResult();
        assertEquals(21, initialTasks.size());
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
        assertEquals(3, supplier.getCollectionsCount());
    }
}
