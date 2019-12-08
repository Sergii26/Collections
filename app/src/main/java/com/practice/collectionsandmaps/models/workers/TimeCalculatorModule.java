package com.practice.collectionsandmaps.models.workers;

import com.practice.collectionsandmaps.models.suppliers.CollectionsTasksSupplier;
import com.practice.collectionsandmaps.models.suppliers.MapsTasksSupplier;
import com.practice.collectionsandmaps.models.suppliers.TasksSupplier;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

@Module
public class TimeCalculatorModule {
    @Named("Maps")
    @Provides
    public TimeCalculator provideMapsTimeCalculator() {
        return new MapTimeCalculator();
    }

    @Named("Collections")
    @Provides
    public TimeCalculator provideCollectionsTasksSupplier() {
        return new CollectionTimeCalculator();
    }
}
