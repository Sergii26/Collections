package com.practice.collectionsandmaps.ui.fragment;

import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.practice.collectionsandmaps.R;
import com.practice.collectionsandmaps.dto.CalculationResult;
import com.practice.collectionsandmaps.dto.TaskData;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TasksRecyclerAdapter extends RecyclerView.Adapter<TasksRecyclerAdapter.CollectionsTasksViewHolder>{

    private List<TaskData> tasks = new ArrayList<>();

    public void setTasks(List<TaskData> tasks) {
        this.tasks.clear();
        this.tasks.addAll(tasks);
        this.notifyDataSetChanged();
    }

    public void hideProgress(){
        for(int i = 0; i < tasks.size(); i++){
            tasks.get(i).setShowProgress(false);
        }
        notifyDataSetChanged();
    }

    public void showProgress(){
        for(int i = 0; i < tasks.size(); i++){
            tasks.get(i).setShowProgress(true);
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CollectionsTasksViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.task_item, viewGroup, false);
        return new CollectionsTasksViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CollectionsTasksViewHolder collectionsTasksViewHolder, int i) {
        collectionsTasksViewHolder.bindView(tasks.get(i));
    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }

    public class CollectionsTasksViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.tvNameOfTask)
        TextView tvNameOfTask;
        @BindView(R.id.tvTimeForTask)
        TextView tvTimeForTask;
        @BindView(R.id.progressBar)
        ProgressBar progressBar;

        public CollectionsTasksViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bindView(TaskData task){
            this.tvNameOfTask.setText(task.getNameOfTask());
            if(task.isDefaultTime()){
                this.tvTimeForTask.setText(tvTimeForTask.getContext().getString(R.string.default_time));
            } else {
                this.tvTimeForTask.setText(tvTimeForTask.getContext().getString(R.string.dimension_ms, task.getTimeForTask()));
            }
            this.progressBar.setVisibility(task.getShowProgress() ? View.VISIBLE : View.INVISIBLE);
        }
    }

    public void updateItem(CalculationResult result) {
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getNameOfTask() == result.getNameOfTask()) {
                tasks.set(i, result);
                notifyItemChanged(i);
                break;
            }
        }
    }

}
