package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
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
        ImageView addButton = findViewById(R.id.addAlarm);
        addButton.setOnClickListener(this);
        ImageView timerButton = findViewById(R.id.imageView2);
        timerButton.setOnClickListener(this);
        ImageView toneButton = findViewById(R.id.imageView3);
        toneButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.addAlarm) {
            Intent myintent = new Intent(MainActivity.this, AddAlarm.class);
            startActivity(myintent);
        }
        else if(v.getId() == R.id.imageView2){
            Intent myintent = new Intent(MainActivity.this, TimerScreen.class);
            startActivity(myintent);
        }
        else if(v.getId() == R.id.imageView3){
            Intent myintent = new Intent(MainActivity.this, MusicSelection.class);
            startActivity(myintent);
        }
    }

}
