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

        CountDown = findViewById(R.id.text_view_countdown);

        StartPause = findViewById(R.id.button);
        Reset = findViewById(R.id.button2);
        // 'Listen' for clicks
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

    private void startTimer() {
        Timer = new CountDownTimer(TimeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                TimeLeftInMillis = millisUntilFinished;
                updateText();
            }

            @Override
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

    private void pauseTimer() {
        Timer.cancel();
        Running = false;
        StartPause.setText("Start");
        Reset.setVisibility(View.VISIBLE);
    }

    private void resetTimer() {
        TimeLeftInMillis = START_TIME_IN_MILLIS;
        updateText();
        Reset.setVisibility(View.INVISIBLE);
        StartPause.setVisibility(View.VISIBLE);
    }

    private void updateText() {
        int min = (int) (TimeLeftInMillis / 1000) / 60;
        int sec = (int) (TimeLeftInMillis / 1000) % 60;

        String timeLeftFormatted = String.format(Locale.getDefault(), "%02d:%02d", min, sec);

        CountDown.setText(timeLeftFormatted);
    }
}
