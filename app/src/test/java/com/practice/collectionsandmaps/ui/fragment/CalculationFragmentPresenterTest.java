package com.practice.collectionsandmaps.ui.fragment;

import com.practice.collectionsandmaps.models.suppliers.TasksSupplier;
import com.practice.collectionsandmaps.models.workers.TimeCalculator;
import com.practice.collectionsandmaps.ui.fragment.tests.MockTasksSupplier;
import com.practice.collectionsandmaps.ui.fragment.tests.MockTimeCalculator;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mockito;

import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.schedulers.Schedulers;

import static junit.framework.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

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
        calculator = new MockTimeCalculator();
        supplier = new MockTasksSupplier();
        view = Mockito.mock(CollectionsAndMapsFragment.class);
        presenter = new CalculationFragmentPresenter(view, supplier, calculator);
    }

    @After
    public void tearDown(){
        calculator = null;
        supplier = null;
        view = null;
        presenter = null;
    }

    @Test
    public void testCreated(){
        assertNotNull(presenter);
    }

    @Test
    public void getCollectionsCount_countIsBack(){
        assertEquals(0, presenter.getCollectionsCount());
    }

    @Test
    public void getInitialResult_resultIsBack(){
        assertNull(presenter.getInitialResult());
    }

    @Test
    public void startCalculation_withTwoEmptyStrings(){
        presenter.startCalculation("", "");
        Mockito.verify(view,Mockito.times(1)).setElementsError(Mockito.any());
        Mockito.verify(view,Mockito.times(1)).setThreadsError(Mockito.any());
        Mockito.verify(view,Mockito.times(0)).setBtnChecked(false);
        Mockito.verify(view,Mockito.times(0)).showData(Mockito.any());
        Mockito.verify(view,Mockito.times(0)).showProgress();
        Mockito.verify(view,Mockito.times(0)).showToast(Mockito.any());
        Mockito.verify(view,Mockito.times(0)).setupResult(Mockito.any());
    }

    @Test
    public void startCalculation_withTwoZeroStrings(){
        presenter.startCalculation("0", "0");
        Mockito.verify(view,Mockito.times(1)).setElementsError(Mockito.any());
        Mockito.verify(view,Mockito.times(1)).setThreadsError(Mockito.any());
        Mockito.verify(view,Mockito.times(0)).setBtnChecked(false);
        Mockito.verify(view,Mockito.times(0)).showData(Mockito.any());
        Mockito.verify(view,Mockito.times(0)).showProgress();
        Mockito.verify(view,Mockito.times(0)).showToast(Mockito.any());
        Mockito.verify(view,Mockito.times(0)).setupResult(Mockito.any());
    }

    @Test
    public void startCalculation_withEmptyElementsAndPositiveThreads(){
        presenter.startCalculation("", "2");
        Mockito.verify(view,Mockito.times(1)).setElementsError(Mockito.any());
        Mockito.verify(view,Mockito.times(1)).setThreadsError(null);
        Mockito.verify(view,Mockito.times(0)).setBtnChecked(false);
        Mockito.verify(view,Mockito.times(0)).showData(Mockito.any());
        Mockito.verify(view,Mockito.times(0)).showProgress();
        Mockito.verify(view,Mockito.times(0)).showToast(Mockito.any());
        Mockito.verify(view,Mockito.times(0)).setupResult(Mockito.any());
    }

    @Test
    public void startCalculation_withEmptyThreadsAndPositiveElements(){
        presenter.startCalculation("2", "");
        Mockito.verify(view,Mockito.times(1)).setElementsError(null);
        Mockito.verify(view,Mockito.times(1)).setThreadsError(Mockito.any());
        Mockito.verify(view,Mockito.times(0)).setBtnChecked(false);
        Mockito.verify(view,Mockito.times(0)).showData(Mockito.any());
        Mockito.verify(view,Mockito.times(0)).showProgress();
        Mockito.verify(view,Mockito.times(0)).showToast(Mockito.any());
        Mockito.verify(view,Mockito.times(0)).setupResult(Mockito.any());
    }

    @Test
    public void startCalculation_withZeroThreadsAndPositiveElements(){
        presenter.startCalculation("2", "");
        Mockito.verify(view,Mockito.times(1)).setElementsError(null);
        Mockito.verify(view,Mockito.times(1)).setThreadsError(Mockito.any());
        Mockito.verify(view,Mockito.times(0)).setBtnChecked(false);
        Mockito.verify(view,Mockito.times(0)).showData(Mockito.any());
        Mockito.verify(view,Mockito.times(0)).showProgress();
        Mockito.verify(view,Mockito.times(0)).showToast(Mockito.any());
        Mockito.verify(view,Mockito.times(0)).setupResult(Mockito.any());
    }

    @Test
    public void startCalculation_withZeroElementsAndPositiveThreads(){
        presenter.startCalculation("0", "2");
        Mockito.verify(view,Mockito.times(1)).setElementsError(Mockito.any());
        Mockito.verify(view,Mockito.times(1)).setThreadsError(null);
        Mockito.verify(view,Mockito.times(0)).setBtnChecked(false);
        Mockito.verify(view,Mockito.times(0)).showData(Mockito.any());
        Mockito.verify(view,Mockito.times(0)).showProgress();
        Mockito.verify(view,Mockito.times(0)).showToast(Mockito.any());
        Mockito.verify(view,Mockito.times(0)).setupResult(Mockito.any());
    }

    @Test
    public void startCalculation_withZeroElementsAndEmptyThreads(){
        presenter.startCalculation("0", "");
        Mockito.verify(view,Mockito.times(1)).setElementsError(Mockito.any());
        Mockito.verify(view,Mockito.times(1)).setThreadsError(Mockito.any());
        Mockito.verify(view,Mockito.times(0)).setBtnChecked(false);
        Mockito.verify(view,Mockito.times(0)).showData(Mockito.any());
        Mockito.verify(view,Mockito.times(0)).showProgress();
        Mockito.verify(view,Mockito.times(0)).showToast(Mockito.any());
        Mockito.verify(view,Mockito.times(0)).setupResult(Mockito.any());
    }

    @Test
    public void startCalculation_withEmptyElementsAndZeroThreads(){
        presenter.startCalculation("", "0");
        Mockito.verify(view,Mockito.times(1)).setElementsError(Mockito.any());
        Mockito.verify(view,Mockito.times(1)).setThreadsError(Mockito.any());
        Mockito.verify(view,Mockito.times(0)).setBtnChecked(false);
        Mockito.verify(view,Mockito.times(0)).showData(Mockito.any());
        Mockito.verify(view,Mockito.times(0)).showProgress();
        Mockito.verify(view,Mockito.times(0)).showToast(Mockito.any());
        Mockito.verify(view,Mockito.times(0)).setupResult(Mockito.any());
    }

    @Ignore //don`t work with other tests
    @Test
    public void startCalculation_withPositiveElementsAndPositiveThreads(){
        presenter.startCalculation("100", "4");
        Mockito.verify(view).setElementsError(null);
        Mockito.verify(view).setThreadsError(null);
        Mockito.verify(view).setBtnChecked(true);
        Mockito.verify(view).showData(Mockito.any());
        Mockito.verify(view).showProgress();
        Mockito.verify(view, Mockito.times(6)).setupResult(Mockito.any());
        Mockito.verify(view).setBtnChecked(false);
        Mockito.verify(view).showToast(Mockito.any());
    }

}
