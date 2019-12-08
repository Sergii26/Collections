package com.practice.collectionsandmaps;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    private final Context app;


    public AppModule(Context app) {
        this.app = app;

    }

    @Provides
    @Singleton
    public Context getApp() {
        return app;
    }
}
