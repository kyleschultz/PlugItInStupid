package com.example.myapplication;

// Android imports
import androidx.appcompat.app.AppCompatActivity;


import android.content.SharedPreferences;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.content.Intent;
import android.view.View;
import android.content.Context;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;

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
    AlarmState state;
    Calendar cal = Calendar.getInstance();
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
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        manager = (AlarmManager) getSystemService(ALARM_SERVICE);
        this.context = this;
        App.setContext(this.context);
        MediaPlayer m = App.getmMediaPlayer();
        System.out.println("in Main activity mediaplayer currently is " + m);
        if(m == null) {
            App.setmMediaPlayer(MediaPlayer.create(this, R.raw.byob));
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
        Intent fromAlarm = getIntent();
        if(fromAlarm != null && fromAlarm.getExtras() != null && App.getSwitchedDisplays() == false){
            String timeValue = fromAlarm.getExtras().getString("time");
            System.out.println(timeValue);
            views = App.getViews();
            System.out.println("views" + views);

            if(views.size() != 0){
                for(String str : views){
                    addAlarmTextView(str);
                }
            }
            App.addToArrayList(timeValue);
            addAlarmTextView(timeValue);
        }
        else {
            System.out.println("switched displays" + App.getSwitchedDisplays());
            views = App.getViews();
            System.out.println("views" + views);

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
        System.out.println(views);
        // Add this for-loop to restoring your list
        for(String str : views){
            addAlarmTextView(str);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState){
        // Save the values you need from your textview into "outSTate" -object
//        outState.putParcelableArrayList("key", toDoList);
        super.onSaveInstanceState(outState);
        outState.putStringArrayList("key", views);
        System.out.println(views);

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
        //LinearLayout wrapper = findViewById(R.id.AlarmLayout);
        wrapper.addView(createATextView(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT, RelativeLayout.ALIGN_PARENT_RIGHT,
                time.toString(), 24));
        App.addOnOff(true);
        wrapper.addView(createSwitch(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT, RelativeLayout.ALIGN_PARENT_RIGHT,
                time.toString(), 24));


    }

    public TextView createATextView(int layout_widh, int layout_height, int align,
                                    String text, int fontSize){
        TextView textView_item_name = new TextView(this);
        textView_item_name.setId(viewId);
        viewId++;
        RelativeLayout.LayoutParams _params = new RelativeLayout.LayoutParams(
                layout_widh, layout_height);

        //_params.setMargins(margin, margin, margin, margin);
        //_params.addRule(align);
        textView_item_name.setLayoutParams(_params);

        textView_item_name.setText(text);
        textView_item_name.setTextSize(TypedValue.COMPLEX_UNIT_SP, fontSize);
        textView_item_name.setTextColor(Color.parseColor("#000000"));
        // textView1.setBackgroundColor(0xff66ff66); // hex color 0xAARRGGBB
        //textView_item_name.setPadding(padding, padding, padding, padding);

        return textView_item_name;
    }

    public Switch createSwitch(int layout_widh, int layout_height, int align,
                               String text, int fontSize){
        final Switch onOff = new Switch(this);
        onOff.setId(switchId);
        final int tempId = switchId;
        switchId++;
        onOff.setText("On/Off");
        onOff.setChecked(App.getOnOffAtIndex(tempId));
        ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        onOff.setLayoutParams(lp);
        onOff.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(!isChecked){
                    //onOff.setChecked(false);
                    System.out.println("Temp id" + tempId);
                    state = new OffState();
                    App.changeOnOffIndex(tempId, false);
                    App.getIntents().get(tempId).cancel();
                    state.handle(manager, App.getIntents().get(tempId), cal, true);
                }
                else{
                    //onOff.setChecked(true);
                    cal = Calendar.getInstance();
                    state = new CreateState();
                    if(App.getDays().get(tempId).size() == 0){
                        String tempTime = App.getTimes().get(tempId);
                        String[] times = tempTime.split(":");
                        //System.out.println(times[0] + times[1]);
                        int hour = Integer.parseInt(times[0]);
                        int minute = Integer.parseInt(times[1]);
                        cal.setTimeInMillis(System.currentTimeMillis());
                        cal.set(Calendar.HOUR_OF_DAY, hour);
                        cal.set(Calendar.MINUTE, minute);
                        cal.set(Calendar.SECOND, 0);
                        cal.set(Calendar.MILLISECOND, 0);
                        if(System.currentTimeMillis() >= cal.getTimeInMillis()){
                            System.out.println("Here");
                            cal.setTimeInMillis(86400000 + cal.getTimeInMillis());
                        }
                        App.changeOnOffIndex(tempId, true);
                        Intent intent = new Intent(App.getContext(), AlarmReceiver.class);
                        intent.putExtra("extra", "yes");
                        PendingIntent pending = PendingIntent.getBroadcast(App.getContext(), App.getIds(), intent, PendingIntent.FLAG_UPDATE_CURRENT);
                        App.setIds(App.getIds() + 1);
                        App.setIntentAtIndex(tempId, pending);
                        state.handle(manager, pending, cal, false);
                    }
                    else{
                        ArrayList<Integer> tmpDays = App.getDays().get(tempId);
                        String tempTime = App.getTimes().get(tempId);
                        String[] times = tempTime.split(":");
                        //System.out.println(times[0] + times[1]);
                        int hour = Integer.parseInt(times[0]);
                        int minute = Integer.parseInt(times[1]);
                        for(int i = 0; i < tmpDays.size(); i++){
                            cal.setTimeInMillis(System.currentTimeMillis());
                            cal.set(Calendar.DAY_OF_WEEK, tmpDays.get(i));
                            //System.out.println(weekdays.get(i));
                            cal.set(Calendar.HOUR_OF_DAY, hour);
                            cal.set(Calendar.MINUTE, minute);
                            cal.set(Calendar.SECOND, 0);
                            cal.set(Calendar.MILLISECOND, 0);
                            if(cal.getTimeInMillis() < System.currentTimeMillis()) {
                                System.out.println("Here2");
                                cal.add(Calendar.DAY_OF_YEAR, 7);
                            }
                            App.changeOnOffIndex(tempId, true);
                            Intent intent = new Intent(App.getContext(), AlarmReceiver.class);
                            intent.putExtra("extra", "yes");
                            PendingIntent pending = PendingIntent.getBroadcast(App.getContext(), App.getIds(), intent, PendingIntent.FLAG_UPDATE_CURRENT);
                            App.setIds(App.getIds() + 1);
                            App.setIntentAtIndex(tempId, pending);
                            state.handle(manager, pending, cal, true);
                        }
                    }

                }
            }
        });
        return onOff;
    }
    private void toggleTheme(boolean darkTheme) {
        SharedPreferences.Editor editor = getSharedPreferences(PREFS_NAME, MODE_PRIVATE).edit();
        editor.putBoolean(PREF_DARK_THEME, darkTheme);
        editor.apply();
        App.setSwitchedDisplays(true);
        Intent intent = getIntent();
        //finish();
        startActivity(intent);
    }
}
