package com.practice.collectionsandmaps.models.workers;

import com.practice.collectionsandmaps.dto.TaskData;
import com.practice.collectionsandmaps.models.workers.TimeCalculator;

public class MockTimeCalculator implements TimeCalculator {
    @Override
    public void execAndSetupTime(TaskData task) {
        try {
            Thread.currentThread().sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        task.setTimeForTask(3);
    }
}
