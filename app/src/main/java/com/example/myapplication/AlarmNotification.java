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
    // State pattern used here
    AlarmState state;
    Calendar cal;
    private int indexofIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm_notification);
        Intent i = getIntent();
        // Get extras from AddAlarm intent created earlier
        indexofIntent = i.getIntExtra("index", 0);
        // Initialize alarm manager
        manager = (AlarmManager) getSystemService(ALARM_SERVICE);
        myIntent = new Intent(App.getContext(), AlarmReceiver.class);
        // Listen for relevant objects
        checkButton = findViewById(R.id.alarmCheck);
        checkButton.setOnClickListener(this);
        snoozeButton = findViewById(R.id.snooze);
        snoozeButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        //if ok is pressed after the alarm goes off then turn off the media player and reset the calendar value to go off again in 24 hours
        if(v.getId() == R.id.alarmCheck){
            // Put extras for RingtoneService
            myIntent.putExtra("extra", "no");
            myIntent.putExtra("extra1", "main");
            cal = Calendar.getInstance();
            // Get the time for the alarm at the index
            String tempTime = App.getTimes().get(indexofIntent);
            String[] times = tempTime.split(":");
            // Get the hour and minute from the string and set them to int
            int hour = Integer.parseInt(times[0]);
            int minute = Integer.parseInt(times[1]);
            // Set the alarm time
            cal.setTimeInMillis(System.currentTimeMillis());
            cal.set(Calendar.HOUR_OF_DAY, hour);
            cal.set(Calendar.MINUTE, minute);
            cal.set(Calendar.SECOND, 0);
            cal.set(Calendar.MILLISECOND, 0);
            // State pattern used here
            state = new CreateState();
            manager = (AlarmManager) getSystemService(ALARM_SERVICE);
            // add 24 hours
            cal.setTimeInMillis(cal.getTimeInMillis() + 86400000);
            // set the reciever
            Intent intent = new Intent(this.getApplicationContext(), AlarmReceiver.class);
            intent.putExtra("extra", "yes");
            intent.putExtra("index", indexofIntent);
            // Create a new intent
            PendingIntent pending = PendingIntent.getBroadcast(this.getApplicationContext(), App.getIds(), intent, PendingIntent.FLAG_UPDATE_CURRENT);
            // Overwrite the correct intent at the index
            App.setIntentAtIndex(indexofIntent, pending);
            App.setIds(App.getIds() + 1);
            // Handle the alarm
            // State pattern used here
            state.handle(manager, pending, cal, false);
            // Turn off ringtone
            sendBroadcast(myIntent);

        }
        // If snoozed then stop the media player and set the timer/calendar to 10 minutes
        else if(v.getId() == R.id.snooze){
            // Put extras for RingtoneService
            myIntent.putExtra("extra", "no");
            myIntent.putExtra("extra1", "main");
            // initialize AlarmManager
            manager = (AlarmManager) getSystemService(ALARM_SERVICE);
            // set the state
            // State pattern used here
            state = new SnoozeState();
            cal = Calendar.getInstance();
            // Get the time the alarm was set
            String tempTime = App.getTimes().get(indexofIntent);
            String[] times = tempTime.split(":");
            int hour = Integer.parseInt(times[0]);
            int minute = Integer.parseInt(times[1]);
            // Set calendar to time initially set
            cal.setTimeInMillis(System.currentTimeMillis());
            cal.set(Calendar.HOUR_OF_DAY, hour);
            cal.set(Calendar.MINUTE, minute);
            cal.set(Calendar.SECOND, 0);
            cal.set(Calendar.MILLISECOND, 0);
            // Create a new intent for alarm receiver
            Intent intent = new Intent(this.getApplicationContext(), AlarmReceiver.class);
            // Add extras for RingtoneService
            intent.putExtra("extra", "yes");
            intent.putExtra("index", indexofIntent);
            // Create a new intent wiht the BroadcastReceiver
            PendingIntent pending = PendingIntent.getBroadcast(this.getApplicationContext(), App.getIds(), intent, PendingIntent.FLAG_UPDATE_CURRENT);
            // Set the new intent in the list
            App.setIntentAtIndex(indexofIntent, pending);
            App.setIds(App.getIds() + 1);
            // handle the snooze state
            // State pattern used here
            state.handle(manager, pending, cal, false);
            //send a toast to the user letting them know it will snooze for 10 minutes
            Toast t = Toast.makeText(getApplicationContext(), "Alarm Snoozed for 10 minutes",
                    Toast.LENGTH_SHORT);
            t.setGravity(Gravity.FILL_HORIZONTAL, 10, 1500);
            t.show();
            // Stop the current ringtone
            sendBroadcast(myIntent);
        }
    }
}
