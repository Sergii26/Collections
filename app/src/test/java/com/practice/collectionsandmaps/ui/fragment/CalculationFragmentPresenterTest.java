package com.practice.collectionsandmaps.ui.fragment;

import com.practice.collectionsandmaps.R;
import com.practice.collectionsandmaps.dto.ListTaskData;
import com.practice.collectionsandmaps.dto.Tags;
import com.practice.collectionsandmaps.dto.TaskData;
import com.practice.collectionsandmaps.models.suppliers.CollectionsTasksSupplier;
import com.practice.collectionsandmaps.models.suppliers.TasksSupplier;
import com.practice.collectionsandmaps.models.workers.CollectionTimeCalculator;
import com.practice.collectionsandmaps.models.workers.TimeCalculator;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.schedulers.Schedulers;

import static junit.framework.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;

public class CalculationFragmentPresenterTest {

    private CalculationFragmentContract.FragmentView view;
    private CalculationFragmentPresenter presenter;
    private TimeCalculator calculator;
    private TasksSupplier supplier;

    @BeforeClass
    public static void setupClass() {
        RxAndroidPlugins.setInitMainThreadSchedulerHandler(
                __ -> Schedulers.trampoline());
    }

    @Before
    public void setUp(){
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
        calculator = Mockito.mock(CollectionTimeCalculator.class);
        supplier = Mockito.mock(CollectionsTasksSupplier.class);
        view = Mockito.mock(CollectionsAndMapsFragment.class);
        presenter = new CalculationFragmentPresenter(supplier, calculator);
        presenter.setView(view);
        Mockito.when(supplier.getCollectionsCount()).thenReturn(3);
        Mockito.when(supplier.getInitialResult()).thenReturn(new ArrayList<TaskData>());
        Mockito.when(supplier.getTasks()).thenReturn(tasksForCollections);
    }

    @After
    public void tearDown(){
        calculator = null;
        supplier = null;
        view = null;
        presenter = null;
    }

    private void verifyNoMore(){
        Mockito.verifyNoMoreInteractions(supplier);
        Mockito.verifyNoMoreInteractions(view);
        Mockito.verifyNoMoreInteractions(calculator);
    }

    @Test
    public void testCreated(){
        assertNotNull(presenter);
    }

    @Test
    public void getCollectionsCount_countIsBack(){
        final int num = presenter.getCollectionsCount();
        assertEquals(3, num);
        Mockito.verify(supplier).getCollectionsCount();
        verifyNoMore();
    }

    @Test
    public void getInitialResult_resultIsBack(){
        List<TaskData> initTasks = presenter.getInitialResult();
        List<TaskData> expectedValue = new ArrayList<>();
        assertEquals(expectedValue, initTasks);
        Mockito.verify(supplier).getInitialResult();
        verifyNoMore();
    }

    @Test
    public void startCalculation_withTwoEmptyStrings(){
        presenter.startCalculation("", "");
        Mockito.verify(view,Mockito.times(1)).setElementsError(Mockito.any());
        Mockito.verify(view, Mockito.times(1)).getStringFromResources(R.string.elements_must_not_be_empty);
        Mockito.verify(view,Mockito.times(1)).setThreadsError(Mockito.any());
        Mockito.verify(view, Mockito.times(1)).getStringFromResources(R.string.threads_must_not_be_empty);
        Mockito.verify(view,Mockito.times(0)).setBtnChecked(false);
        Mockito.verify(view,Mockito.times(0)).showData(Mockito.any());
        Mockito.verify(view,Mockito.times(0)).showProgress();
        Mockito.verify(view,Mockito.times(0)).showToast(Mockito.any());
        Mockito.verify(view,Mockito.times(0)).setupResult(Mockito.any());
        verifyNoMore();
    }

    @Test
    public void startCalculation_withTwoZeroStrings(){
        presenter.startCalculation("0", "0");
        Mockito.verify(view,Mockito.times(1)).setElementsError(Mockito.any());
        Mockito.verify(view, Mockito.times(1)).getStringFromResources(R.string.enter_elements);
        Mockito.verify(view,Mockito.times(1)).setThreadsError(Mockito.any());
        Mockito.verify(view, Mockito.times(1)).getStringFromResources(R.string.enter_threads);
        Mockito.verify(view,Mockito.times(0)).setBtnChecked(false);
        Mockito.verify(view,Mockito.times(0)).showData(Mockito.any());
        Mockito.verify(view,Mockito.times(0)).showProgress();
        Mockito.verify(view,Mockito.times(0)).showToast(Mockito.any());
        Mockito.verify(view,Mockito.times(0)).setupResult(Mockito.any());
        verifyNoMore();
    }

    @Test
    public void startCalculation_withEmptyElementsAndPositiveThreads(){
        presenter.startCalculation("", "2");
        Mockito.verify(view,Mockito.times(1)).setElementsError(Mockito.any());
        Mockito.verify(view, Mockito.times(1)).getStringFromResources(R.string.elements_must_not_be_empty);
        Mockito.verify(view,Mockito.times(1)).setThreadsError(null);
        Mockito.verify(view,Mockito.times(0)).setBtnChecked(false);
        Mockito.verify(view,Mockito.times(0)).showData(Mockito.any());
        Mockito.verify(view,Mockito.times(0)).showProgress();
        Mockito.verify(view,Mockito.times(0)).showToast(Mockito.any());
        Mockito.verify(view,Mockito.times(0)).setupResult(Mockito.any());
        verifyNoMore();
    }

    @Test
    public void startCalculation_withEmptyThreadsAndPositiveElements(){
        presenter.startCalculation("2", "");
        Mockito.verify(view,Mockito.times(1)).setElementsError(null);
        Mockito.verify(view,Mockito.times(1)).setThreadsError(Mockito.any());
        Mockito.verify(view, Mockito.times(1)).getStringFromResources(R.string.threads_must_not_be_empty);
        Mockito.verify(view,Mockito.times(0)).setBtnChecked(false);
        Mockito.verify(view,Mockito.times(0)).showData(Mockito.any());
        Mockito.verify(view,Mockito.times(0)).showProgress();
        Mockito.verify(view,Mockito.times(0)).showToast(Mockito.any());
        Mockito.verify(view,Mockito.times(0)).setupResult(Mockito.any());
        verifyNoMore();
    }

    @Test
    public void startCalculation_withZeroThreadsAndPositiveElements(){
        presenter.startCalculation("2", "0");
        Mockito.verify(view,Mockito.times(1)).setElementsError(null);
        Mockito.verify(view,Mockito.times(1)).setThreadsError(Mockito.any());
        Mockito.verify(view, Mockito.times(1)).getStringFromResources(R.string.enter_threads);
        Mockito.verify(view,Mockito.times(0)).setBtnChecked(false);
        Mockito.verify(view,Mockito.times(0)).showData(Mockito.any());
        Mockito.verify(view,Mockito.times(0)).showProgress();
        Mockito.verify(view,Mockito.times(0)).showToast(Mockito.any());
        Mockito.verify(view,Mockito.times(0)).setupResult(Mockito.any());
        verifyNoMore();
    }

    @Test
    public void startCalculation_withZeroElementsAndPositiveThreads(){
        presenter.startCalculation("0", "2");
        Mockito.verify(view,Mockito.times(1)).setElementsError(Mockito.any());
        Mockito.verify(view, Mockito.times(1)).getStringFromResources(R.string.enter_elements);
        Mockito.verify(view,Mockito.times(1)).setThreadsError(null);
        Mockito.verify(view,Mockito.times(0)).setBtnChecked(false);
        Mockito.verify(view,Mockito.times(0)).showData(Mockito.any());
        Mockito.verify(view,Mockito.times(0)).showProgress();
        Mockito.verify(view,Mockito.times(0)).showToast(Mockito.any());
        Mockito.verify(view,Mockito.times(0)).setupResult(Mockito.any());
        verifyNoMore();
    }

    @Test
    public void startCalculation_withZeroElementsAndEmptyThreads(){
        presenter.startCalculation("0", "");
        Mockito.verify(view,Mockito.times(1)).setElementsError(Mockito.any());
        Mockito.verify(view, Mockito.times(1)).getStringFromResources(R.string.enter_elements);
        Mockito.verify(view,Mockito.times(1)).setThreadsError(Mockito.any());
        Mockito.verify(view, Mockito.times(1)).getStringFromResources(R.string.threads_must_not_be_empty);
        Mockito.verify(view,Mockito.times(0)).setBtnChecked(false);
        Mockito.verify(view,Mockito.times(0)).showData(Mockito.any());
        Mockito.verify(view,Mockito.times(0)).showProgress();
        Mockito.verify(view,Mockito.times(0)).showToast(Mockito.any());
        Mockito.verify(view,Mockito.times(0)).setupResult(Mockito.any());
        verifyNoMore();
    }

    @Test
    public void startCalculation_withEmptyElementsAndZeroThreads(){
        presenter.startCalculation("", "0");
        Mockito.verify(view,Mockito.times(1)).setElementsError(Mockito.any());
        Mockito.verify(view, Mockito.times(1)).getStringFromResources(R.string.elements_must_not_be_empty);
        Mockito.verify(view,Mockito.times(1)).setThreadsError(Mockito.any());
        Mockito.verify(view, Mockito.times(1)).getStringFromResources(R.string.enter_threads);
        Mockito.verify(view,Mockito.times(0)).setBtnChecked(false);
        Mockito.verify(view,Mockito.times(0)).showData(Mockito.any());
        Mockito.verify(view,Mockito.times(0)).showProgress();
        Mockito.verify(view,Mockito.times(0)).showToast(Mockito.any());
        Mockito.verify(view,Mockito.times(0)).setupResult(Mockito.any());
        verifyNoMore();
    }

    @Ignore //don`t work with other tests
    @Test
    public void startCalculation_withPositiveElementsAndPositiveThreads(){
        presenter.startCalculation("100", "4");
        Mockito.verify(view).setElementsError(null);
        Mockito.verify(view).setThreadsError(null);
        Mockito.verify(view).setBtnChecked(true);
        Mockito.verify(supplier).getInitialResult();
        Mockito.verify(view).showData(Mockito.any());
        Mockito.verify(view).showProgress();
        Mockito.verify(supplier).getTasks();
        Mockito.verify(calculator, Mockito.times(21)).execAndSetupTime(Mockito.any());
        Mockito.verify(view, Mockito.times(21)).setupResult(Mockito.any());
        Mockito.verify(view).setBtnChecked(false);
        Mockito.verify(view).getStringFromResources(R.string.calculation_finished);
        Mockito.verify(view).showToast(Mockito.any());
        Mockito.verify(view).calculationStopped();
        verifyNoMore();
    }

}
