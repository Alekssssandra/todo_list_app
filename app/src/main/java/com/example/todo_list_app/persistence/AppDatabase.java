package com.example.todo_list_app.persistence;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = { TodoTask.class }, version = 2)
public abstract class AppDatabase extends RoomDatabase {
    private static final String DB_NAME = "todo_list_app_db";
    private static volatile AppDatabase _instance;

    public static synchronized AppDatabase getInstance(Context context) {
        if (_instance == null) {
            _instance = Room
                    .databaseBuilder(context, AppDatabase.class, DB_NAME)
                    .allowMainThreadQueries()
                    .build();
        }
        return _instance;
    }

    public abstract TodoTaskDAO todoTaskDAO();
}
