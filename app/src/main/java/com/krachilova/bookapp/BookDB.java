package com.krachilova.bookapp;


import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {BookEntity.class},version=1)
public abstract class BookDB extends RoomDatabase {
    public static BookDB instance;
    public abstract BookDao bookDao();
}


