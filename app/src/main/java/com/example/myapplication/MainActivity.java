package com.example.myapplication;

// Android imports
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.content.Intent;
import android.view.View;
import android.content.Context;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Context context;
    // 'Listen' for clicks
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.context = this;
        App.setContext(this.context);
        // Add alarm
        ImageView addButton = findViewById(R.id.addAlarm);
        addButton.setOnClickListener(this);

        // Ringtone
        ImageView toneButton = findViewById(R.id.imageView3);
        toneButton.setOnClickListener(this);

        // Countdown timer
        ImageView timerButton = findViewById(R.id.countdownTimer);
        timerButton.setOnClickListener(this);

        // Timezone
        Button tzButton = findViewById(R.id.timeZoneButton);
        tzButton.setOnClickListener(this);
    }

    // Controls what happens upon clicking on features
    @Override
    public void onClick(View v) {
        // Add alarm
        if(v.getId() == R.id.addAlarm) {
            Intent myintent = new Intent(MainActivity.this, AddAlarm.class);
            startActivity(myintent);
        }

        // Countdown timer
        else if(v.getId() == R.id.countdownTimer){
            Intent myintent = new Intent(MainActivity.this, TimerScreen.class);
            startActivity(myintent);
        }

        // Ringtone
        else if(v.getId() == R.id.imageView3){
            Intent myintent = new Intent(MainActivity.this, MusicSelection.class);
            startActivity(myintent);
        }

        // Timezone
        else if(v.getId() == R.id.timeZoneButton){
            Intent myintent = new Intent(MainActivity.this, ChangeTimeZone.class);
            startActivity(myintent);
        }

    }

}
