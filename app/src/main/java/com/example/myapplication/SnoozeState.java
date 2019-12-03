package com.example.myapplication;

import android.app.AlarmManager;
import android.app.PendingIntent;

import android.icu.util.Calendar;

public class SnoozeState implements AlarmState {

    public void handle(AlarmManager manager, PendingIntent intent, Calendar calendar, boolean repeating) {
        //Calendar calendar = Calendar.getInstance();
        System.out.println("Snooze");
        long timeAfterTenMinute = calendar.getTimeInMillis() + 120000;
        System.out.println(timeAfterTenMinute);
        System.out.println(System.currentTimeMillis());
        manager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, timeAfterTenMinute, intent);
    }
}
