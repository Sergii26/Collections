package com.practice.collectionsandmaps.models;

import com.practice.collectionsandmaps.models.workers.CollectionTimeCalculator;
import com.practice.collectionsandmaps.models.workers.MapTimeCalculator;
import com.practice.collectionsandmaps.models.workers.TimeCalculator;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

@Module
public class TestTimeCalculatorModule {

    @Named("Maps")
    @Provides
    public TimeCalculator provideMapsTimeCalculator() {
        return new TestTimeCalculator();
    }

    @Named("Collections")
    @Provides
    public TimeCalculator provideCollectionsTasksSupplier() {
        return new TestTimeCalculator();
    }
}
