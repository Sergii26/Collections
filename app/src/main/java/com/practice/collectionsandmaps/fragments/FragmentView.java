package com.practice.collectionsandmaps.fragments;

import com.practice.collectionsandmaps.model.Task;

import java.util.ArrayList;

public interface FragmentView {
    void showData(ArrayList<Task> tasks);
    void updateData();
}


