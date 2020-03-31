package com.example.routine.Model;

public class DailyReminder {
    private String Frequency;

    public DailyReminder(String frequency) {
        Frequency = frequency;
    }

    public String getFrequency() {
        return Frequency;
    }

    public void setFrequency(String frequency) {
        Frequency = frequency;
    }
}
