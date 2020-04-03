package com.example.routine.DbHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.routine.Model.DailyReminder;
import com.example.routine.Model.Reminder;

import java.util.ArrayList;

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
        values.put(DBConstants.T_1_C_TYPE, DBConstants.TYPE_DAILY);
        long id = db.insert(DBConstants.T_1_NAME, null, values);
        ContentValues values2 = new ContentValues();
        values2.put(DBConstants.T__2_C_ID, id);
        values2.put(DBConstants.T__2_C_FREQUENCY, frequency);
        db.insert(DBConstants.T_2_NAME, null, values2);
        db.close();
        return id;
    }

    public ArrayList<Reminder> getReminder(){ //orderby parametresi alarak sıralama yapabilirim
        ArrayList<Reminder> reminders = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + DBConstants.T_1_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if(cursor.moveToNext()){
            do {
                Reminder reminder = new Reminder(
                        cursor.getInt(cursor.getColumnIndex(DBConstants.T_1_C_ID)),
                        ""+cursor.getString(cursor.getColumnIndex(DBConstants.T_1_C_EVENT_NAME)),
                        ""+cursor.getString(cursor.getColumnIndex(DBConstants.T_1_C_NOTIFICATION_MESSAGE)),
                        ""+cursor.getString(cursor.getColumnIndex(DBConstants.T_1_C_STARTED_DATE)),
                        ""+cursor.getString(cursor.getColumnIndex(DBConstants.T_1_C_ENDED_DATE)),
                        ""+cursor.getString(cursor.getColumnIndex(DBConstants.T_1_C_TIME)),
                        ""+cursor.getString(cursor.getColumnIndex(DBConstants.T_1_C_TYPE))
                );
                switch (reminder.getType()){
                    case DBConstants.TYPE_DAILY:
                        String selectDailtQuery = "SELECT * FROM " + DBConstants.T_2_NAME + " WHERE " + DBConstants.T__2_C_ID + " = " + reminder.getID();
                        Cursor cursorDaily = db.rawQuery(selectDailtQuery ,null);
                        cursorDaily.moveToFirst();
                        String frequency = cursorDaily.getString(cursorDaily.getColumnIndex(DBConstants.T__2_C_FREQUENCY));
                        DailyReminder dailyReminder = new DailyReminder(""+frequency);
                        reminder.setDailyReminder(dailyReminder);
                        break;
                }
                reminders.add(reminder);
            } while (cursor.moveToNext());
        }
        db.close();
        return reminders;
    }
}
