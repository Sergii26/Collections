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
public class CollectionsFragmentPresenterModule {

    CalculationFragmentContract.FragmentView view;

    CollectionsFragmentPresenterModule(CalculationFragmentContract.FragmentView view){
        this.view = view;
    }

    @Provides
    public CalculationFragmentContract.Presenter provideCollectionsPresenter(){
        return new CalculationFragmentPresenter(view, new CollectionsTasksSupplier(), new CollectionTimeCalculator());
    }



    @Provides
    public CalculationFragmentContract.FragmentView provideFragmentView(){
        return view;
    }

    @Provides
    public TasksSupplier provideCollectionsSupplier(){
        return new CollectionsTasksSupplier();
    }



    @Provides
    public TimeCalculator provideCollectionCalculator(){
        return new CollectionTimeCalculator();
    }



}
