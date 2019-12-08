package com.practice.collectionsandmaps;

import android.app.Application;

import com.practice.collectionsandmaps.ui.fragment.CalculationFragmentPresenterModule;
import com.practice.collectionsandmaps.ui.fragment.FragmentsIndication;

public class App extends Application {
    private static App instance;
    private AppComponent mapsComponent;
    private AppComponent collectionsComponent;

    public static App getInstance() {
        return instance;
    }

    public void setComponents(AppComponent collectionsComponent, AppComponent mapsComponent){
        this.collectionsComponent = collectionsComponent;
        this.mapsComponent = mapsComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        mapsComponent = DaggerAppComponent.builder()
                .calculationFragmentPresenterModule(new CalculationFragmentPresenterModule(FragmentsIndication.MAP)).build();
        collectionsComponent = DaggerAppComponent.builder()
                .calculationFragmentPresenterModule(new CalculationFragmentPresenterModule(FragmentsIndication.COLLECTION)).build();
    }

    public AppComponent getCollectionsComponent() {
        return collectionsComponent;
    }

    public AppComponent getMapsComponent() {
        return mapsComponent;
    }
}
