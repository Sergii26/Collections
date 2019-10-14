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

public class MapsRecyclerAdapter extends RecyclerView.Adapter<MapsRecyclerAdapter.MapsTasksViewHolder>{

    private ArrayList<Task> tasks;

    public MapsRecyclerAdapter(){
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
    public MapsRecyclerAdapter.MapsTasksViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.task_item, viewGroup, false);
        return new MapsRecyclerAdapter.MapsTasksViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MapsRecyclerAdapter.MapsTasksViewHolder mapsTasksViewHolder, int i) {
        Task task = tasks.get(i);
        mapsTasksViewHolder.tvNameOfTask.setText(task.getTaskTitle());
        mapsTasksViewHolder.tvTimeForTask.setText(task.getTimeForTask());
        if(task.getTimeForTask().equals("N/A ms")) {
            mapsTasksViewHolder.progressBar.setVisibility(View.VISIBLE);
        } else {
            mapsTasksViewHolder.progressBar.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }

    public class MapsTasksViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.tvNameOfTask)
        TextView tvNameOfTask;
        @BindView(R.id.tvTimeForTask)
        TextView tvTimeForTask;
        @BindView(R.id.progressBar)
        ProgressBar progressBar;

        public MapsTasksViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
