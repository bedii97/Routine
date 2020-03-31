package com.example.routine.DbHelper;

public class DBConstants {
    public static final String DB_NAME = "Routine.db";
    public static final int DB_VERSION = 1;
    //TABLE 1
    public static final String T_1_NAME = "routine_table";
    public static final String T_1_C_ID = "ID";
    public static final String T_1_C_EVENT_NAME = "Event_Name";
    public static final String T_1_C_NOTIFICATION_MESSAGE = "Notification_Message";
    public static final String T_1_C_CURRENT_DATE = "Current_Date";
    public static final String T_1_C_END_DATE = "End_Date";
    public static final String T_1_C_CURRENT_TIME = "Current_Time";
    public static final String T_1_CREATE_QUERY = "CREATE TABLE " + T_1_NAME + " ("
            + T_1_C_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + T_1_C_EVENT_NAME + " TEXT, "
            + T_1_C_NOTIFICATION_MESSAGE + " TEXT, "
            + T_1_C_CURRENT_DATE + "TEXT, "
            + T_1_C_END_DATE + " TEXT, "
            + T_1_C_CURRENT_TIME + "TEXT) ";
    //TABLE 2
    public static final String T_2_NAME = "daily_routine_table";
    public static final String T__2_C_ID = "ID";
    public static final String T__2_C_FREQUENCY = "Frequency";
    public static final String T_2_CREATE_QUERY = "CREATE TABLE " + T_2_NAME + " ("
            + T__2_C_ID + " INTEGER PRIMARY KEY, "
            + T__2_C_FREQUENCY + " INTEGER";
}
