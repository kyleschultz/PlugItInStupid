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
import java.util.ArrayList;

public class AddAlarm extends AppCompatActivity implements View.OnClickListener {

    private TimePicker tPicker;
    private int hour;
    private int minute;
    private Calendar calendar;
    private AlarmState state;
    AlarmManager alarmManager;
    Context context;
    private ArrayList<Integer> weekdays;

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
        weekdays = new ArrayList<Integer>();

    }

    @TargetApi(Build.VERSION_CODES.M)
    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.textView){
            System.out.println("Clicked add");
            hour = tPicker.getCurrentHour();
            minute = tPicker.getCurrentMinute();
            CheckBox sunday = findViewById(R.id.checkBox);
            if(sunday.isChecked()){
                weekdays.add(1);
            }
            CheckBox monday = findViewById(R.id.checkBox2);
            if(monday.isChecked()){
                weekdays.add(2);
            }
            CheckBox tuesday = findViewById(R.id.checkBox3);
            if(tuesday.isChecked()){
                weekdays.add(3);
            }
            CheckBox wednesday = findViewById(R.id.checkBox4);
            if(wednesday.isChecked()){
                weekdays.add(4);
            }
            CheckBox thursday = findViewById(R.id.checkBox5);
            if(thursday.isChecked()){
                weekdays.add(5);
            }
            CheckBox friday = findViewById(R.id.checkBox6);
            if(friday.isChecked()) {
                weekdays.add(6);
            }
            CheckBox saturday = findViewById(R.id.checkBox7);
            if(saturday.isChecked()){
                weekdays.add(7);
            }
            System.out.println(tPicker.getCurrentHour() + ":" + tPicker.getCurrentMinute());
            calendar.setTimeInMillis(System.currentTimeMillis());
            calendar.set(Calendar.HOUR_OF_DAY, tPicker.getCurrentHour());
            calendar.set(Calendar.MINUTE, tPicker.getCurrentMinute());
            calendar.set(Calendar.SECOND, 0);
            calendar.set(Calendar.MILLISECOND, 0);
            //System.out.println(this.context);
            this.state = new CreateState();
            boolean repeating = false;
            System.out.println(weekdays);
            if (weekdays.size() == 0) {
                Intent intent = new Intent(this.context, AlarmReceiver.class);
                PendingIntent pending = PendingIntent.getBroadcast(this.context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
                state.handle(alarmManager, pending, calendar, repeating);
            }
            else{
                for(int i = 0; i < weekdays.size(); i++){
                    repeating = true;
                    calendar.set(Calendar.DAY_OF_WEEK, weekdays.get(i));
                    Intent intent = new Intent(this.context, AlarmReceiver.class);
                    PendingIntent pending = PendingIntent.getBroadcast(this.context, i+1, intent, PendingIntent.FLAG_UPDATE_CURRENT);
                    state.handle(alarmManager, pending, calendar, repeating);
                }
            }

            //alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pending);
            System.out.println("Set alarm manager");
            finish();
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

