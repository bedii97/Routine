package com.example.routine.DbHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

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
        Log.d("dbko", "createTableOne: " + DBConstants.T_1_CREATE_QUERY);
        db.execSQL(DBConstants.T_1_CREATE_QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DBConstants.T_1_NAME);
        onCreate(db);
    }

    public long insertDaily(String eventName, String notifMessage, String startedDate, String endedDate, String time, String frequency){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DBConstants.T_1_C_EVENT_NAME, eventName);
        values.put(DBConstants.T_1_C_NOTIFICATION_MESSAGE, notifMessage);
        values.put(DBConstants.T_1_C_STARTED_DATE, startedDate);
        values.put(DBConstants.T_1_C_ENDED_DATE, endedDate);
        values.put(DBConstants.T_1_C_TIME, time);
        long id = db.insert(DBConstants.T_1_NAME, null, values);
        ContentValues values2 = new ContentValues();
        values2.put(DBConstants.T__2_C_ID, id);
        values2.put(DBConstants.T__2_C_FREQUENCY, frequency);
        db.insert(DBConstants.T_2_NAME, null, values2);
        db.close();
        return id;
    }
}
