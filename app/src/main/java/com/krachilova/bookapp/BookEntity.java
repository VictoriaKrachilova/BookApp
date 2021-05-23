package com.krachilova.bookapp;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class BookEntity {
    @PrimaryKey
    @ColumnInfo(name = "id")
    public int id;
    @ColumnInfo(name = "title")
    public String title;
    @ColumnInfo(name = "oldPrice")
    public String oldPrice;
    @ColumnInfo(name = "newPrice")
    public String newPrice;
    @ColumnInfo(name = "author")
    public String author;
    @ColumnInfo(name = "dateTime")
    public String dateTime;
}
