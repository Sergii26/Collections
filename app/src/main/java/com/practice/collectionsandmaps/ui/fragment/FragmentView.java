package com.practice.collectionsandmaps.ui.fragment;

import com.practice.collectionsandmaps.dto.Task;

import java.util.List;

public interface FragmentView {
    void showData(List<Task> tasks);
    void updateData();
    void showError();
}


