package com.example.myapplication;

import android.app.AlarmManager;
import android.app.PendingIntent;

import java.util.Calendar;

public class LowBatteryLevelState implements AlarmState {
    public void handle(AlarmManager manager, PendingIntent intent, Calendar calendar, boolean repeating) {
        //Calendar calendar = Calendar.getInstance();
        long newTime = calendar.getTimeInMillis() + 1000;
        manager.set(AlarmManager.RTC_WAKEUP, newTime, intent );
    }
}
