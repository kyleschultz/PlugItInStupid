package com.example.myapplication;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
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
    Context mContext;
    MediaPlayer mMediaPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_selection);

        b = findViewById(R.id.saveButton);
        b.setOnClickListener(this);
        r1 = findViewById(R.id.musicButton1);
        r1.setOnClickListener(this);
        r2 = findViewById(R.id.musicButton2);
        r2.setOnClickListener(this);
        r3 = findViewById(R.id.musicButton3);
        r3.setOnClickListener(this);
        r4 = findViewById(R.id.musicButton4);
        r4.setOnClickListener(this);

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


        int selectedId = radioGroup.getCheckedRadioButtonId();

        if(selectedId == r1.getId()) {
            App.createMediaPlayer("beat");
            App.setMediaString("beat");

            System.out.println("IN BEAT IT IF STATEMENT");
        }
        if(selectedId == r2.getId()) {
            App.createMediaPlayer("byob");
            App.setMediaString("byob");

        }
        if(selectedId == r3.getId()) {
            App.createMediaPlayer("pieces");
            App.setMediaString("pieces");
            System.out.println("IN PIECES IF STATEMENT");

        }
        if(selectedId == r4.getId()) {
            App.createMediaPlayer("toxic");
            App.setMediaString("toxic");

        }
        //go back to the main activity screen
        if(v.getId() == R.id.saveButton){
            System.out.println("IN SAVE BUTTON STATEMENT");
            Intent myIntent = new Intent(this, MainActivity.class);
            startActivity(myIntent);
        }

    }
}