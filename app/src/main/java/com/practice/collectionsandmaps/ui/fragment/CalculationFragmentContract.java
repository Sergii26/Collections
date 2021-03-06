package com.practice.collectionsandmaps.ui.fragment;

import com.practice.collectionsandmaps.dto.CalculationResult;
import com.practice.collectionsandmaps.dto.TaskData;

import java.util.List;

public class CalculationFragmentContract {
    public interface Presenter {
        int getCollectionsCount();
        void startCalculation(String amountOfElements, String amountOfThreads);
        void stopCalculation(boolean showMsg);
        List<TaskData> getInitialResult();
    }

    public interface FragmentView {
        void showData(List<TaskData> tasks);
        void showToast(String text);
        String getStringFromResources(int stringId);
        void setBtnChecked(boolean isChecked);
        void hideProgress();
        void showProgress();
        void setThreadsError(String error);
        void setElementsError(String error);
        void setupResult(CalculationResult result);
        void calculationStopped();
    }
}
