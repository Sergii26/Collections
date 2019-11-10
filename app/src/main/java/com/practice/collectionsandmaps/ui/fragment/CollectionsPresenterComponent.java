package com.practice.collectionsandmaps.ui.fragment;

import dagger.Component;

@Component(modules = {CollectionsFragmentPresenterModule.class})
public interface CollectionsPresenterComponent {

    CalculationFragmentPresenter getCollectionsFragmentPresenter();
}
