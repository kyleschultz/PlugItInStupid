package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;

public class MusicSelection extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_selection);
    }


    MediaPlayer mMediaPlayer;

    // Controls what happens upon clicking on features

    public void onClick(View v) {
        // Add alarm
        if (v.getId() == R.id.ringtone1) {
            Intent myintent = new Intent(MusicSelection.this, MusicSelection.class);
            //startActivity(myintent);
            mMediaPlayer = MediaPlayer.create(this, R.raw.toxicity);
        }
    }
}
