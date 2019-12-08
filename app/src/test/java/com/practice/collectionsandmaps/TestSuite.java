package com.practice.collectionsandmaps;

import com.practice.collectionsandmaps.dto.ListTaskDataTest;
import com.practice.collectionsandmaps.suppliers.CollectionsTasksSupplierTest;
import com.practice.collectionsandmaps.suppliers.MapsTasksSupplierTest;
import com.practice.collectionsandmaps.ui.fragment.CalculationFragmentPresenterTest;
import com.practice.collectionsandmaps.workers.CollectionTimeCalculatorTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({ListTaskDataTest.class, CollectionsTasksSupplierTest.class, MapsTasksSupplierTest.class, CalculationFragmentPresenterTest.class,
        CollectionTimeCalculatorTest.class})
public class TestSuite {
}
