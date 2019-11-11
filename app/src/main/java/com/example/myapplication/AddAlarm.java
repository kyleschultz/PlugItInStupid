package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.TimePicker;
import java.util.Calendar;
import android.content.Intent;
import android.widget.ImageView;
import android.view.View;

public class AddAlarm extends AppCompatActivity implements View.OnClickListener {

    private TimePicker tPicker;
    private int hour;
    private int minute;
    private Calendar calendar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_alarm);
        tPicker = (TimePicker) findViewById(R.id.timePicker);
        calendar = Calendar.getInstance();
        TextView button = findViewById(R.id.textView);
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent myIntent = new Intent(this, AlarmReceiver.class);

    }
}

