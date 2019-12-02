package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class AlarmNotification extends AppCompatActivity implements View.OnClickListener{

    private ImageView checkButton, snoozeButton;
    Context context;
    Intent myIntent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm_notification);

        myIntent = new Intent(App.getContext(), AlarmReceiver.class);
        checkButton = findViewById(R.id.alarmCheck);
        checkButton.setOnClickListener(this);
        snoozeButton = findViewById(R.id.snooze);
        snoozeButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.alarmCheck){
            myIntent.putExtra("extra", "no");
            myIntent.putExtra("extra1", "main");
            sendBroadcast(myIntent);


        }
        else if(v.getId() == R.id.snooze){
            myIntent.putExtra("extra", "no");
            myIntent.putExtra("extra1", "main");
            sendBroadcast(myIntent);

        }
    }


}
