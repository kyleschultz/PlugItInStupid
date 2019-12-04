package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class PlugItInActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plug_it_in);
        ImageView checkButton = findViewById(R.id.alarmCheck);
        checkButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.alarmCheck){
            Intent serviceIntent = new Intent(getApplicationContext(),RingtoneService.class);
            serviceIntent.putExtra("extra", "no");
            getApplicationContext().startService(serviceIntent);
            Intent mActivity = new Intent(getApplicationContext(), MainActivity.class);
            mActivity.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            getApplicationContext().startActivity(mActivity);
        }
    }
}
