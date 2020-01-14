package com.example.todo_list_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import com.example.todo_list_app.persistence.AppDatabase;
import com.example.todo_list_app.persistence.TodoTask;

public class EditTodoTaskActivity extends AppCompatActivity {
    private static final String TAG = "ABOGUSZ_EDIT_TODO_TASK";

    private AppDatabase dbHandler;
    private long        entityID;
    private EditText    editTextTitle;
    private EditText    editTextNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_todo_task);

        dbHandler       = AppDatabase.getInstance(this);
        editTextTitle   = findViewById(R.id.editTextTitle);
        editTextNote    = findViewById(R.id.editTextNote);
        Intent intent   = getIntent();
        entityID        = intent.getLongExtra("item_id", -1L);

        if (entityID == -1) {
            throw new RuntimeException("missing item_id");
        }

        TodoTask entity = dbHandler.todoTaskDAO().findById(entityID);

        editTextTitle.setText(entity.title);
        editTextNote.setText(entity.note);
    }



    @Override
    protected void onPause() {
        super.onPause();
        TodoTask entity = dbHandler.todoTaskDAO().findById(entityID);
        entity.title    = editTextTitle.getText().toString();
        entity.note     = editTextNote.getText().toString();
        dbHandler.todoTaskDAO().update(entity);
        Log.d(TAG, "onPause: ");
    }


}
