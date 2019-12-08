package com.example.myapplication;

import android.app.AlarmManager;
import android.app.PendingIntent;

import android.icu.util.Calendar;

public class CreateState implements AlarmState {
    public void handle(AlarmManager manager, PendingIntent intent, Calendar calendar, boolean repeating) {

        if(repeating){
            manager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),24 * 7 * 60 * 60 * 1000, intent);
        }
        else{
            manager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), intent);
        }
    }
}
