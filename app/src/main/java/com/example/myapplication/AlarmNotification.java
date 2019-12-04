package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.icu.util.Calendar;
import android.widget.Toast;

public class AlarmNotification extends AppCompatActivity implements View.OnClickListener{

    private ImageView checkButton, snoozeButton;
    Intent myIntent;
    AlarmManager manager;
    AlarmState state;
    Calendar cal;
    private int indexofIntent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm_notification);
        Intent i = getIntent();
        indexofIntent = i.getIntExtra("index", 0);
        System.out.println(indexofIntent);
        manager = (AlarmManager) getSystemService(ALARM_SERVICE);
        myIntent = new Intent(App.getContext(), AlarmReceiver.class);
        checkButton = findViewById(R.id.alarmCheck);
        checkButton.setOnClickListener(this);
        snoozeButton = findViewById(R.id.snooze);
        snoozeButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.alarmCheck){
            myIntent.putExtra("extra", "no");
            myIntent.putExtra("extra1", "main");
            cal = Calendar.getInstance();
            String tempTime = App.getTimes().get(indexofIntent);
            String[] times = tempTime.split(":");
            int hour = Integer.parseInt(times[0]);
            int minute = Integer.parseInt(times[1]);
            cal.setTimeInMillis(System.currentTimeMillis());
            cal.set(Calendar.HOUR_OF_DAY, hour);
            cal.set(Calendar.MINUTE, minute);
            cal.set(Calendar.SECOND, 0);
            cal.set(Calendar.MILLISECOND, 0);
            state = new CreateState();
            manager = (AlarmManager) getSystemService(ALARM_SERVICE);
            cal.setTimeInMillis(cal.getTimeInMillis() + 86400000);
            Intent intent = new Intent(this.getApplicationContext(), AlarmReceiver.class);
            intent.putExtra("extra", "yes");
            intent.putExtra("index", indexofIntent);
            PendingIntent pending = PendingIntent.getBroadcast(this.getApplicationContext(), App.getIds(), intent, PendingIntent.FLAG_UPDATE_CURRENT);
            App.setIntentAtIndex(indexofIntent, pending);
            App.setIds(App.getIds() + 1);
            state.handle(manager, pending, cal, false);
            sendBroadcast(myIntent);

        }
        else if(v.getId() == R.id.snooze){
            myIntent.putExtra("extra", "no");
            myIntent.putExtra("extra1", "main");
            manager = (AlarmManager) getSystemService(ALARM_SERVICE);
            state = new SnoozeState();
            cal = Calendar.getInstance();
            String tempTime = App.getTimes().get(indexofIntent);
            String[] times = tempTime.split(":");
            int hour = Integer.parseInt(times[0]);
            int minute = Integer.parseInt(times[1]);
            cal.setTimeInMillis(System.currentTimeMillis());
            cal.set(Calendar.HOUR_OF_DAY, hour);
            cal.set(Calendar.MINUTE, minute);
            cal.set(Calendar.SECOND, 0);
            cal.set(Calendar.MILLISECOND, 0);
            Intent intent = new Intent(this.getApplicationContext(), AlarmReceiver.class);
            intent.putExtra("extra", "yes");
            intent.putExtra("index", indexofIntent);
            PendingIntent pending = PendingIntent.getBroadcast(this.getApplicationContext(), App.getIds(), intent, PendingIntent.FLAG_UPDATE_CURRENT);
            App.setIntentAtIndex(indexofIntent, pending);
            App.setIds(App.getIds() + 1);
            state.handle(manager, pending, cal, false);
            Toast t = Toast.makeText(getApplicationContext(), "Alarm Snoozed for 10 minutes",
                    Toast.LENGTH_SHORT);
            t.setGravity(Gravity.FILL_HORIZONTAL, 10, 1500);
            t.show();
            sendBroadcast(myIntent);
        }
    }
}
