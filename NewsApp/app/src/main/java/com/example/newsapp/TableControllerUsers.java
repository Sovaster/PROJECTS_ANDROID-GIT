package com.example.newsapp;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Debug;
import android.util.Log;

public class TableControllerUsers extends DatabaseHandler
{

    public TableControllerUsers(Context context) {
        super(context);
    }

    public User readSingleRecord(String login)
    {
        User user = null;

        String sql = "SELECT * FROM users WHERE Login = '" + login + "'";

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(sql, null);

        if (cursor.moveToFirst()) {

            @SuppressLint("Range") int id = Integer.parseInt(cursor.getString(cursor.getColumnIndex("ID")));
            @SuppressLint("Range") String userLogin = cursor.getString(cursor.getColumnIndex("Login"));
            @SuppressLint("Range") String password = cursor.getString(cursor.getColumnIndex("Password"));
            @SuppressLint("Range") int role = Integer.parseInt(cursor.getString(cursor.getColumnIndex("Role")));

            user = new User();
            user.ID = id;
            user.Login = userLogin;
            user.Password = password;
            user.UserRole = UserRole.values()[role];
        }

        cursor.close();
        db.close();

        return user;
    }

    public boolean create(User user) {

        ContentValues values = new ContentValues();

        values.put("Login", user.Login);
        values.put("Password", user.Password);
        values.put("Role", user.UserRole.ordinal());

        SQLiteDatabase db = this.getWritableDatabase();

        boolean createSuccessful = db.insert("users", null, values) > 0;
        db.close();

        return createSuccessful;
    }

    public User validateUser(User user)
    {
        User newUser = readSingleRecord(user.Login);

        if(newUser == null)
        {
            return null;
        }

        if(user.Password.equals(newUser.Password)) {
           return newUser;
        }

        return null;
    }
}
