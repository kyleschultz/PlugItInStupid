package com.example.myapplication;


import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.net.Uri;

import android.os.IBinder;


import java.io.File;
import java.io.IOException;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

public class RingtoneService extends Service {
    //public RingtoneService() {}
    MediaPlayer mediaPlayer = MediaPlayer.create(App.getContext(), R.raw.beat_it);
    MediaPlayer mediaPlayer2 = MediaPlayer.create(App.getContext(), R.raw.byob);
    MediaPlayer mediaPlayer3 = MediaPlayer.create(App.getContext(), R.raw.pieces);
    MediaPlayer mediaPlayer4 = MediaPlayer.create(App.getContext(), R.raw.toxicity);
    private boolean isRunning;
    private int startId;


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.

        return null;
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int sId)
    {
        NotificationManager mNM = (NotificationManager)
                getSystemService(NOTIFICATION_SERVICE);

        Intent intent1 = new Intent(this.getApplicationContext(), MainActivity.class);
        PendingIntent pIntent = PendingIntent.getActivity(this, 0, intent1, 0);

        Notification mNotify  = new Notification.Builder(this)
                .setContentTitle("Music Playing" + "!")
                .setContentText("Click me!")
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentIntent(pIntent)
                .setAutoCancel(true)
                .build();

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
        if(!this.isRunning && startId == 1){
            String s = App.getMediaString();
            Uri uri;

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


            mNM.notify(0, mNotify);
            this.isRunning = true;
            this.startId = 0;
        }
        else{
            String s = App.getMediaString();

            if(s == "beat"){
                mediaPlayer.stop();
                mediaPlayer.reset();

            }
            else if(s == "byob"){
                mediaPlayer2.stop();
                mediaPlayer2.reset();

            }
            else if(s == "pieces"){
                mediaPlayer3.stop();
                mediaPlayer3.reset();

            }
            else{
                mediaPlayer4.stop();
                mediaPlayer4.reset();

            }

            this.isRunning = false;
            this.startId = 0;
        }
        return START_NOT_STICKY;
    }
}
