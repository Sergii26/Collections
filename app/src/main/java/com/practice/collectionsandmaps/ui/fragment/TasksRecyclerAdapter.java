package com.practice.collectionsandmaps.ui.fragment;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.practice.collectionsandmaps.R;
import com.practice.collectionsandmaps.dto.Task;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TasksRecyclerAdapter extends RecyclerView.Adapter<TasksRecyclerAdapter.CollectionsTasksViewHolder>{

    private List<Task> tasks = new ArrayList<>();
    private boolean stopCalculation;

    public TasksRecyclerAdapter(){
    }

    public void setStopCalculation(boolean stopCalculation) {
        this.stopCalculation = stopCalculation;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks.clear();
        this.tasks.addAll(tasks);
        this.notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CollectionsTasksViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
    View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.task_item, viewGroup, false);
        return new CollectionsTasksViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CollectionsTasksViewHolder collectionsTasksViewHolder, int i) {
        if(stopCalculation) {
            collectionsTasksViewHolder.bindViewAfterStop(tasks.get(i));
        } else {
            collectionsTasksViewHolder.bindView(tasks.get(i));
        }
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

        public void bindView(Task task){
            this.tvNameOfTask.setText(task.getTaskTitle());
            this.tvTimeForTask.setText(task.getTimeForTask());
            if(task.isDefaultTime()) {
                this.progressBar.setVisibility(View.VISIBLE);
            } else {
                this.progressBar.setVisibility(View.INVISIBLE);
            }
        }

        public void bindViewAfterStop(Task task){
            this.tvNameOfTask.setText(task.getTaskTitle());
            this.tvTimeForTask.setText(task.getTimeForTask());
            this.progressBar.setVisibility(View.INVISIBLE);
        }
    }
}
