package com.example.myapplication;

import android.app.Application;
import android.content.Context;

import java.util.ArrayList;
import android.icu.util.Calendar;
import android.media.MediaPlayer;

public class App extends Application {

    private static Context context;
    private static int ids = 0;
    private static ArrayList<String> views = new ArrayList<String>();
    private static Calendar calendar;
    private static MediaPlayer mMediaPlayer;
    private static boolean ringtoneServiceRunning;

    public static Context getContext() {
        return context;
    }

    public static void setContext(Context c) {
        context = c;
    }

    public static int getIds() { return ids; }

    public static void setIds(int id) {
        ids = id;
    }

    public static ArrayList<String> getViews() {
        return views;
    }

    public static void addToArrayList(String view){
        views.add(view);
    }

    public static void setCalendar(Calendar c){
        calendar = c;
    }

    public static Calendar getCalendar(){
        return calendar;
    }

    public static void setmMediaPlayer(MediaPlayer m) { mMediaPlayer = m;}

    public static MediaPlayer getmMediaPlayer() {return mMediaPlayer;}

    public static void setRingtoneServiceRunning( boolean r) {ringtoneServiceRunning = r;};

    public static boolean getRingtoneServiceRunning() {return ringtoneServiceRunning;}
}
