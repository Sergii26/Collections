package com.practice.collectionsandmaps;

import com.practice.collectionsandmaps.models.TestTimeCalculatorModule;
import com.practice.collectionsandmaps.models.suppliers.TaskSupplierModule;
import com.practice.collectionsandmaps.models.suppliers.TasksSupplier;
import com.practice.collectionsandmaps.models.workers.TimeCalculator;

import javax.inject.Named;

import dagger.Component;

@Component(modules = {TestTimeCalculatorModule.class, TaskSupplierModule.class})
public interface AppTestComponent extends AppComponent{

    @Named("Maps")
    public TimeCalculator provideMapsTimeCalculator();

    @Named("Collections")
    public TimeCalculator provideCollectionsTimeCalculator();

    @Named("Maps")
    public TasksSupplier provideMapsTasksSupplier();

    @Named("Collections")
    public TasksSupplier provideCollectionsTasksSupplier();
}
