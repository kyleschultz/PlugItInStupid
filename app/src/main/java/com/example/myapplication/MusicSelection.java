package com.example.myapplication;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MusicSelection extends AppCompatActivity implements View.OnClickListener{

    private RadioGroup radioGroup;
    private RadioButton r1, r2, r3, r4;
    private Button b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_selection);

        b = findViewById(R.id.saveButton);
        b.setOnClickListener(this);

        radioGroup = findViewById(R.id.radioGroupMusic);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                if(checkedId == R.id.musicButton1) {
                    Toast t = Toast.makeText(getApplicationContext(), "beat it selected",
                            Toast.LENGTH_SHORT);

                    t.setGravity(Gravity.FILL_HORIZONTAL, 10, 1500);
                    t.show();
                }
                else if(checkedId == R.id.musicButton2) {
                    Toast t = Toast.makeText(getApplicationContext(), "byob selected",
                            Toast.LENGTH_SHORT);
                    t.setGravity(Gravity.FILL_HORIZONTAL, 10, 1500);
                    t.show();
                }
                else if(checkedId == R.id.musicButton3) {
                    Toast t = Toast.makeText(getApplicationContext(), "pieces selected",
                            Toast.LENGTH_SHORT);
                    t.setGravity(Gravity.FILL_HORIZONTAL, 10, 1500);
                    t.show();
                }
                else if(checkedId == R.id.musicButton4) {
                    Toast t = Toast.makeText(getApplicationContext(), "toxicity selected",
                            Toast.LENGTH_SHORT);
                    t.setGravity(Gravity.FILL_HORIZONTAL, 10, 1500);
                    t.show();
                }

            }
        });
    }

    @Override
    public void onClick(View v) {

        r1 = findViewById(R.id.musicButton1);
        r2 = findViewById(R.id.musicButton2);
        r3 = findViewById(R.id.musicButton3);
        r4 = findViewById(R.id.musicButton4);


        MediaPlayer mMediaPlayer;
        int selectedId = radioGroup.getCheckedRadioButtonId();

        if(selectedId == r1.getId()) {
            mMediaPlayer = MediaPlayer.create(this, R.raw.beat_it);
            System.out.println("in beat it if statement");
            App.setmMediaPlayer(mMediaPlayer);
        }
        else if(selectedId == r2.getId()) {
            mMediaPlayer = MediaPlayer.create(this, R.raw.byob);
            App.setmMediaPlayer(mMediaPlayer);
        }
        else if(selectedId == r3.getId()) {
            mMediaPlayer = MediaPlayer.create(this, R.raw.pieces);
            App.setmMediaPlayer(mMediaPlayer);
        }
        else if(selectedId == r4.getId()) {
            mMediaPlayer = MediaPlayer.create(this, R.raw.toxicity);
            App.setmMediaPlayer(mMediaPlayer);
        }
        else if(v.getId() == R.id.saveButton){
            Intent myIntent = new Intent(this, MainActivity.class);
            startActivity(myIntent);
        }

    }
}