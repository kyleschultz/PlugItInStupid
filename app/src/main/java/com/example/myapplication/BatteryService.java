package com.example.myapplication;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.IBinder;
import android.util.Log;

public class BatteryService extends Service {
    public BatteryService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand (Intent intent, int flags, int startId){
        // Create an IntentFilter to know the battery status
        IntentFilter ifilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        Intent batteryStatus = getApplicationContext().registerReceiver(null, ifilter);
        // Get the charging state
        int status = batteryStatus.getIntExtra(BatteryManager.EXTRA_STATUS, -1);
        // check if charging or not
        boolean isCharging = status == BatteryManager.BATTERY_STATUS_CHARGING ||
                status == BatteryManager.BATTERY_STATUS_FULL;
        // if it is charging set the BroadcastReceiver
        if(!isCharging){
            this.registerReceiver(this.batteryChangeReceiver,  ifilter);
        }

        return super.onStartCommand(intent, flags, startId);
    }

    private final BroadcastReceiver batteryChangeReceiver = new BroadcastReceiver() {

        @Override
        public void onReceive(final Context context, final Intent intent) {
            // Only show plug it in notification once
            if(!App.getPlugItInCalled()) {
                checkBatteryLevel(intent);
            }
        }
    };

    private void checkBatteryLevel(Intent batteryChangeIntent) {
        App.setPlugItInCalled(true);
        // calculate the battery level
        final int currLevel = batteryChangeIntent.getIntExtra(
                BatteryManager.EXTRA_LEVEL, -1);
        final int maxLevel = batteryChangeIntent.getIntExtra(
                BatteryManager.EXTRA_SCALE, -1);
        final int percentage = (int) Math.round((currLevel * 100.0) / maxLevel);

        Log.d("BatteryService", "current battery level: " + percentage);

        // low battery
        if (percentage <= 15) {
            Log.d("Battery low", "battery low");
            // Stop the service
            Intent stopService = new Intent(App.getContext(), BatteryService.class);
            App.getContext().stopService(stopService);
            // Create an Intent for PlugItInActivity
            Intent intent = new Intent(getBaseContext(), PlugItInActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            App.getContext().startActivity(intent);
            // Intent for RingtoneService
            Intent serviceIntent = new Intent(App.getContext(),RingtoneService.class);
            serviceIntent.putExtra("extra", "yes");
            // Start PlugItInActivity
            App.getContext().startService(serviceIntent);

        }
        // unregister the BroadcastReceiver
        unregisterReceiver(batteryChangeReceiver);
    }
}
