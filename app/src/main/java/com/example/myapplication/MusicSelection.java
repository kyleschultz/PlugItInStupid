package com.example.myapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

public class MusicSelection extends AppCompatActivity implements View.OnClickListener{

    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_selection);

        this.context = this;

        RadioButton ringtoneButton1 = findViewById(R.id.ringtone1);
        ringtoneButton1.setOnClickListener(this);

        RadioButton ringtoneButton2= findViewById(R.id.ringtone2);
        ringtoneButton2.setOnClickListener(this);

        RadioButton ringtoneButton3 = findViewById(R.id.radioButton3);
        ringtoneButton3.setOnClickListener(this);

        RadioButton ringtoneButton4 = findViewById(R.id.ringtone4);
        ringtoneButton4.setOnClickListener(this);

        TextView ok = findViewById(R.id.textView7);
        ok.setOnClickListener(this);
    }


    MediaPlayer mMediaPlayer;
    MediaPlayer defaultPlayer = MediaPlayer.create(this, R.raw.byob);

    // Controls what happens upon clicking on features

    public void onClick(View v) {
        // Set ringtone 1
        if (v.getId() == R.id.ringtone1) {

            mMediaPlayer = MediaPlayer.create(this, R.raw.toxicity);
        }

        // Set ringtone 2
        else if (v.getId() == R.id.ringtone2) {

            mMediaPlayer = MediaPlayer.create(this, R.raw.beat_it);
        }

        // Set ringtone 3
        else if (v.getId() == R.id.radioButton3) {

            mMediaPlayer = MediaPlayer.create(this, R.raw.byob);
        }

        // Set ringtone 4
        else if (v.getId() == R.id.ringtone4) {

            mMediaPlayer = MediaPlayer.create(this, R.raw.pieces);
        }

        // Ok is clicked
        else if (v.getId() == R.id.textView7) {
            Intent myintent = new Intent(MusicSelection.this, MainActivity.class);
            startActivity(myintent);

        }
    }
}
