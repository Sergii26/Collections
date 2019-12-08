package com.practice.collectionsandmaps.models.suppliers;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

@Module
public class TaskSupplierModule {
    @Named("Maps")
    @Provides
    public TasksSupplier provideMapsTaskSupplier() {
        return new MapsTasksSupplier();
    }

    @Named("Collections")
    @Provides
    public TasksSupplier provideCollectionsTasksSupplier() {
        return new CollectionsTasksSupplier();
    }
}
