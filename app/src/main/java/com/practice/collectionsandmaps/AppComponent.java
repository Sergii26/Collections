package com.practice.collectionsandmaps;

import com.practice.collectionsandmaps.ui.fragment.CalculationFragmentPresenterModule;
import com.practice.collectionsandmaps.ui.fragment.CollectionsAndMapsFragment;

import dagger.Component;

@Component(modules = {AppModule.class, CalculationFragmentPresenterModule.class})
public interface AppComponent {
    void injectPresenter(CollectionsAndMapsFragment fragment);
}
