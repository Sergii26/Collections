package com.practice.collectionsandmaps.ui.fragment;

import com.practice.collectionsandmaps.App;

import dagger.Module;
import dagger.Provides;

@Module
public class FragmentModule {

    private CalculationFragmentContract.FragmentView view;
    private int fragmentIndication;

    public FragmentModule(CalculationFragmentContract.FragmentView view, int fragmentIndication) {
        this.view = view;
        this.fragmentIndication = fragmentIndication;
    }

    @Provides
    public CalculationFragmentContract.Presenter providePresenter() {
        return new CalculationFragmentPresenter(fragmentIndication == FragmentsIndication.MAP ? App.getInstance().getAppComponent().provideMapsTasksSupplier() :
                App.getInstance().getAppComponent().provideCollectionsTasksSupplier(),
                fragmentIndication == FragmentsIndication.MAP ? App.getInstance().getAppComponent().provideMapsTimeCalculator() :
                        App.getInstance().getAppComponent().provideCollectionsTimeCalculator(), view);
    }
}

