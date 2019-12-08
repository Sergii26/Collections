package com.practice.collectionsandmaps.ui.fragment;

import com.practice.collectionsandmaps.App;
import com.practice.collectionsandmaps.AppComponent;

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
        final AppComponent appComponent = App.getInstance().getAppComponent();
        return new CalculationFragmentPresenter(fragmentIndication == FragmentsIndication.MAP ? appComponent.provideMapsTasksSupplier() :
                appComponent.provideCollectionsTasksSupplier(),
                fragmentIndication == FragmentsIndication.MAP ? appComponent.provideMapsTimeCalculator() :
                        appComponent.provideCollectionsTimeCalculator(), view);
    }
}

