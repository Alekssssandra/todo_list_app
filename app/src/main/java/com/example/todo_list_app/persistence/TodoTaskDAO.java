package com.example.todo_list_app.persistence;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface TodoTaskDAO {
    @Query("SELECT * FROM todo_tasks")
    List<TodoTask> findAll();

    @Query("SELECT * FROM todo_tasks WHERE todo_task_id = :id")
    TodoTask findById(long id);

    @Insert
    long insert(TodoTask entity);

    @Delete
    void delete(TodoTask entity);

    @Update
    void update(TodoTask entity);
}
