package com.example.myapplication;

import android.app.AlarmManager;
import android.app.PendingIntent;

import java.util.Calendar;

public class CreateState implements AlarmState {
    public void handle(AlarmManager manager, PendingIntent intent) {
        Calendar calendar = Calendar.getInstance();
        manager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), intent);
    }
}
