package com.example.myapplication;

import android.app.AlarmManager;
import android.app.PendingIntent;

import android.icu.util.Calendar;

// State pattern used here
public interface AlarmState {
    // handles what to do depending on the action of the alarm
    void handle(AlarmManager manager, PendingIntent intent, Calendar calendar, boolean repeating);
}
