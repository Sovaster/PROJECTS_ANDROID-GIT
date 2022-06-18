package com.example.newsapp;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class TableControllerNews extends DatabaseHandler
{
    public TableControllerNews(Context context) {
        super(context);
    }

    public boolean create(New newBlock) {

        ContentValues values = new ContentValues();

        values.put("Title", newBlock.Title);
        values.put("Description", newBlock.Description);

        SQLiteDatabase db = this.getWritableDatabase();

        boolean createSuccessful = db.insert("news", null, values) > 0;
        db.close();

        return createSuccessful;
    }

    public List<New> read()
    {
        List<New> recordsList = new ArrayList<New>();

        String sql = "SELECT * FROM news ORDER BY ID DESC";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(sql, null);

        if (cursor.moveToFirst()) {
            do {

                @SuppressLint("Range") int id = Integer.parseInt(cursor.getString(cursor.getColumnIndex("ID")));
                @SuppressLint("Range") String title = cursor.getString(cursor.getColumnIndex("Title"));
                @SuppressLint("Range") String description = cursor.getString(cursor.getColumnIndex("Description"));

                New newBlock = new New();
                newBlock.ID = id;
                newBlock.Title = title;
                newBlock.Description = description;

                recordsList.add(newBlock);

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return recordsList;
    }

    public boolean delete(int newId)
    {
        boolean deleteSuccessful = false;

        SQLiteDatabase db = this.getWritableDatabase();
        deleteSuccessful = db.delete("news", "ID ='" + newId + "'", null) > 0;
        db.close();

        return deleteSuccessful;
    }

    public New readSingleRecord(int newId)
    {
        New newBlock = null;

        String sql = "SELECT * FROM news WHERE ID = " + newId;

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(sql, null);

        if (cursor.moveToFirst()) {

            @SuppressLint("Range") int id = Integer.parseInt(cursor.getString(cursor.getColumnIndex("ID")));
            @SuppressLint("Range") String title = cursor.getString(cursor.getColumnIndex("Title"));
            @SuppressLint("Range") String description = cursor.getString(cursor.getColumnIndex("Description"));

            newBlock = new New();
            newBlock.ID = id;
            newBlock.Title = title;
            newBlock.Description = description;
        }

        cursor.close();
        db.close();

        return newBlock;
    }

    public boolean update(New newBlock)
    {
        ContentValues values = new ContentValues();

        values.put("Title", newBlock.Title);
        values.put("Description", newBlock.Description);

        String where = "ID = ?";

        String[] whereArgs = { Integer.toString(newBlock.ID) };

        SQLiteDatabase db = this.getWritableDatabase();

        boolean updateSuccess =  db.update("news", values, where, new String[]{Integer.toString(newBlock.ID)}) > 0;
        db.close();

        return updateSuccess;
    }
}
