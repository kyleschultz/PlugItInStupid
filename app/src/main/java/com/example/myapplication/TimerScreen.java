package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Locale;

public class TimerScreen extends AppCompatActivity {
    //create all the variables needed for keeping track of the countdown
    private static final long START_TIME_IN_MILLIS = 600000;

    private TextView CountDown;
    private Button StartPause;
    private Button Reset;

    private CountDownTimer Timer;

    private boolean Running;

    private long TimeLeftInMillis = START_TIME_IN_MILLIS;

    private static final String PREFS_NAME = "prefs";
    private static final String PREF_DARK_THEME = "dark_theme";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SharedPreferences preferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        boolean useDarkTheme = preferences.getBoolean(PREF_DARK_THEME, false);
        // Check for dark theme
        if(useDarkTheme) {
            setTheme(R.style.AppTheme_Dark_ActionBar);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer_screen);
        //set the countdown to the view
        CountDown = findViewById(R.id.text_view_countdown);
        //set the start/pause button and the reset button to the proper button ids
        StartPause = findViewById(R.id.button);
        Reset = findViewById(R.id.button2);
        // Listen for relevant objects 
        StartPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Running) {
                    pauseTimer();
                } else {
                    startTimer();
                }
            }
        });

        Reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetTimer();
            }
        });

        updateText();
    }

    //start the timer
    private void startTimer() {
        Timer = new CountDownTimer(TimeLeftInMillis, 1000) {
            @Override
            //update the view with each second ticking down
            public void onTick(long millisUntilFinished) {
                TimeLeftInMillis = millisUntilFinished;
                updateText();
            }

            @Override
            //when its finished reset the button to start again
            public void onFinish() {
                Running = false;
                StartPause.setText("Start");
                StartPause.setVisibility(View.INVISIBLE);
                Reset.setVisibility(View.VISIBLE);
            }
        }.start();

        Running = true;
        StartPause.setText("Pause");
        Reset.setVisibility(View.INVISIBLE);
    }
    //pause the timer and set running to false
    private void pauseTimer() {
        Timer.cancel();
        Running = false;
        StartPause.setText("Start");
        Reset.setVisibility(View.VISIBLE);
    }
    //reset the time to the original start time in millis
    private void resetTimer() {
        TimeLeftInMillis = START_TIME_IN_MILLIS;
        updateText();
        Reset.setVisibility(View.INVISIBLE);
        StartPause.setVisibility(View.VISIBLE);
    }
    //keep the text on the screen updated for the user to see
    private void updateText() {
        int min = (int) (TimeLeftInMillis / 1000) / 60;
        int sec = (int) (TimeLeftInMillis / 1000) % 60;

        String timeLeftFormatted = String.format(Locale.getDefault(), "%02d:%02d", min, sec);

        CountDown.setText(timeLeftFormatted);
    }
}
