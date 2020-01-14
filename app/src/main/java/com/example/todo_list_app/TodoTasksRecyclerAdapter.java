package com.example.todo_list_app;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.todo_list_app.persistence.TodoTask;
import java.util.List;

public class TodoTasksRecyclerAdapter extends RecyclerView.Adapter<TodoTasksRecyclerAdapter.ViewHolder> {
    public List<TodoTask>           dataset;
    public OnTodoTaskListener       taskListener;
    public OnTaskDoneButtonListener taskDoneButtonListener;

    public TodoTasksRecyclerAdapter(
            List<TodoTask> dataset,
            OnTodoTaskListener taskListener,
            OnTaskDoneButtonListener taskDoneButtonListener
    ) {
        this.dataset                = dataset;
        this.taskListener           = taskListener;
        this.taskDoneButtonListener = taskDoneButtonListener;
    }

    // ===========================================================================
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.todo_task_list_item, parent, false);

        return new ViewHolder(view, taskListener, taskDoneButtonListener);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.title.setText(dataset.get(position).title);
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }
    // ===========================================================================



    // ===========================================================================
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView                 title;
        public Button                   taskDoneButton;
        public OnTodoTaskListener       taskListener;
        public OnTaskDoneButtonListener taskDoneButtonListener;

        public ViewHolder(View itemView, OnTodoTaskListener taskListener, OnTaskDoneButtonListener taskDoneButtonListener) {
            super(itemView);
            title = itemView.findViewById(R.id.task_title_textview);
            this.taskListener = taskListener;
            this.taskDoneButtonListener = taskDoneButtonListener;
            itemView.setOnClickListener(this);
            taskDoneButton = itemView.findViewById(R.id.task_done_button);
            taskDoneButton.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

            if (v instanceof Button) {
                taskDoneButtonListener.onTaskDoneButtonClick(getAdapterPosition());
            } else {
                taskListener.onTodoTaskClick(getAdapterPosition());
            }
        }
    }
    // ===========================================================================



    // ===========================================================================
    public interface OnTodoTaskListener {
        void onTodoTaskClick(int position);
    }
    // ===========================================================================



    // ===========================================================================
    public interface OnTaskDoneButtonListener {
        void onTaskDoneButtonClick(int position);
    }
    // ===========================================================================
}
