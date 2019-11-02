package com.practice.collectionsandmaps.ui.fragment;

import com.practice.collectionsandmaps.dto.TaskData;

import java.util.List;

public interface FragmentView {
    void showData(List<TaskData> tasks);
    void updateData();
    void showError(String error);
    String getStringFromResources(int stringId);
    void setBtnChecked(boolean isChecked);
    void hideProgress();
    void showProgress();
}


