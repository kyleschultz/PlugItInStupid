package com.example.myapplication;

import android.app.AlarmManager;
import android.app.PendingIntent;

import java.util.Calendar;

public class OffState implements AlarmState {
    public void handle(AlarmManager manager, PendingIntent intent, Calendar calendar, boolean repeating) {
        manager.cancel(intent);
    }
}
