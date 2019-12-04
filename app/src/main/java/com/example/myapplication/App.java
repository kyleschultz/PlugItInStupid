package com.example.myapplication;

import android.app.Application;
import android.content.Context;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import android.icu.util.Calendar;
import android.media.MediaMetadata;
import android.media.MediaPlayer;
import android.net.Uri;

public class App extends Application {

    private static Context context;
    private static int ids = 0;
    private static ArrayList<String> views = new ArrayList<String>();
    private static Calendar calendar;
    public static String mediaString;


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


    public static void setMediaString(String s) {mediaString = s;}

    public static String getMediaString() {return mediaString;}



    public static void createMediaPlayer(String song){
        if(song == "beat"){
            //mMediaPlayer = MediaPlayer.create(App.getContext(), R.raw.beat_it);
            setMediaString("beat");
        }
        else if(song == "byob"){
            //mMediaPlayer = MediaPlayer.create(App.getContext(), R.raw.byob);
            setMediaString("beat");
        }
        else if(song == "pieces"){
            //mMediaPlayer = MediaPlayer.create(App.getContext(), R.raw.pieces);
            setMediaString("beat");
        }
        else{
            //mMediaPlayer = MediaPlayer.create(App.getContext(), R.raw.toxicity);
            setMediaString("beat");
        }

    }
}
