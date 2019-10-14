package com.practice.collectionsandmaps.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.practice.collectionsandmaps.R;
import com.practice.collectionsandmaps.model.Task;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CollectionsRecyclerAdapter extends RecyclerView.Adapter<CollectionsRecyclerAdapter.CollectionsTasksViewHolder>{

    private ArrayList<Task> tasks;

    public CollectionsRecyclerAdapter(){
        tasks = new ArrayList<>();
    }

    public void setTasks(ArrayList<Task> tasks) {
        this.tasks = tasks;
        this.notifyDataSetChanged();
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    @NonNull
    @Override
    public CollectionsTasksViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
    View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.task_item, viewGroup, false);
        return new CollectionsTasksViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CollectionsTasksViewHolder collectionsTasksViewHolder, int i) {
        Task task = tasks.get(i);
        collectionsTasksViewHolder.tvNameOfTask.setText(task.getTaskTitle());
        collectionsTasksViewHolder.tvTimeForTask.setText(task.getTimeForTask());
        if(task.getTimeForTask().equals("N/A ms")) {
            collectionsTasksViewHolder.progressBar.setVisibility(View.VISIBLE);
        } else {
            collectionsTasksViewHolder.progressBar.setVisibility(View.INVISIBLE);
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
    }
}
