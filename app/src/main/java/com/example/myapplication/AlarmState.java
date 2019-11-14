package com.example.myapplication;

import android.app.AlarmManager;
import android.app.PendingIntent;

public interface AlarmState {
    public void handle(AlarmManager manager, PendingIntent intent);
}
