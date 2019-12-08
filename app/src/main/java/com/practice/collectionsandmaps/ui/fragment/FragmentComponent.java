package com.practice.collectionsandmaps.ui.fragment;

import dagger.Component;

@Component(modules = {FragmentModule.class})
public interface FragmentComponent {
    void injectsCollectionsAndMapsFragment(CollectionsAndMapsFragment fragment);
}
