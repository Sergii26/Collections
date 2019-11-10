package com.practice.collectionsandmaps.ui.fragment;

import com.practice.collectionsandmaps.models.suppliers.MapsTasksSupplier;
import com.practice.collectionsandmaps.models.suppliers.TasksSupplier;
import com.practice.collectionsandmaps.models.workers.MapTimeCalculator;
import com.practice.collectionsandmaps.models.workers.TimeCalculator;

import dagger.Module;
import dagger.Provides;

@Module
public class MapsFragmentPresenterModule {

    CalculationFragmentContract.FragmentView view;

    MapsFragmentPresenterModule(CalculationFragmentContract.FragmentView view){
        this.view = view;
    }

    @Provides
    public CalculationFragmentContract.Presenter provideMapsPresenter(){
        return new CalculationFragmentPresenter(view, new MapsTasksSupplier(), new MapTimeCalculator());
    }

    @Provides
    public CalculationFragmentContract.FragmentView provideFragmentView(){
        return view;
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
