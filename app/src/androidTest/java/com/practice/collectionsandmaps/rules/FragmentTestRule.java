package com.practice.collectionsandmaps.rules;

import android.app.Activity;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;

import com.practice.collectionsandmaps.App;

import com.practice.collectionsandmaps.ui.fragment.FragmentsIndication;
import com.practice.collectionsandmaps.dagger_test.DaggerTestComponent;
import com.practice.collectionsandmaps.dagger_test.TestCalculationFragmentPresenterModule;
import com.practice.collectionsandmaps.dagger_test.TestComponent;

public class FragmentTestRule<T extends Activity> extends ActivityTestRule {


    public FragmentTestRule(Class activityClass) {
        super(activityClass);
    }

    @Override
    protected void beforeActivityLaunched() {
        super.beforeActivityLaunched();


        TestComponent mapsComponent = DaggerTestComponent.builder()
                .testCalculationFragmentPresenterModule(new TestCalculationFragmentPresenterModule(FragmentsIndication.MAP)).build();

        TestComponent collectionsComponent = DaggerTestComponent.builder()
                .testCalculationFragmentPresenterModule(new TestCalculationFragmentPresenterModule(FragmentsIndication.COLLECTION)).build();


        App app = (App) InstrumentationRegistry.getInstrumentation().getTargetContext().getApplicationContext();
        app.setComponents(collectionsComponent, mapsComponent);
    }
}
