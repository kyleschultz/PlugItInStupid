package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;


import android.app.PendingIntent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.TimePicker;
import android.icu.util.Calendar;
import android.content.Intent;
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
    Bundle mBundle;

    private static final String PREFS_NAME = "prefs";
    private static final String PREF_DARK_THEME = "dark_theme";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Check for dark theme
        SharedPreferences preferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        boolean useDarkTheme = preferences.getBoolean(PREF_DARK_THEME, false);

        if(useDarkTheme) {
            setTheme(R.style.AppTheme_Dark_ActionBar);
        }
        super.onCreate(savedInstanceState);
        // Set view to activity_add_alarm xml
        setContentView(R.layout.activity_add_alarm);
        tPicker = findViewById(R.id.timePicker);
        if(App.getCalendar() == null){
            calendar = Calendar.getInstance();
        }
        else{
            calendar = App.getCalendar();
        }

        // Get the alarm manager
        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

        // Get application context
        context = App.getContext();
        // Listen for relevant objects
        TextView button = findViewById(R.id.textView);
        button.setOnClickListener(this);
        TextView cancel = findViewById(R.id.textView2);
        cancel.setOnClickListener(this);
        // Create weekdays list and bundle
        weekdays = new ArrayList<>();
        mBundle = new Bundle();

    }

    @Override
    public void onClick(View v) {
        // Depending on days selected, add alarm
        if(v.getId() == R.id.textView){
            // get time picker hour and minute
            hour = tPicker.getCurrentHour();
            minute = tPicker.getCurrentMinute();
            String daysString = "         ";
            CheckBox sunday = findViewById(R.id.checkBox);

            // Check which weekdays have been selected
            if(sunday.isChecked()){
                weekdays.add(1);
                daysString = daysString + "Su ";
            }
            CheckBox monday = findViewById(R.id.checkBox2);
            if(monday.isChecked()){
                weekdays.add(2);
                daysString = daysString + "M ";
            }
            CheckBox tuesday = findViewById(R.id.checkBox3);
            if(tuesday.isChecked()){
                weekdays.add(3);
                daysString = daysString + "T ";
            }
            CheckBox wednesday = findViewById(R.id.checkBox4);
            if(wednesday.isChecked()){
                weekdays.add(4);
                daysString = daysString + "W ";
            }
            CheckBox thursday = findViewById(R.id.checkBox5);
            if(thursday.isChecked()){
                weekdays.add(5);
                daysString = daysString + "Th ";
            }
            CheckBox friday = findViewById(R.id.checkBox6);
            if(friday.isChecked()) {
                weekdays.add(6);
                daysString = daysString + "F ";
            }
            CheckBox saturday = findViewById(R.id.checkBox7);
            if(saturday.isChecked()){
                weekdays.add(7);
                daysString = daysString + "S ";
            }
            // set the alarm state
            this.state = new CreateState();
            // No days selected
            boolean repeating = false;
            if (weekdays.size() == 0) {
                // Set calendar time to go off at selected time
                calendar.setTimeInMillis(System.currentTimeMillis());
                calendar.set(Calendar.HOUR_OF_DAY, tPicker.getCurrentHour());
                calendar.set(Calendar.MINUTE, tPicker.getCurrentMinute());
                calendar.set(Calendar.SECOND, 0);
                calendar.set(Calendar.MILLISECOND, 0);
                // If the time is in the past, set it for a day from now
                if(System.currentTimeMillis() >= calendar.getTimeInMillis()){
                    // 86400000 = 1 day
                    calendar.setTimeInMillis(86400000 + calendar.getTimeInMillis());
                }

                // Build intent to alarm reciever
                Intent intent = new Intent(this.context, AlarmReceiver.class);

                // Add empty weekdays to list
                App.addDays(weekdays);

                // Build time string
                String temp = tPicker.getCurrentHour().toString() + ":" + tPicker.getCurrentMinute().toString();

                //Add the time for later use in UI
                App.addTime(temp);

                // Put extras for RingtoneService
                intent.putExtra("extra", "yes");
                intent.putExtra("index", App.getIntents().size());
                //Create PendingIntent to go off later
                PendingIntent pending = PendingIntent.getBroadcast(this.context, App.getIds(), intent, PendingIntent.FLAG_UPDATE_CURRENT);

                // Add intent to list
                App.addIntent(pending);

                // Handle the alarm
                App.setIds(App.getIds() + 1);
                state.handle(alarmManager, pending, calendar, repeating);
            }
            else{
                // Multiple days selected
                repeating = true;
                for(int i = 0; i < weekdays.size(); i++){
                    // For each day of the week selected set the time on the calendar for that day
                    calendar.setTimeInMillis(System.currentTimeMillis());
                    calendar.set(Calendar.DAY_OF_WEEK, weekdays.get(i));
                    calendar.set(Calendar.HOUR_OF_DAY, tPicker.getCurrentHour());
                    calendar.set(Calendar.MINUTE, tPicker.getCurrentMinute());
                    calendar.set(Calendar.SECOND, 0);
                    calendar.set(Calendar.MILLISECOND, 0);
                    // If the day has past set it for a week from now
                    if(calendar.getTimeInMillis() < System.currentTimeMillis()) {
                        calendar.add(Calendar.DAY_OF_YEAR, 7);
                    }
                    // Set the reciever for the alarm
                    Intent intent = new Intent(this.context, AlarmReceiver.class);
                    // Add weekdays for later use in UI
                    App.addDays(weekdays);
                    //Build time string
                    String temp = tPicker.getCurrentHour().toString() + ":" + tPicker.getCurrentMinute().toString();
                    App.addTime(temp);
                    // put extras for ringtone
                    intent.putExtra("extra", "yes");
                    intent.putExtra("index", App.getIntents().size());
                    // Create the pending intent for later
                    PendingIntent pending = PendingIntent.getBroadcast(this.context, App.getIds(), intent, PendingIntent.FLAG_UPDATE_CURRENT);
                    // Add PendingIntent to app to keep track
                    App.addIntent(pending);
                    // Increment ids
                    App.setIds(App.getIds() + 1);
                    // Handle the alarm
                    state.handle(alarmManager, pending, calendar, repeating);
                }
            }
            // Strings to send to MainActivity
            String hour_string = String.valueOf(hour);
            String minute_string = String.valueOf(minute);
            String amPm = "";

            // Convert 24-hour time to 12-hour time
            if (hour > 12) {
                hour_string = String.valueOf(hour - 12);
                amPm = "pm";
            }
            else{
                amPm = "am";
            }

            if (minute < 10) {
                minute_string = "0" + String.valueOf(minute);
            }
            if(hour == 0){
                hour_string = "12";
            }
            if(hour == 12){
                amPm = "pm";
            }

            // Build the bundle to send back to MainActivity to add alarm to UI
            Intent main = new Intent(this, MainActivity.class);
            mBundle.putString("time", hour_string + ":" + minute_string + amPm + daysString);
            main.putExtras(mBundle);
            startActivity(main);
        }
        // User clicked cancel
        else if(v.getId() == R.id.textView2){
            Intent myIntent = new Intent(this, MainActivity.class);
            startActivity(myIntent);
        }
    }
}
