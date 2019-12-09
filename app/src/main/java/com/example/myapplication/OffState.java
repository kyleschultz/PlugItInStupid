package com.example.myapplication;

import android.app.AlarmManager;
import android.app.PendingIntent;

import android.icu.util.Calendar;

public class OffState implements AlarmState {
    public void handle(AlarmManager manager, PendingIntent intent, Calendar calendar, boolean repeating) {
        // Cancel the intent in the AlarmManager
        manager.cancel(intent);
    }
}
