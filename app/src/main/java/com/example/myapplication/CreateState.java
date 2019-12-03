package com.example.myapplication;

import android.app.AlarmManager;
import android.app.PendingIntent;

import android.icu.util.Calendar;

public class CreateState implements AlarmState {
    public void handle(AlarmManager manager, PendingIntent intent, Calendar calendar, boolean repeating) {
        //Calendar calendar = Calendar.getInstance();
        //System.out.println(repeating);
        //if(System.currentTimeMillis() >= calendar.getTimeInMillis()){
        //    System.out.println("Here");
        //    calendar.setTimeInMillis(86400000 + calendar.getTimeInMillis());
        //}
        System.out.println(calendar.getTimeInMillis());
        System.out.println(System.currentTimeMillis());
        if(repeating == true){
            manager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),24 * 7 * 60 * 60 * 1000, intent);
        }
        else{
            manager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), intent);
        }
    }
}
