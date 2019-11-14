package com.example.myapplication;

import android.app.AlarmManager;
import android.app.PendingIntent;

public class OffState implements AlarmState {
    public void handle(AlarmManager manager, PendingIntent intent) {
        manager.cancel(intent);
    }
}
