package com.practice.collectionsandmaps.ui.fragment;

import com.practice.collectionsandmaps.models.suppliers.CollectionsTasksSupplier;
import com.practice.collectionsandmaps.models.suppliers.MapsTasksSupplier;
import com.practice.collectionsandmaps.models.suppliers.TasksSupplier;
import com.practice.collectionsandmaps.models.workers.CollectionTimeCalculator;
import com.practice.collectionsandmaps.models.workers.MapTimeCalculator;
import com.practice.collectionsandmaps.models.workers.TimeCalculator;

import dagger.Module;
import dagger.Provides;

@Module
public class CalculationFragmentPresenterModule {

    private int indicator;

    public CalculationFragmentPresenterModule(int indicator){
        this.indicator = indicator;
    }

    @Provides
    public CalculationFragmentContract.Presenter providePresenter(){
        if(indicator == FragmentsIndication.COLLECTION) {
            return new CalculationFragmentPresenter(new CollectionsTasksSupplier(), new CollectionTimeCalculator());
        }
        return new CalculationFragmentPresenter(new MapsTasksSupplier(), new MapTimeCalculator());
    }

    @Provides
    public TasksSupplier provideCollectionsSupplier(){
        return new CollectionsTasksSupplier();
    }

    @Provides
    public TimeCalculator provideCollectionCalculator(){
        return new CollectionTimeCalculator();
    }

    @Provides
    public TasksSupplier provideMapsSupplier(){
        return new MapsTasksSupplier();
    }

    @Provides
    public TimeCalculator provideMapCalculator(){
        return new MapTimeCalculator();
    }
}
