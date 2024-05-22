package com.example.test;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

public class DatabaseAdapter {
    private final DatabaseHelper dbHelper;

    public DatabaseAdapter(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    public long insertData(String name, int age) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("age", age);
        long id = db.insert("mytable", null, contentValues);
        db.close();
        return id;
    }

    public Cursor getData() {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String[] columns = {"id", "name", "age"};
        Cursor cursor = db.query("mytable", columns, null, null, null, null, null);
        return cursor;
    }

    public int updateData(int id, String name, int age) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("age", age);
        String whereClause = "id=?";
        String[] whereArgs = {String.valueOf(id)};
        int count = db.update("mytable", contentValues, whereClause, whereArgs);
        db.close();
        return count;
    }

    public int deleteData(int id) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String whereClause = "id=?";
        String[] whereArgs = {String.valueOf(id)};
        int count = db.delete("mytable", whereClause, whereArgs);
        db.close();
        return count;
    }
}
