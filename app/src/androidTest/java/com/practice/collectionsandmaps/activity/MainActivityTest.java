package com.practice.collectionsandmaps.activity;

import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.practice.collectionsandmaps.R;
import com.practice.collectionsandmaps.ui.MainActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.swipeLeft;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.AllOf.allOf;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule =
            new ActivityTestRule<>(MainActivity.class);

    @Test
    public void testDisplayedMainActivityPager(){
        onView(ViewMatchers.withId(R.id.mainActivityPager)).check(matches(isDisplayed()));
    }

    @Test
    public void testDisplayedTabLayout(){
        onView(withId(R.id.tablayout)).check(matches(isDisplayed()));
    }

    @Test
    public void testNamesOfTabs(){
        onView(withText("Collections")).check(matches(isDisplayed()));
        onView(withText("Maps")).check(matches(isDisplayed()));
    }



    @Test
    public void testButtonAtMapsIsDisplayed(){
        onView(withId(R.id.mainActivityPager)).perform(swipeLeft());
        //for complete swiping
        onView(withId(R.id.mainActivityPager)).perform(swipeLeft());

        onView(allOf(withId(R.id.btnStartOrStop),isDisplayed()))
                .check(matches(isDisplayed()));
    }
}
