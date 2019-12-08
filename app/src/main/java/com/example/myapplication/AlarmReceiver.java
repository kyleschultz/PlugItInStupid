package com.example.myapplication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class AlarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        System.out.println("In receiver");
        String state = intent.getExtras().getString("extra");

        Context main = App.getContext();

        // create an intent to the AlarmNotification service
        if(intent.getExtras().getString("extra1") == null) {
            Intent notify = new Intent(context, AlarmNotification.class);
            int temp = intent.getIntExtra("index", 0);
            notify.putExtra("index", temp);
            main.startActivity(notify);
        }
        else{
            // create an Intent to MainActivity
            Intent mActivity = new Intent(context, MainActivity.class);
            main.startActivity(mActivity);
        }
        // create an intent to the ringtone service
        Intent serviceIntent = new Intent(context,RingtoneService.class);
        serviceIntent.putExtra("extra", state);
        context.startService(serviceIntent);
    }
}
