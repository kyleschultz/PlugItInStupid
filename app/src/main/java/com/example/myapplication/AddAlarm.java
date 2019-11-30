package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;


import android.annotation.TargetApi;
import android.app.PendingIntent;
import android.os.Build;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.TimePicker;
import java.util.Calendar;
import android.content.Intent;
import android.widget.ImageView;
import android.view.View;
import android.app.AlarmManager;
import android.content.Context;

public class AddAlarm extends AppCompatActivity implements View.OnClickListener {

    private TimePicker tPicker;
    private int hour;
    private int minute;
    private Calendar calendar;
    private AlarmState state;
    AlarmManager alarmManager;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_alarm);
        tPicker = (TimePicker) findViewById(R.id.timePicker);
        calendar = Calendar.getInstance();
        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        context = App.getContext();
        TextView button = findViewById(R.id.textView);
        button.setOnClickListener(this);
        TextView cancel = findViewById(R.id.textView2);
        cancel.setOnClickListener(this);

    }

    @TargetApi(Build.VERSION_CODES.M)
    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.textView){
            System.out.println("Clicked add");
            hour = tPicker.getCurrentHour();
            minute = tPicker.getCurrentMinute();
            CheckBox sunday = findViewById(R.id.checkBox);
            CheckBox monday = findViewById(R.id.checkBox2);
            CheckBox tuesday = findViewById(R.id.checkBox3);
            CheckBox wednesday = findViewById(R.id.checkBox4);
            CheckBox thursday = findViewById(R.id.checkBox5);
            CheckBox firday = findViewById(R.id.checkBox6);
            System.out.println(tPicker.getCurrentHour() + ":" + tPicker.getCurrentMinute());
            calendar.set(Calendar.HOUR_OF_DAY, tPicker.getCurrentHour());
            calendar.set(Calendar.MINUTE, tPicker.getCurrentMinute());
            System.out.println(this.context);
            Intent intent = new Intent(this, AlarmReceiver.class);
            PendingIntent pending = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
            alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pending);
            System.out.println("Set alarm manager");
            //finish();
        }
        else if(v.getId() == R.id.textView2){
            Intent myIntent = new Intent(this, MainActivity.class);
            startActivity(myIntent);
        }

    }

    public void setState(){
        this.state = new CreateState();
    }
}

