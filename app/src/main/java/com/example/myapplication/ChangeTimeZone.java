package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import java.util.TimeZone;

public class ChangeTimeZone extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_time_zone);


        TextView button1 = findViewById(R.id.timeButton1);
        button1.setOnClickListener(this);
    }
    public void onClick(View v) {
        /*
        if(v.getId() == R.id.timeButton1) {
            TimeZone.setDefault(TimeZone.getTimeZone("GMT+3"));
        }
        */
    }
}
