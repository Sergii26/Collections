package com.practice.collectionsandmaps.models.workers;

import com.practice.collectionsandmaps.dto.TaskData;

public interface TimeCalculator {
    void execAndSetupTime(TaskData task);
}
