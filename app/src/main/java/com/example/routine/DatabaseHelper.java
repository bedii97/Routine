package com.example.routine;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Routine.db";
    public static final String TABLE_NAME = "routine_table";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "Event_Name";
    public static final String COL_3 = "Notification_Message";
    public static final String COL_4 = "Day";
    public static final String COL_5 = "Time";
    public static final String COL_6 = "Repeat_Type";

    public DatabaseHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, EVENT_NAME TEXT, NOTIFICATION_MESSAGE TEXT, DAY ) ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
