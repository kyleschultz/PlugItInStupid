package com.example.myapplication;

import android.app.Application;
import android.app.PendingIntent;
import android.content.Context;

import java.util.ArrayList;
import android.icu.util.Calendar;
import android.widget.Switch;
import android.media.MediaPlayer;

public class App extends Application {

    private static Context context;
    private static int ids = 0;
    private static ArrayList<String> views = new ArrayList<String>();
    private static ArrayList<Switch> switches = new ArrayList<Switch>();
    private static ArrayList<PendingIntent> alarms = new ArrayList<PendingIntent>();
    private static ArrayList<String> times = new ArrayList<String>();
    private static ArrayList<ArrayList<Integer>> days = new ArrayList<ArrayList<Integer>>();
    private static ArrayList<Boolean> onOff = new ArrayList<Boolean>();

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

    public static void addIntent(PendingIntent intent){
        alarms.add(intent);
    }

    public static void addTime(String time){
        times.add(time);
    }

    public static void addDays(ArrayList<Integer> day){
        days.add(day);
    }

    public static ArrayList<PendingIntent> getIntents(){
        return alarms;
    }

    public static ArrayList<String> getTimes(){
        return times;
    }

    public static ArrayList<ArrayList<Integer>> getDays(){
        return days;
    }

    public static ArrayList<Switch> getSwitches(){
        return switches;
    }

    public static void addSwitch(Switch s){
        switches.add(s);
    }

    public static void addOnOff(boolean b){
        onOff.add(b);
    }

    public static boolean getOnOffAtIndex(int ind){
        return onOff.get(ind);
    }

    public static void changeOnOffIndex(int ind, boolean elem){
        onOff.set(ind, elem);
    }

    public static void setIntentAtIndex(int ind, PendingIntent intent){
        alarms.set(ind, intent);
    }
    public static void setmMediaPlayer(MediaPlayer m) {
        mMediaPlayer = m;
    }

    public static MediaPlayer getmMediaPlayer() {return mMediaPlayer;}

    public static void setRingtoneServiceRunning( boolean r) {ringtoneServiceRunning = r;};

    public static boolean getRingtoneServiceRunning() {return ringtoneServiceRunning;}

}
