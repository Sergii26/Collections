package com.practice.collectionsandmaps.dagger_test;

import com.practice.collectionsandmaps.AppComponent;
import com.practice.collectionsandmaps.ui.fragment.CollectionsAndMapsFragment;

import dagger.Component;

@Component(modules = {TestCalculationFragmentPresenterModule.class})
public interface TestComponent extends AppComponent {

    void injectPresenter(CollectionsAndMapsFragment fragment);
}
