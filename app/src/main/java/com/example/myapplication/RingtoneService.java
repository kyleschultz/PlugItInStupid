package com.example.myapplication;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;

import android.os.IBinder;


import androidx.annotation.Nullable;

public class RingtoneService extends Service {
    //create the media player instance for each song
    MediaPlayer mediaPlayer = MediaPlayer.create(App.getContext(), R.raw.beat_it);
    MediaPlayer mediaPlayer2 = MediaPlayer.create(App.getContext(), R.raw.byob);
    MediaPlayer mediaPlayer3 = MediaPlayer.create(App.getContext(), R.raw.pieces);
    MediaPlayer mediaPlayer4 = MediaPlayer.create(App.getContext(), R.raw.toxicity);
    private boolean isRunning;
    private int startId;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int sId)
    {
        //set the id based on what the state from the extras passed in from the instance indicate
        String state = intent.getExtras().getString("extra");
        switch (state) {
            case "no":
                startId = 0;
                break;
            case "yes":
                startId = 1;
                break;
            default:
                startId = 0;
                break;
        }
        //if the media player isn't running and the id is 1 then get the ringtone that was selected
        //based on the string value play that song
        if(!this.isRunning && startId == 1){
            String s = App.getMediaString();

            if(s == "beat"){
                mediaPlayer.start();

            }
            else if(s == "byob"){
                mediaPlayer2.start();

            }
            else if(s == "pieces"){
                mediaPlayer3.start();

            }
            else{
                mediaPlayer4.start();

            }
            this.isRunning = true;
            this.startId = 0;
        }
        //if the media player is running get the ringtone string and pause the music, seek to the beginning for the next play
        else{
            String s = App.getMediaString();

            if(s == "beat"){
                mediaPlayer.pause();
                mediaPlayer.seekTo(0);

            }
            else if(s == "byob"){
                mediaPlayer2.pause();
                mediaPlayer2.seekTo(0);

            }
            else if(s == "pieces"){
                mediaPlayer3.pause();
                mediaPlayer3.seekTo(0);

            }
            else{
                mediaPlayer4.pause();
                mediaPlayer4.seekTo(0);

            }
            this.isRunning = false;
            this.startId = 0;
        }
        return START_NOT_STICKY;
    }
}
