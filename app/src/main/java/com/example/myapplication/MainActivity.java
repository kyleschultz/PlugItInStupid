package com.example.myapplication;

// AppCompatActivity import
import androidx.appcompat.app.AppCompatActivity;

// Android imports
import android.content.SharedPreferences;
import android.content.Context;
import android.content.Intent;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.ViewGroup;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;

import java.util.ArrayList;
import android.icu.util.Calendar;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Context context;
    private ArrayList<String> views;
    LinearLayout wrapper;
    private static final String PREFS_NAME = "prefs";
    private static final String PREF_DARK_THEME = "dark_theme";
    private int viewId = 0;
    private int switchId = 0;
    AlarmManager manager;
    // State pattern used here
    AlarmState state;
    Calendar cal;
    private BatteryService bService;
    // 'Listen' for clicks
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Checking for useDarkTheme boolean in this manner is applied to
        // all activities in our app to avoid 'BaseActivity' anti-pattern
        SharedPreferences preferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        boolean useDarkTheme = preferences.getBoolean(PREF_DARK_THEME, false);
        if(useDarkTheme) {
            setTheme(R.style.AppTheme_Dark_ActionBar);
        }
        if(bService == null && !App.getPlugItInCalled()){
            Intent batteryIntent = new Intent(this, BatteryService.class);
            startService(batteryIntent);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        manager = (AlarmManager) getSystemService(ALARM_SERVICE);
        this.context = this;
        App.setContext(this.context);
        String song = App.getMediaString();
        if(song == null){
            App.setMediaString("byob");
            App.createMediaPlayer("byob");
        }

        // Add alarm
        ImageView addButton = findViewById(R.id.addAlarm);
        addButton.setOnClickListener(this);

        // Ringtone
        ImageView toneButton = findViewById(R.id.select_ringtone);
        toneButton.setOnClickListener(this);

        // Countdown timer
        ImageView timerButton = findViewById(R.id.countdownTimer);
        timerButton.setOnClickListener(this);

        // Timezone
        Button tzButton = findViewById(R.id.timeZoneButton);
        tzButton.setOnClickListener(this);
        wrapper = findViewById(R.id.AlarmLayout);

        // Toggle Dark Mode
        Switch toggle = findViewById(R.id.switch1);
        toggle.setChecked(useDarkTheme);
        toggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton view, boolean isChecked) {
                toggleTheme(isChecked);
            }
        });
        // Below accounts for adding an alarm to the UI and
        // checks to see if the display was switched
        Intent fromAlarm = getIntent();
        if(fromAlarm != null && fromAlarm.getExtras() != null && !App.getSwitchedDisplays()){
            String timeValue = fromAlarm.getExtras().getString("time");
            views = App.getViews();

            if(views.size() != 0){
                for(String str : views){
                    addAlarmTextView(str);
                }
            }
            App.addToArrayList(timeValue);
            addAlarmTextView(timeValue);
        }
        else {
            views = App.getViews();

            if (views.size() != 0) {
                for (String str : views) {
                    addAlarmTextView(str);
                }
            }
        }
        App.setSwitchedDisplays(false);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState){

        super.onRestoreInstanceState(savedInstanceState);

        views = savedInstanceState.getStringArrayList("key");
        // Add this for-loop to restoring your list
        for(String str : views){
            addAlarmTextView(str);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState){
        // Save the values you need from your textview into "outSTate" -object
        super.onSaveInstanceState(outState);
        outState.putStringArrayList("key", views);

    }

    // Controls what happens upon clicking on features
    @Override
    public void onClick(View v) {
        // Add alarm
        if(v.getId() == R.id.addAlarm) {
            Intent myintent = new Intent(MainActivity.this, AddAlarm.class);
            startActivity(myintent);
        }

        // Countdown timer
        else if(v.getId() == R.id.countdownTimer){
            Intent myintent = new Intent(MainActivity.this, TimerScreen.class);
            startActivity(myintent);
        }

        // Ringtone
        else if(v.getId() == R.id.select_ringtone){
            Intent myintent = new Intent(MainActivity.this, MusicSelection.class);
            startActivity(myintent);
        }

        // Timezone
        else if(v.getId() == R.id.timeZoneButton){
            Intent myintent = new Intent(MainActivity.this, ChangeTimeZone.class);
            startActivity(myintent);
        }

    }

    public void addAlarmTextView(String time){
        // Add a TextView and a Switch
        wrapper.addView(createATextView(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT, time, 24));
        App.addOnOff(true);
        wrapper.addView(createSwitch());


    }

    public TextView createATextView(int layout_widh, int layout_height, String text, int fontSize){
        // Create a TextView to add to the UI
        TextView textView_item_name = new TextView(this);
        textView_item_name.setId(viewId);
        viewId++;
        RelativeLayout.LayoutParams _params = new RelativeLayout.LayoutParams(
                layout_widh, layout_height);
        // Set params
        textView_item_name.setLayoutParams(_params);
        textView_item_name.setText(text);
        textView_item_name.setTextSize(TypedValue.COMPLEX_UNIT_SP, fontSize);
        textView_item_name.setTextColor(Color.parseColor("#4abf51"));

        return textView_item_name;
    }

    public Switch createSwitch(){
        // Create an on off switch
        final Switch onOff = new Switch(this);
        onOff.setId(switchId);
        final int tempId = switchId;
        switchId++;
        onOff.setText("On/Off");
        // Get if it is on or off
        onOff.setChecked(App.getOnOffAtIndex(tempId));
        // Match parents layout
        ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        onOff.setLayoutParams(lp);
        onOff.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(!isChecked){
                    // If switched off, state pattern used here
                    state = new OffState();
                    App.changeOnOffIndex(tempId, false);
                    App.getIntents().get(tempId).cancel();
                    // Handle the alarm, state pattern used here
                    state.handle(manager, App.getIntents().get(tempId), cal, true);
                }
                else{
                    cal = Calendar.getInstance();
                    // State pattern used here
                    state = new CreateState();
                    if(App.getDays().get(tempId).size() == 0){
                        // If the alarm was not set for multiple days
                        String tempTime = App.getTimes().get(tempId);
                        // Get the time
                        String[] times = tempTime.split(":");
                        int hour = Integer.parseInt(times[0]);
                        int minute = Integer.parseInt(times[1]);
                        // Set the calendar time
                        cal.setTimeInMillis(System.currentTimeMillis());
                        cal.set(Calendar.HOUR_OF_DAY, hour);
                        cal.set(Calendar.MINUTE, minute);
                        cal.set(Calendar.SECOND, 0);
                        cal.set(Calendar.MILLISECOND, 0);
                        // If it was set for the past add a day
                        if(System.currentTimeMillis() >= cal.getTimeInMillis()){
                            cal.setTimeInMillis(86400000 + cal.getTimeInMillis());
                        }
                        // Change the switch in App
                        App.changeOnOffIndex(tempId, true);
                        // Create a new intent for AlarmReceiver
                        Intent intent = new Intent(App.getContext(), AlarmReceiver.class);
                        // Put extra for the Ringtone
                        intent.putExtra("extra", "yes");
                        // Create the new PendingIntent
                        PendingIntent pending = PendingIntent.getBroadcast(App.getContext(), App.getIds(), intent, PendingIntent.FLAG_UPDATE_CURRENT);
                        App.setIds(App.getIds() + 1);
                        // Change the pending intent corresponding to the switches index
                        App.setIntentAtIndex(tempId, pending);
                        // Handle the alarm, state pattern used here
                        state.handle(manager, pending, cal, false);
                    }
                    else{
                        // Alarm set for multiple days
                        ArrayList<Integer> tmpDays = App.getDays().get(tempId);
                        // Get the time
                        String tempTime = App.getTimes().get(tempId);
                        String[] times = tempTime.split(":");
                        int hour = Integer.parseInt(times[0]);
                        int minute = Integer.parseInt(times[1]);
                        // Set alarm for each day
                        for(int i = 0; i < tmpDays.size(); i++){
                            // Set day and time of alarm
                            cal.setTimeInMillis(System.currentTimeMillis());
                            cal.set(Calendar.DAY_OF_WEEK, tmpDays.get(i));
                            cal.set(Calendar.HOUR_OF_DAY, hour);
                            cal.set(Calendar.MINUTE, minute);
                            cal.set(Calendar.SECOND, 0);
                            cal.set(Calendar.MILLISECOND, 0);
                            // If day was in the past set it for 7 days from now
                            if(cal.getTimeInMillis() < System.currentTimeMillis()) {
                                cal.add(Calendar.DAY_OF_YEAR, 7);
                            }
                            // Change the switch to on
                            App.changeOnOffIndex(tempId, true);
                            // Create an intent for alarm receiver
                            Intent intent = new Intent(App.getContext(), AlarmReceiver.class);
                            // Put yes for ringtone service
                            intent.putExtra("extra", "yes");
                            PendingIntent pending = PendingIntent.getBroadcast(App.getContext(), App.getIds(), intent, PendingIntent.FLAG_UPDATE_CURRENT);
                            App.setIds(App.getIds() + 1);
                            // Change the intent at the index
                            App.setIntentAtIndex(tempId, pending);
                            // Handle the alarm, state pattern used here
                            state.handle(manager, pending, cal, true);
                        }
                    }
                }
            }
        });
        // Return the switch
        return onOff;
    }
    private void toggleTheme(boolean darkTheme) {
        SharedPreferences.Editor editor = getSharedPreferences(PREFS_NAME, MODE_PRIVATE).edit();
        editor.putBoolean(PREF_DARK_THEME, darkTheme);
        editor.apply();
        App.setSwitchedDisplays(true);
        Intent intent = getIntent();
        finish();
        startActivity(intent);
    }
}
