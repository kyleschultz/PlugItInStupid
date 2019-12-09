package com.example.myapplication;

import android.app.AlarmManager;
import android.app.PendingIntent;

import android.icu.util.Calendar;

public class SnoozeState implements AlarmState {

    public void handle(AlarmManager manager, PendingIntent intent, Calendar calendar, boolean repeating) {
        // Set a new alarm 10 minutes from now
        long timeAfterTenMinute = calendar.getTimeInMillis() + 600000;
        manager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, timeAfterTenMinute, intent);
    }
}
