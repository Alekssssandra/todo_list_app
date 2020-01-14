package com.example.todo_list_app.persistence;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "todo_tasks")
public class TodoTask {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "todo_task_id")
    public long id;

    @ColumnInfo(name = "title")
    public String title;

    @ColumnInfo(name = "note")
    public String note;

    public TodoTask() { }

    public TodoTask(String title, String note) {
        this.title  = title;
        this.note   = note;
    }
}
