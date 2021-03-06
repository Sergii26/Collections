package com.practice.collectionsandmaps;

import com.practice.collectionsandmaps.models.suppliers.TaskSupplierModule;
import com.practice.collectionsandmaps.models.suppliers.TasksSupplier;
import com.practice.collectionsandmaps.models.workers.TimeCalculator;
import com.practice.collectionsandmaps.models.workers.TimeCalculatorModule;

import javax.inject.Named;

import dagger.Component;

@Component(modules = {TaskSupplierModule.class, TimeCalculatorModule.class})
public interface AppComponent {

    @Named("Maps")
    public TimeCalculator provideMapsTimeCalculator();

    @Named("Collections")
    public TimeCalculator provideCollectionsTimeCalculator();

    @Named("Maps")
    public TasksSupplier provideMapsTasksSupplier();

    @Named("Collections")
    public TasksSupplier provideCollectionsTasksSupplier();
}
