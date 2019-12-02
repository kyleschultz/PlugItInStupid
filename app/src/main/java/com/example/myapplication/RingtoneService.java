package com.example.myapplication;


import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.IBinder;


import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

public class RingtoneService extends Service {
    //public RingtoneService() {}
    MediaPlayer mediaPlayer = App.getmMediaPlayer();
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
        final NotificationManager mNM = (NotificationManager)
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
            mediaPlayer.start();
            mNM.notify(0, mNotify);
            this.isRunning = true;
            this.startId = 0;
        }
        else{
            mediaPlayer.stop();
            mediaPlayer.reset();
            this.isRunning = false;
            this.startId = 0;
        }
        return START_NOT_STICKY;
    }




}
