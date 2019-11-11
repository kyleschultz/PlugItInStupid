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
        ImageView button = findViewById(R.id.addAlarm);
        button.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Intent myintent = new Intent(MainActivity.this, AddAlarm.class);
        startActivity(myintent);
    }

}
