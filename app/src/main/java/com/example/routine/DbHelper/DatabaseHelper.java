package com.example.routine.DbHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    public DatabaseHelper(@Nullable Context context) {
        super(context, DBConstants.DB_NAME, null, DBConstants.DB_VERSION);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        createTableOne(db); //Main Table
        createTableTwo(db); //Daily Table

    }

    private void createTableTwo(SQLiteDatabase db) {
        db.execSQL(DBConstants.T_2_CREATE_QUERY);
    }

    private void createTableOne(SQLiteDatabase db) {
        db.execSQL(DBConstants.T_1_CREATE_QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DBConstants.T_1_NAME);
        onCreate(db);
    }

    /*public long insertDaily(String eventName, String notifMessage, String currentDate, String endDate, String currentTime, String frequency){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DBConstants.T_1_C_EVENT_NAME, eventName);
        values.put(DBConstants.T_1_C_EVENT_NAME, eventName);
        values.put(DBConstants.T_1_C_EVENT_NAME, eventName);
        values.put(DBConstants.T_1_C_EVENT_NAME, eventName);
        values.put(DBConstants.T_1_C_EVENT_NAME, eventName);
        values.put(DBConstants.T_1_C_EVENT_NAME, eventName);
    }*/
}
