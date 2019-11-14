package com.example.myapplication;

import android.app.AlarmManager;
import android.app.PendingIntent;

import java.util.Calendar;

public class SnoozeState implements AlarmState {

    public void handle(AlarmManager manager, PendingIntent intent) {
        Calendar calendar = Calendar.getInstance();
        long timeAfterTenMinute = calendar.getTimeInMillis() + 600000;
        manager.set(AlarmManager.RTC_WAKEUP, timeAfterTenMinute, intent);
    }
}