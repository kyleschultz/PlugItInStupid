package com.example.myapplication;

import android.app.AlarmManager;
import android.app.PendingIntent;

import android.icu.util.Calendar;

public class SnoozeState implements AlarmState {

    public void handle(AlarmManager manager, PendingIntent intent, Calendar calendar, boolean repeating) {
        //Calendar calendar = Calendar.getInstance();
        long timeAfterTenMinute = System.currentTimeMillis() + 600000;
        manager.set(AlarmManager.RTC_WAKEUP, timeAfterTenMinute, intent);
    }
}
