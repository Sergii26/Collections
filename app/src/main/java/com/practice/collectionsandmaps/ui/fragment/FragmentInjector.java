package com.practice.collectionsandmaps.ui.fragment;

import com.practice.collectionsandmaps.models.suppliers.CollectionsTasksSupplier;
import com.practice.collectionsandmaps.models.suppliers.MapsTasksSupplier;
import com.practice.collectionsandmaps.models.workers.CollectionTimeCalculator;
import com.practice.collectionsandmaps.models.workers.MapTimeCalculator;

public class FragmentInjector {
    static CalculationFragmentContract.Presenter createPresenter(CalculationFragmentContract.FragmentView view, int mode) {
        return new CalculationFragmentPresenter(view,
                FragmentsIndication.COLLECTION == mode ?
                new CollectionsTasksSupplier() : new MapsTasksSupplier(),
                FragmentsIndication.COLLECTION == mode ?
                new CollectionTimeCalculator() : new MapTimeCalculator());
    }

}
