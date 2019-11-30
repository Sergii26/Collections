package com.practice.collectionsandmaps.fragments;

import android.content.pm.ActivityInfo;
import android.support.test.espresso.ViewInteraction;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;

import com.practice.collectionsandmaps.R;
import com.practice.collectionsandmaps.RecyclerViewMatcher;
import com.practice.collectionsandmaps.ui.MainActivity;

import org.hamcrest.Matchers;
import org.hamcrest.core.AllOf;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.RecyclerViewActions.scrollToPosition;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static android.support.test.espresso.matcher.ViewMatchers.hasErrorText;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.core.AllOf.allOf;

public class CollectionsFragmentTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule =
            new ActivityTestRule<>(MainActivity.class);

    @Test
    public void testButtonAtCollectionsIsDisplayed() {
        onView(AllOf.allOf(ViewMatchers.withId(R.id.btnStartOrStop), isDisplayed()))
                .check(matches(isDisplayed()));
    }

    @Test
    public void testEditTextElementsIsDisplayed() {
        onView(allOf(withId(R.id.etAmountOfElements), isDisplayed()))
                .check(matches(isDisplayed()));
    }

    @Test
    public void testEditTextThreadsIsDisplayed() {
        onView(allOf(withId(R.id.etAmountOfThreads), isDisplayed()))
                .check(matches(isDisplayed()));
    }

    @Test
    public void testTextViewLabelElementsIsDisplayed() {
        onView(allOf(withId(R.id.tvAmountOfElementsLabel), isDisplayed()))
                .check(matches(isDisplayed()));
    }

    @Test
    public void testTextViewLabelThreadsIsDisplayed() {
        onView(allOf(withId(R.id.tvAmountOfThreadsLabel), isDisplayed()))
                .check(matches(isDisplayed()));
    }

    @Test
    public void testTextViewRecyclerViewIsDisplayed() {
        onView(allOf(withId(R.id.rvTasks), isDisplayed()))
                .check(matches(isDisplayed()));
    }

    @Test
    public void editTextElementsShowingEmptyErrorTest() {
        onView(allOf(withId(R.id.etAmountOfThreads), isDisplayed()))
                .perform(typeText("12"));
        onView(allOf(withId(R.id.btnStartOrStop), isDisplayed()))
                .perform(click());
        onView(allOf(withId(R.id.etAmountOfElements), isDisplayed()))
                .check(matches(hasErrorText(mActivityRule.getActivity().getString(R.string.elements_must_not_be_empty))));
    }

    @Test
    public void editTextThreadsShowingEmptyErrorTest() {
        onView(allOf(withId(R.id.etAmountOfElements), isDisplayed()))
                .perform(typeText("12"));
        onView(allOf(withId(R.id.btnStartOrStop), isDisplayed()))
                .perform(click());
        onView(allOf(withId(R.id.etAmountOfThreads), isDisplayed()))
                .check(matches(hasErrorText(mActivityRule.getActivity().getString(R.string.threads_must_not_be_empty))));

    }

    @Test
    public void editTextThreadsShowingMoreThanNullErrorTest() {
        onView(allOf(withId(R.id.etAmountOfElements), isDisplayed()))
                .perform(typeText("12"));
        onView(allOf(withId(R.id.etAmountOfThreads), isDisplayed()))
                .perform(typeText("0"));
        onView(allOf(withId(R.id.btnStartOrStop), isDisplayed()))
                .perform(click());
        onView(allOf(withId(R.id.etAmountOfThreads), isDisplayed()))
                .check(matches(hasErrorText(mActivityRule.getActivity().getString(R.string.enter_threads))));

    }

    @Test
    public void editTextElementsShowingMoreThanNullErrorTest() {
        onView(allOf(withId(R.id.etAmountOfThreads), isDisplayed()))
                .perform(typeText("12"));
        onView(allOf(withId(R.id.etAmountOfElements), isDisplayed()))
                .perform(typeText("0"));
        onView(allOf(withId(R.id.btnStartOrStop), isDisplayed()))
                .perform(click());
        onView(allOf(withId(R.id.etAmountOfElements), isDisplayed()))
                .check(matches(hasErrorText(mActivityRule.getActivity().getString(R.string.enter_elements))));

    }

    @Test
    public void bothEditTextsShowingEmptyErrorTest() {
        onView(allOf(withId(R.id.btnStartOrStop), isDisplayed()))
                .perform(click());
        onView(allOf(withId(R.id.etAmountOfElements), isDisplayed()))
                .check(matches(hasErrorText(mActivityRule.getActivity().getString(R.string.elements_must_not_be_empty))));
        onView(allOf(withId(R.id.etAmountOfThreads), isDisplayed()))
                .check(matches(hasErrorText(mActivityRule.getActivity().getString(R.string.threads_must_not_be_empty))));
    }

    @Test
    public void bothEditTextsShowingMoreThanNullErrorTest() {
        onView(allOf(withId(R.id.etAmountOfThreads), isDisplayed()))
                .perform(typeText("0"));
        onView(allOf(withId(R.id.etAmountOfElements), isDisplayed()))
                .perform(typeText("0"));
        onView(allOf(withId(R.id.btnStartOrStop), isDisplayed()))
                .perform(click());
        onView(allOf(withId(R.id.etAmountOfElements), isDisplayed()))
                .check(matches(hasErrorText(mActivityRule.getActivity().getString(R.string.enter_elements))));
        onView(allOf(withId(R.id.etAmountOfThreads), isDisplayed()))
                .check(matches(hasErrorText(mActivityRule.getActivity().getString(R.string.enter_threads))));
    }

    @Test
    public void showingDefaultTimeAtStartTest() {
        ViewInteraction view = onView(allOf(withId(R.id.rvTasks), isDisplayed()));
        for (int i = 0; i < 21; i++) {
            view.perform(scrollToPosition(i));
            view.check(matches(RecyclerViewMatcher.atPosition(i, hasDescendant(withText(R.string.default_time)))));
        }
    }

    @Test
    public void showingCalculationResultTest(){
        onView(allOf(withId(R.id.etAmountOfThreads), isDisplayed()))
                .perform(typeText("4"));
        onView(allOf(withId(R.id.etAmountOfElements), isDisplayed()))
                .perform(typeText("120"));
        onView(allOf(withId(R.id.btnStartOrStop), isDisplayed()))
                .perform(click());

        ViewInteraction view = onView(allOf(withId(R.id.rvTasks), isDisplayed()));
        for (int i = 0; i < 21; i++) {
            view.perform(scrollToPosition(i));
            view.check(matches(RecyclerViewMatcher.atPosition(i, hasDescendant(allOf(withId(R.id.tvTimeForTask), not(withText(R.string.default_time)))))));
        }
    }

    @Test
    public void showingFinishedToastTest(){
        onView(allOf(withId(R.id.etAmountOfThreads), isDisplayed()))
                .perform(typeText("4"));
        onView(allOf(withId(R.id.etAmountOfElements), isDisplayed()))
                .perform(typeText("120"));
        onView(allOf(withId(R.id.btnStartOrStop), isDisplayed()))
                .perform(click());

        onView(withText(R.string.calculation_finished))
                .inRoot(withDecorView(not(Matchers.is(mActivityRule.getActivity().getWindow().getDecorView()))))
                .check(matches(isDisplayed()));
    }

    @Test
    public void showingStopCalculationToastTest(){
        onView(allOf(withId(R.id.etAmountOfThreads), isDisplayed()))
                .perform(typeText("4"));
        onView(allOf(withId(R.id.etAmountOfElements), isDisplayed()))
                .perform(typeText("1000000"));
        onView(allOf(withId(R.id.btnStartOrStop), isDisplayed()))
                .perform(click(), click());

        onView(withText(R.string.calculation_stopped))
                .inRoot(withDecorView(not(Matchers.is(mActivityRule.getActivity().getWindow().getDecorView()))))
                .check(matches(isDisplayed()));
    }

    @Test
    public void showingCalculationResultAfterStopTest(){
        onView(allOf(withId(R.id.etAmountOfThreads), isDisplayed()))
                .perform(typeText("4"));
        onView(allOf(withId(R.id.etAmountOfElements), isDisplayed()))
                .perform(typeText("1500000"));
        onView(allOf(withId(R.id.btnStartOrStop), isDisplayed()))
                .perform(click(), click());

        ViewInteraction view = onView(allOf(withId(R.id.rvTasks), isDisplayed()));

        //checking that screen has view with not changed time (default time)
        view.check(matches(hasDescendant(allOf(withId(R.id.tvTimeForTask), withText(R.string.default_time)))));
    }

    @Test
    public void savingStatesOfCalculationResultAfterChangeOrientationTest(){
        onView(allOf(withId(R.id.etAmountOfThreads), isDisplayed()))
                .perform(typeText("4"));
        onView(allOf(withId(R.id.etAmountOfElements), isDisplayed()))
                .perform(typeText("10"));
        onView(allOf(withId(R.id.btnStartOrStop), isDisplayed()))
                .perform(click());
        ViewInteraction view = onView(allOf(withId(R.id.rvTasks), isDisplayed()));
        for (int i = 0; i < 21; i++) {
            view.perform(scrollToPosition(i));
            view.check(matches(RecyclerViewMatcher.atPosition(i, hasDescendant(allOf(withId(R.id.tvTimeForTask), not(withText(R.string.default_time)))))));
        }

        mActivityRule.getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        for (int i = 0; i < 21; i++) {
            view.perform(scrollToPosition(i));
            view.check(matches(RecyclerViewMatcher.atPosition(i, hasDescendant(allOf(withId(R.id.tvTimeForTask), not(withText(R.string.default_time)))))));
        }
    }

}
