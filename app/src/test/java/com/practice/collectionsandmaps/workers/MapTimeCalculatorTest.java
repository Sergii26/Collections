package com.practice.collectionsandmaps.workers;

import com.practice.collectionsandmaps.R;
import com.practice.collectionsandmaps.dto.MapTaskData;
import com.practice.collectionsandmaps.dto.Tags;
import com.practice.collectionsandmaps.dto.TaskData;
import com.practice.collectionsandmaps.models.suppliers.MapsTasksSupplier;
import com.practice.collectionsandmaps.models.workers.MapTimeCalculator;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class MapTimeCalculatorTest {

    private static Map<Integer, Integer> hashMap;
    private static Map<Integer, Integer> treeMap;
    private static MapsTasksSupplier supplierMock;

    private static MapTimeCalculator calculator;

    @BeforeClass
    public static void initMockAndCalculator(){
        List<TaskData> tasksForMaps = new ArrayList<>();
        tasksForMaps.add(new MapTaskData(R.string.adding_hash_map, Tags.ADDING, new HashMap<>()));
        tasksForMaps.add(new MapTaskData(R.string.adding_tree_map, Tags.ADDING, new TreeMap<>()));
        tasksForMaps.add(new MapTaskData(R.string.search_hash_map, Tags.SEARCH_IN_MAP, new HashMap<>()));
        tasksForMaps.add(new MapTaskData(R.string.search_tree_map, Tags.SEARCH_IN_MAP, new TreeMap<>()));
        tasksForMaps.add(new MapTaskData(R.string.removing_hash_map, Tags.REMOVING, new HashMap<>()));
        tasksForMaps.add(new MapTaskData(R.string.removing_tree_map, Tags.REMOVING, new TreeMap<>()));
        supplierMock = Mockito.mock(MapsTasksSupplier.class);
        Mockito.when(supplierMock.getTasks()).thenReturn(tasksForMaps);
        calculator = new MapTimeCalculator();
    }

    @Before
    public void initLists(){
        hashMap = new HashMap<>(100);
        for(int i = 0; i < 100; i++){
            hashMap.put(i, 1);
        }
        treeMap = new TreeMap<>(hashMap);
    }

    @Test
    public void execAndSetupTime_isCorrect(){
        List<TaskData> tasks = supplierMock.getTasks();
        for(TaskData task: tasks){
            task.fill(100);
            calculator.execAndSetupTime(task);
            assertNotEquals(-1, task.getTimeForTask());
        }
    }

    @Test
    public void adding_isCorrectAddingWithHashMap(){
        final int startSize = hashMap.size();
        calculator.adding(hashMap);
        final int resultSize = hashMap.size();
        assertEquals(1, resultSize - startSize);
        assertEquals(Integer.valueOf(-1), hashMap.get(-1));
    }

    @Test
    public void adding_isCorrectAddingWithTreeMap(){
        final int startSize = treeMap.size();
        calculator.adding(treeMap);
        final int resultSize = treeMap.size();
        assertEquals(1, resultSize - startSize);
        assertEquals(Integer.valueOf(-1), treeMap.get(-1));
    }

    @Test
    public void removing_isCorrectRemovingWithHashMap(){
        final int startSize = hashMap.size();
        assertEquals(Integer.valueOf(1), hashMap.get(0));
        calculator.removing(hashMap);
        final int resultSize = hashMap.size();
        assertEquals(1,startSize - resultSize);
        assertNotEquals(Integer.valueOf(1), hashMap.get(0));
    }

    @Test
    public void removing_isCorrectRemovingWithTreeMap(){
        final int startSize = treeMap.size();
        assertEquals(Integer.valueOf(1), treeMap.get(0));
        calculator.removing(treeMap);
        final int resultSize = treeMap.size();
        assertEquals(1,startSize - resultSize);
        assertNotEquals(Integer.valueOf(1), treeMap.get(0));
    }


}
