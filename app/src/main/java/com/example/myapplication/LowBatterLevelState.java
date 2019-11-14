package com.example.myapplication;

import android.app.AlarmManager;
import android.app.PendingIntent;

import java.util.Calendar;

public class LowBatterLevelState implements AlarmState {
    public void handle(AlarmManager manager, PendingIntent intent) {
        Calendar calendar = Calendar.getInstance();
        long timeAfterTenMinute = calendar.getTimeInMillis() + 1000;
        manager.set(AlarmManager.RTC_WAKEUP, timeAfterTenMinute, intent );
    }
}
