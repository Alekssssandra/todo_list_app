package com.example.todo_list_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.todo_list_app.persistence.AppDatabase;
import com.example.todo_list_app.persistence.TodoTask;

import java.util.List;

public class MainActivity extends AppCompatActivity implements
        TodoTasksRecyclerAdapter.OnTodoTaskListener,
        TodoTasksRecyclerAdapter.OnTaskDoneButtonListener {
    private static final String         TAG = "ABOGUSZ_MAIN";
    private AppDatabase                 dbHandler;

    private TodoTasksRecyclerAdapter    recyclerAdapter;
    private RecyclerView                recyclerView;
    private RecyclerView.LayoutManager  layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.main_activity_recycler_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        dbHandler = AppDatabase.getInstance(this);
        List<TodoTask> dataset = dbHandler.todoTaskDAO().findAll();
        recyclerAdapter = new TodoTasksRecyclerAdapter(dataset, this, this);
        recyclerView.setAdapter(recyclerAdapter);

        Log.d(TAG, "onCreate: initialization done");
    }

    @Override
    public void onTodoTaskClick(int position) {
        long entityID = recyclerAdapter.dataset.get(position).id;

        Intent intent = new Intent(this, EditTodoTaskActivity.class);
        intent.putExtra("item_id", entityID);
        startActivity(intent);

        Log.d(TAG, String.format("onTodoTaskClick: clicked at position %d", position));
    }

    @Override
    public void onTaskDoneButtonClick(int position) {
        TodoTask entity = recyclerAdapter.dataset.remove(position);
        dbHandler.todoTaskDAO().delete(entity);
        recyclerAdapter.notifyDataSetChanged();
        Log.d(TAG, String.format("onTaskDoneButtonClick: clicked at position %d", position));
    }

    public void addNewTodoTask(View view) {
        long entityID = dbHandler.todoTaskDAO().insert(new TodoTask("title", "description"));

        Intent intent = new Intent(this, EditTodoTaskActivity.class);
        intent.putExtra("item_id", entityID);
        startActivity(intent);

        Log.d(TAG, "addNewTodoTask: clicked");
    }

    @Override
    protected void onStart() {
        super.onStart();
        recyclerAdapter.dataset.clear();
        recyclerAdapter.dataset.addAll(dbHandler.todoTaskDAO().findAll());
        recyclerAdapter.notifyDataSetChanged();
        Log.d(TAG, "onStart: ");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: ");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: ");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: ");
    }
}
