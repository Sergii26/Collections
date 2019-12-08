package com.practice.collectionsandmaps.idling_resources;

import android.app.Activity;
import android.support.test.espresso.IdlingResource;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.practice.collectionsandmaps.R;
import com.practice.collectionsandmaps.dto.TaskData;
import com.practice.collectionsandmaps.ui.fragment.TasksRecyclerAdapter;

import java.util.List;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.core.AllOf.allOf;


public class BackgroundWorkIdlingResources implements IdlingResource {

    final private String resourceName;
    final private Activity activity;
    final private RecyclerView rv;

    private volatile ResourceCallback resourceCallback;

    public BackgroundWorkIdlingResources(Activity activity, String resourceName, RecyclerView rv) {
        this.resourceName = resourceName;
        this.activity = activity;
        this.rv = rv;
    }

    @Override
    public String getName() {
        return resourceName;
    }

    @Override
    public boolean isIdleNow() {

        TasksRecyclerAdapter adapter = (TasksRecyclerAdapter)rv.getAdapter();
        List<TaskData> tasks = adapter.getTasks();
        Log.d("testLogs", tasks.size() + "");
        double time = tasks.get(tasks.size() - 1).getTimeForTask();

        if (time != -1) {
            resourceCallback.onTransitionToIdle();
        }

        return time != -1;
    }

    @Override
    public void registerIdleTransitionCallback(ResourceCallback callback) {
        this.resourceCallback = callback;
    }

}
