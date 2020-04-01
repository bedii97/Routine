package com.example.routine.Model;

import androidx.annotation.Nullable;

public class Reminder {
    private Integer ID;
    private String EventName;
    private String NotificationMessage;
    private String StartedDate;
    private String EndedDate;
    private String Time;
    private String Type;
    private DailyReminder dailyReminder;

    public Reminder(Integer ID, String eventName, String notificationMessage, String startedDate, String endedDate, String time, String type) {
        this.ID = ID;
        EventName = eventName;
        NotificationMessage = notificationMessage;
        StartedDate = startedDate;
        EndedDate = endedDate;
        Time = time;
        Type = type;
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getEventName() {
        return EventName;
    }

    public void setEventName(String eventName) {
        EventName = eventName;
    }

    public String getNotificationMessage() {
        return NotificationMessage;
    }

    public void setNotificationMessage(String notificationMessage) {
        NotificationMessage = notificationMessage;
    }

    public String getStartedDate() {
        return StartedDate;
    }

    public void setStartedDate(String startedDate) {
        StartedDate = startedDate;
    }

    public String getEndedDate() {
        return EndedDate;
    }

    public void setEndedDate(String endedDate) {
        EndedDate = endedDate;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public DailyReminder getDailyReminder() {
        return dailyReminder;
    }

    public void setDailyReminder(DailyReminder dailyReminder) {
        this.dailyReminder = dailyReminder;
    }
}
