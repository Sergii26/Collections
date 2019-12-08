package com.practice.collectionsandmaps;

import android.app.Application;

public class App extends Application {
    private static App instance;
    protected AppComponent appComponent;

    public static App getInstance() {
        return instance;
    }

    public AppComponent getAppComponent(){
        return appComponent;
    }

    public void setComponents(AppComponent appComponent){
        this.appComponent = appComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        appComponent = DaggerAppComponent.create();
    }

}
