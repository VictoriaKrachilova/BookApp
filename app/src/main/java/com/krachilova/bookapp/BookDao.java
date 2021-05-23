package com.krachilova.bookapp;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

@Dao
public interface BookDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE) //ВАЖНО для нашей задачи
    public void insertBook(BookEntity bookEntity);

    @Query("Select * FROM BookEntity where dateTime='now'")
    public BookEntity getOneBook();

    @Query("Select * FROM BookEntity where dateTime!='now'")
    public BookEntity getBook();
}

