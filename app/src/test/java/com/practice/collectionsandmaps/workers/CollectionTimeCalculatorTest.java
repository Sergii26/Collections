package com.practice.collectionsandmaps.workers;

import android.util.Log;

import com.practice.collectionsandmaps.R;
import com.practice.collectionsandmaps.dto.ListTaskData;
import com.practice.collectionsandmaps.dto.Tags;
import com.practice.collectionsandmaps.dto.TaskData;
import com.practice.collectionsandmaps.models.suppliers.TasksSupplier;
import com.practice.collectionsandmaps.models.workers.CollectionTimeCalculator;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class CollectionTimeCalculatorTest {


    private TasksSupplier supplierMock;
    private List<Integer> arrayList;
    private List<Integer> linkedList;
    private List<Integer> copyOnWriteList;

    private static CollectionTimeCalculator calculator;

    @Before
    public void initMocksAndCalculator(){
        List<TaskData> tasksForCollections = new ArrayList<>();
        tasksForCollections.add(new ListTaskData(R.string.adding_to_start_array_list, Tags.ADDING_TO_START, new ArrayList<>()));
        tasksForCollections.add(new ListTaskData(R.string.adding_to_start_linked_list, Tags.ADDING_TO_START, new LinkedList<>()));
        tasksForCollections.add(new ListTaskData(R.string.adding_to_start_copy_on_write_list, Tags.ADDING_TO_START, new CopyOnWriteArrayList<>()));
        tasksForCollections.add(new ListTaskData(R.string.adding_to_middle_array_list, Tags.ADDING_TO_MIDDLE, new ArrayList<>()));
        tasksForCollections.add(new ListTaskData(R.string.adding_to_middle_linked_list, Tags.ADDING_TO_MIDDLE, new LinkedList<>()));
        tasksForCollections.add(new ListTaskData(R.string.adding_to_middle_copy_on_write_list, Tags.ADDING_TO_MIDDLE, new CopyOnWriteArrayList<>()));
        tasksForCollections.add(new ListTaskData(R.string.adding_to_end_array_list, Tags.ADDING_TO_END, new ArrayList<>()));
        tasksForCollections.add(new ListTaskData(R.string.adding_to_end_linked_list, Tags.ADDING_TO_END, new LinkedList<>()));
        tasksForCollections.add(new ListTaskData(R.string.adding_to_end_copy_on_write_list, Tags.ADDING_TO_END, new CopyOnWriteArrayList<>()));
        tasksForCollections.add(new ListTaskData(R.string.search_array_list, Tags.SEARCH, new ArrayList<>()));
        tasksForCollections.add(new ListTaskData(R.string.search_linked_list, Tags.SEARCH, new LinkedList<>()));
        tasksForCollections.add(new ListTaskData(R.string.search_copy_on_write_list, Tags.SEARCH, new CopyOnWriteArrayList<>()));
        tasksForCollections.add(new ListTaskData(R.string.removing_from_start_array_list, Tags.REMOVING_FROM_START, new ArrayList<>()));
        tasksForCollections.add(new ListTaskData(R.string.removing_from_start_linked_list, Tags.REMOVING_FROM_START, new LinkedList<>()));
        tasksForCollections.add(new ListTaskData(R.string.removing_from_start_copy_on_write_list, Tags.REMOVING_FROM_START, new CopyOnWriteArrayList<>()));
        tasksForCollections.add(new ListTaskData(R.string.removing_from_middle_array_list, Tags.REMOVING_FROM_MIDDLE, new ArrayList<>()));
        tasksForCollections.add(new ListTaskData(R.string.removing_from_middle_linked_list, Tags.REMOVING_FROM_MIDDLE, new LinkedList<>()));
        tasksForCollections.add(new ListTaskData(R.string.removing_from_middle_copy_on_write_list, Tags.REMOVING_FROM_MIDDLE, new CopyOnWriteArrayList<>()));
        tasksForCollections.add(new ListTaskData(R.string.removing_from_end_array_list, Tags.REMOVING_FROM_END, new ArrayList<>()));
        tasksForCollections.add(new ListTaskData(R.string.removing_from_end_linked_list, Tags.REMOVING_FROM_END, new LinkedList<>()));
        tasksForCollections.add(new ListTaskData(R.string.removing_from_end_copy_on_write_list, Tags.REMOVING_FROM_END, new CopyOnWriteArrayList<>()));
        Log.d("MyLog", "in CollectionsTasksSupplier at getTasks() for collections. Amount of Tasks: " + tasksForCollections.size());
        supplierMock = Mockito.mock(TasksSupplier.class);
        Mockito.when(supplierMock.getTasks()).thenReturn(tasksForCollections);
        calculator = new CollectionTimeCalculator();
        arrayList = new ArrayList<>(100);
        for(int i = 0; i < 100; i++){
            arrayList.add(1);
        }
        linkedList = new LinkedList<>(arrayList);
        copyOnWriteList = new CopyOnWriteArrayList<>(arrayList);
    }

    @Test
    public void execAndSetupTime_tasksTimesAreChanging(){

        List<TaskData> tasks = supplierMock.getTasks();
        for(TaskData task: tasks){
            task.fill(100);
            calculator.execAndSetupTime(task);
            assertNotEquals(-1, task.getTimeForTask());
        }
    }

    @Test
    public void addingToStartTask_isCorrectAddingWithArrayList(){
        final int startSize = arrayList.size();
        calculator.addingToStartTask(arrayList);
        final int resultSize = arrayList.size();
        assertEquals(1, resultSize - startSize);
        assertEquals(Integer.valueOf(-1), arrayList.get(0));
    }

    @Test
    public void addingToStartTask_isCorrectAddingWithLinkedList(){
        final int startSize = linkedList.size();
        calculator.addingToStartTask(linkedList);
        final int resultSize = linkedList.size();
        assertEquals(1, resultSize - startSize);
        assertEquals(Integer.valueOf(-1), linkedList.get(0));
    }

    @Test
    public void addingToStartTask_isCorrectAddingWithCopyOnWriteList(){
        final int startSize = copyOnWriteList.size();
        calculator.addingToStartTask(copyOnWriteList);
        final int resultSize = copyOnWriteList.size();
        assertEquals(1, resultSize - startSize);
        assertEquals(Integer.valueOf(-1), copyOnWriteList.get(0));
    }

    @Test
    public void addingToMiddleTask_isCorrectAddingWithArrayList(){
        final int startSize = arrayList.size();
        calculator.addingToMiddleTask(arrayList);
        final int resultSize = arrayList.size();
        assertEquals(1, resultSize - startSize);
        assertEquals(Integer.valueOf(-2), arrayList.get(arrayList.size()/2));
    }

    @Test
    public void addingToMiddleTask_isCorrectAddingWithLinkedList(){
        final int startSize = linkedList.size();
        calculator.addingToMiddleTask(linkedList);
        final int resultSize = linkedList.size();
        assertEquals(1, resultSize - startSize);
        assertEquals(Integer.valueOf(-2), linkedList.get(linkedList.size()/2));
    }

    @Test
    public void addingToMiddleTask_isCorrectAddingWithCopyOnWriteList(){
        final int startSize = copyOnWriteList.size();
        calculator.addingToMiddleTask(copyOnWriteList);
        final int resultSize = copyOnWriteList.size();
        assertEquals(1, resultSize - startSize);
        assertEquals(Integer.valueOf(-2), copyOnWriteList.get(copyOnWriteList.size()/2));
    }

    @Test
    public void addingToEndTask_isCorrectAddingWithArrayList(){
        final int startSize = arrayList.size();
        calculator.addingToEndTask(arrayList);
        final int resultSize = arrayList.size();
        assertEquals(1, resultSize - startSize);
        assertEquals(Integer.valueOf(-3), arrayList.get(arrayList.size()-1));
    }

    @Test
    public void addingToEndTask_isCorrectAddingWithLinkedList(){
        final int startSize = linkedList.size();
        calculator.addingToEndTask(linkedList);
        final int resultSize = linkedList.size();
        assertEquals(1, resultSize - startSize);
        assertEquals(Integer.valueOf(-3), linkedList.get(linkedList.size()-1));
    }

    @Test
    public void addingToEndTask_isCorrectAddingWithCopyOnWriteList(){
        final int startSize = copyOnWriteList.size();
        calculator.addingToEndTask(copyOnWriteList);
        final int resultSize = copyOnWriteList.size();
        assertEquals(1, resultSize - startSize);
        assertEquals(Integer.valueOf(-3), copyOnWriteList.get(copyOnWriteList.size()-1));
    }

    @Test
    public void removingFromStartTask_isCorrectRemovingWithArrayList(){
        arrayList.add(0, -10);
        final int startSize = arrayList.size();
        calculator.removingFromStartTask(arrayList);
        final int resultSize = arrayList.size();
        assertEquals(1,startSize - resultSize);
        assertNotEquals(Integer.valueOf(-10), arrayList.get(0));
    }

    @Test
    public void removingFromStartTask_isCorrectRemovingWithLinkedList(){
        linkedList.add(0, -5);
        final int startSize = linkedList.size();
        calculator.removingFromStartTask(linkedList);
        final int resultSize = linkedList.size();
        assertEquals(1,startSize - resultSize);
        assertNotEquals(Integer.valueOf(-5), linkedList.get(0));
    }

    @Test
    public void removingFromStartTask_isCorrectRemovingWithCopyOnWriteList(){
        copyOnWriteList.add(0, -10);
        final int startSize = copyOnWriteList.size();
        calculator.removingFromStartTask(copyOnWriteList);
        final int resultSize = copyOnWriteList.size();
        assertEquals(1,startSize - resultSize);
        assertNotEquals(Integer.valueOf(-10), copyOnWriteList.get(0));
    }

    @Test
    public void removingFromMiddleTask_isCorrectRemovingWithArrayList(){
        arrayList.add(0, -5);
        final int startSize = arrayList.size();
        calculator.removingFromMiddleTask(arrayList);
        final int resultSize = arrayList.size();
        assertEquals(1,startSize - resultSize);
        assertNotEquals(Integer.valueOf(-5), arrayList.get(arrayList.size()/2));
    }

    @Test
    public void removingFromMiddleTask_isCorrectRemovingWithLinkedList(){
        linkedList.add(0, -5);
        final int startSize = linkedList.size();
        calculator.removingFromMiddleTask(linkedList);
        final int resultSize = linkedList.size();
        assertEquals(1,startSize - resultSize);
        assertNotEquals(Integer.valueOf(-5), linkedList.get(linkedList.size()/2));
    }

    @Test
    public void removingFromMiddleTask_isCorrectRemovingWithCopyOnWriteList(){
        copyOnWriteList.add(0, -5);
        final int startSize = copyOnWriteList.size();
        calculator.removingFromMiddleTask(copyOnWriteList);
        final int resultSize = copyOnWriteList.size();
        assertEquals(1,startSize - resultSize);
        assertNotEquals(Integer.valueOf(-5), copyOnWriteList.get(copyOnWriteList.size()/2));
    }

    @Test
    public void removingFromEndTask_isCorrectRemovingWithArrayList(){
        arrayList.add(-5);
        final int startSize = arrayList.size();
        calculator.removingFromEndTask(arrayList);
        final int resultSize = arrayList.size();
        assertEquals(1,startSize - resultSize);
        assertNotEquals(Integer.valueOf(-5), arrayList.get(arrayList.size()-1));
    }

    @Test
    public void removingFromEndTask_isCorrectRemovingWithLinkedList(){
        linkedList.add(-5);
        final int startSize = linkedList.size();
        calculator.removingFromEndTask(linkedList);
        final int resultSize = linkedList.size();
        assertEquals(1,startSize - resultSize);
        assertNotEquals(Integer.valueOf(-5), linkedList.get(linkedList.size()-1));
    }

    @Test
    public void removingFromEndTask_isCorrectRemovingWithCopyOnWriteList(){
        copyOnWriteList.add(-5);
        final int startSize = copyOnWriteList.size();
        calculator.removingFromEndTask(copyOnWriteList);
        final int resultSize = copyOnWriteList.size();
        assertEquals(1,startSize - resultSize);
        assertNotEquals(Integer.valueOf(-5), copyOnWriteList.get(copyOnWriteList.size()-1));
    }
}
