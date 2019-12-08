package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.icu.util.Calendar;
import android.icu.util.TimeZone;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class ChangeTimeZone extends AppCompatActivity implements View.OnClickListener{
    // RadioGroup object
    private RadioGroup radioGroup;
    // Used to check for dark theme
    private static final String PREFS_NAME = "prefs";
    private static final String PREF_DARK_THEME = "dark_theme";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Check if dark theme is selected
        SharedPreferences preferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        boolean useDarkTheme = preferences.getBoolean(PREF_DARK_THEME, false);

        if(useDarkTheme) {
            setTheme(R.style.AppTheme_Dark_ActionBar);
        }
        super.onCreate(savedInstanceState);
        // Set view to activity_change_time_zone xml
        setContentView(R.layout.activity_change_time_zone);
        // Save button
        Button b = findViewById(R.id.saveButton);
        // Listen for save button
        b.setOnClickListener(this);
        // Listen for radio group
        radioGroup = findViewById(R.id.radioGroup6);
        // Check to see which radio button is checked
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                if(checkedId == R.id.timeButton1) {
                    Toast t = Toast.makeText(getApplicationContext(), "Timezone: EST",
                            Toast.LENGTH_SHORT);

                    t.setGravity(Gravity.FILL_HORIZONTAL, 10, 1500);
                    t.show();
                }
                else if(checkedId == R.id.timeButton2) {
                    Toast t = Toast.makeText(getApplicationContext(), "Timezone: CST",
                            Toast.LENGTH_SHORT);
                    t.setGravity(Gravity.FILL_HORIZONTAL, 10, 1500);
                    t.show();
                }
                else if(checkedId == R.id.timeButton3) {
                    Toast t = Toast.makeText(getApplicationContext(), "Timezone: MST",
                            Toast.LENGTH_SHORT);
                    t.setGravity(Gravity.FILL_HORIZONTAL, 10, 1500);
                    t.show();
                }
                else if(checkedId == R.id.timeButton4) {
                    Toast t = Toast.makeText(getApplicationContext(), "Timezone: PST",
                            Toast.LENGTH_SHORT);
                    t.setGravity(Gravity.FILL_HORIZONTAL, 10, 1500);
                    t.show();
                }
                else if(checkedId == R.id.timeButton5) {
                    Toast t = Toast.makeText(getApplicationContext(), "Timezone: AST",
                            Toast.LENGTH_SHORT);
                    t.setGravity(Gravity.FILL_HORIZONTAL, 10, 1500);
                    t.show();
                }
                else if(checkedId == R.id.timeButton6) {
                    Toast t = Toast.makeText(getApplicationContext(), "Timezone: HST",
                            Toast.LENGTH_SHORT);
                    t.setGravity(Gravity.FILL_HORIZONTAL, 10, 1500);
                    t.show();
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        // Object for each radio button
        RadioButton EST = findViewById(R.id.timeButton1);
        RadioButton CST = findViewById(R.id.timeButton2);
        RadioButton MST = findViewById(R.id.timeButton3);
        RadioButton PST = findViewById(R.id.timeButton4);
        RadioButton AST = findViewById(R.id.timeButton5);
        RadioButton HST = findViewById(R.id.timeButton6);

        Calendar calendar = Calendar.getInstance();
        int selectedId = radioGroup.getCheckedRadioButtonId();
        // Check to see which radio button is selected upon save
        if(selectedId == EST.getId()) {
            calendar.setTimeZone(TimeZone.getTimeZone("US/Eastern"));
            TimeZone tz = calendar.getTimeZone();
        }
        else if(selectedId == CST.getId()) {
            calendar.setTimeZone(TimeZone.getTimeZone("US/Central"));
            TimeZone tz = calendar.getTimeZone();
        }
        else if(selectedId == MST.getId()) {
            calendar.setTimeZone(TimeZone.getTimeZone("US/Mountain"));
            TimeZone tz = calendar.getTimeZone();
        }
        else if(selectedId == PST.getId()) {
            calendar.setTimeZone(TimeZone.getTimeZone("US/Pacific"));
            TimeZone tz = calendar.getTimeZone();
        }
        else if(selectedId == AST.getId()) {
            calendar.setTimeZone(TimeZone.getTimeZone("US/Alaska"));
            TimeZone tz = calendar.getTimeZone();
        }
        else if(selectedId == HST.getId()) {
            calendar.setTimeZone(TimeZone.getTimeZone("US/Aleutian"));
            TimeZone tz = calendar.getTimeZone();
        }
        // Set the calendar and finish
        App.setCalendar(calendar);
        finish();
    }
}
