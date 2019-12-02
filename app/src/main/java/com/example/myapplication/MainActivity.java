package com.example.myapplication;

// Android imports
import androidx.appcompat.app.AppCompatActivity;

import android.app.LauncherActivity;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.content.Intent;
import android.view.View;
import android.content.Context;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Context context;
    private ArrayList<String> views;
    LinearLayout wrapper;
    private int id = 0;
    // 'Listen' for clicks
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.context = this;
        App.setContext(this.context);
        App.setmMediaPlayer(MediaPlayer.create(this, R.raw.byob));
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

        Intent fromAlarm = getIntent();
        if(fromAlarm != null && fromAlarm.getExtras() != null){
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
        wrapper.addView(createSwitch(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT, RelativeLayout.ALIGN_PARENT_RIGHT,
                time.toString(), 24));


    }

    public TextView createATextView(int layout_widh, int layout_height, int align,
                                    String text, int fontSize){
        TextView textView_item_name = new TextView(this);
        textView_item_name.setId(id);
        id++;
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
        Switch onOff = new Switch(this);
        onOff.setId(id);
        id++;
        onOff.setText("On/Off");
        onOff.setChecked(true);
        ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        onOff.setLayoutParams(lp);
        return onOff;
    }

}
