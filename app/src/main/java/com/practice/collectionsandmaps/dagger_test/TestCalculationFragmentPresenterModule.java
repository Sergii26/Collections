package com.practice.collectionsandmaps.dagger_test;


import com.practice.collectionsandmaps.models.suppliers.CollectionsTasksSupplier;
import com.practice.collectionsandmaps.models.suppliers.MapsTasksSupplier;
import com.practice.collectionsandmaps.models.suppliers.TasksSupplier;
import com.practice.collectionsandmaps.models.workers.MockTimeCalculator;
import com.practice.collectionsandmaps.models.workers.TimeCalculator;
import com.practice.collectionsandmaps.ui.fragment.CalculationFragmentContract;
import com.practice.collectionsandmaps.ui.fragment.CalculationFragmentPresenter;
import com.practice.collectionsandmaps.ui.fragment.FragmentsIndication;

import dagger.Module;
import dagger.Provides;

@Module
public class TestCalculationFragmentPresenterModule {

    private int indicator;

    public TestCalculationFragmentPresenterModule(int indicator){
        this.indicator = indicator;
    }

    @Provides
    public CalculationFragmentContract.Presenter providePresenter(){
        if(indicator == FragmentsIndication.COLLECTION) {
            return new CalculationFragmentPresenter(new CollectionsTasksSupplier(), new MockTimeCalculator());
        }
        return new CalculationFragmentPresenter(new MapsTasksSupplier(), new MockTimeCalculator());
    }

    @Provides
    public TasksSupplier provideCollectionsSupplier(){
        return new CollectionsTasksSupplier();
    }

    @Provides
    public TimeCalculator provideCalculator(){
        return new MockTimeCalculator();
    }

    @Provides
    public TasksSupplier provideMapsSupplier(){
        return new MapsTasksSupplier();
    }

}
