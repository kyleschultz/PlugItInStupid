package com.example.myapplication;

import android.app.AlarmManager;
import android.app.PendingIntent;

import android.icu.util.Calendar;

// State pattern used here
public class CreateState implements AlarmState {
    // Creates an alarm to go off at the given calendar time
    public void handle(AlarmManager manager, PendingIntent intent, Calendar calendar, boolean repeating) {
        // If multiple days, set repeating alarm
        if(repeating){
            manager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),24 * 7 * 60 * 60 * 1000, intent);
        }
        else{
            // Just one alarm
            manager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), intent);
        }
    }
}
