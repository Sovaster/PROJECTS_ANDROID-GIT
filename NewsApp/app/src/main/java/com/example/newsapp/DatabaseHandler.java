package com.example.newsapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHandler extends SQLiteOpenHelper
{
    private static final int DATABASE_VERSION = 1;
    protected static final String DATABASE_NAME = "NewsAppDatabase";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String sql1 = "CREATE TABLE users " +
                "( ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "Login TEXT, " +
                "Password TEXT, Role INTEGER) ";

        String sql2 = "CREATE TABLE news " +
                "( ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "Title TEXT, " +
                "Description TEXT) ";

        db.execSQL(sql1);
        db.execSQL(sql2);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        String sql1 = "DROP TABLE IF EXISTS users";
        db.execSQL(sql1);
        String sql2 = "DROP TABLE IF EXISTS news";
        db.execSQL(sql2);

        onCreate(db);
    }
}
