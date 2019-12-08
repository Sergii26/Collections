package com.practice.collectionsandmaps.rules;

import android.app.Activity;
import android.support.test.rule.ActivityTestRule;

import com.practice.collectionsandmaps.App;
import com.practice.collectionsandmaps.DaggerAppTestComponent;


public class FragmentTestRule<T extends Activity> extends ActivityTestRule {


    public FragmentTestRule(Class activityClass) {
        super(activityClass);
    }

    @Override
    protected void beforeActivityLaunched() {
        super.beforeActivityLaunched();
        App.getInstance().setComponents(DaggerAppTestComponent.create());
    }
}
