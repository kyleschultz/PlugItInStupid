package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.content.Intent;
import android.view.View;
import android.app.AlarmManager;
import android.content.Context;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    AlarmManager alarmManager;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        this.context = this;
        // Add alarm
        ImageView addButton = findViewById(R.id.addAlarm);
        addButton.setOnClickListener(this);

        // Ringtone
        ImageView toneButton = findViewById(R.id.imageView3);
        toneButton.setOnClickListener(this);

        // Count-down timer
        ImageView timerButton = findViewById(R.id.countdownTimer);
        timerButton.setOnClickListener(this);

        // Time-zone
        Button tzButton = findViewById(R.id.timeZoneButton);
        tzButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        // Add alarm
        if(v.getId() == R.id.addAlarm) {
            Intent myintent = new Intent(MainActivity.this, AddAlarm.class);
            startActivity(myintent);
        }

        // Count-down timer
        else if(v.getId() == R.id.countdownTimer){
            Intent myintent = new Intent(MainActivity.this, TimerScreen.class);
            startActivity(myintent);
        }

        // Ring-tone
        else if(v.getId() == R.id.imageView3){
            Intent myintent = new Intent(MainActivity.this, MusicSelection.class);
            startActivity(myintent);
        }

        // Time-zone
        else if(v.getId() == R.id.timeZoneButton){
            Intent myintent = new Intent(MainActivity.this, ChangeTimeZone.class);
            startActivity(myintent);
        }

    }

}
