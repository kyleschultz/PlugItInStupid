package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.icu.util.TimeZone;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ChangeTimeZone extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_time_zone);


        TextView button1 = findViewById(R.id.timeButton1);
        button1.setOnClickListener(this);
    }
    public void onClick(View v) {
        Calendar calendar = Calendar.getInstance();
        /* Setting system timezone is not possible - maybe need Calendar object 
        if(v.getId() == R.id.timeButton1) {
            SimpleDateFormat store = new SimpleDateFormat("dd-MMM-yyyy hh:mm:ss z");
            store.setTimeZone(TimeZone.getTimeZone("US/East"));
        }
         */

    }
}
