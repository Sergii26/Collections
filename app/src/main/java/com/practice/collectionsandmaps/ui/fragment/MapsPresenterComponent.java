package com.practice.collectionsandmaps.ui.fragment;

import dagger.Component;

@Component(modules = {MapsFragmentPresenterModule.class})
public interface MapsPresenterComponent {
    CalculationFragmentPresenter getMapsFragmentPresenter();
}
