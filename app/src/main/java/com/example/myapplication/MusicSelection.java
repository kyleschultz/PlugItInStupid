package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MusicSelection extends AppCompatActivity implements View.OnClickListener{

    //make the radio buttons and put them in a group so only one can be selected
    private RadioGroup radioGroup;
    private RadioButton r1, r2, r3, r4;
    private Button b;

    private static final String PREFS_NAME = "prefs";
    private static final String PREF_DARK_THEME = "dark_theme";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SharedPreferences preferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        boolean useDarkTheme = preferences.getBoolean(PREF_DARK_THEME, false);
        // Check for dark theme
        if(useDarkTheme) {
            setTheme(R.style.AppTheme_Dark_ActionBar);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_selection);

        // 'Listen' for clicks
        b = findViewById(R.id.saveButton);
        b.setOnClickListener(this);
        r1 = findViewById(R.id.musicButton1);
        r1.setOnClickListener(this);
        r2 = findViewById(R.id.musicButton2);
        r2.setOnClickListener(this);
        r3 = findViewById(R.id.musicButton3);
        r3.setOnClickListener(this);
        r4 = findViewById(R.id.musicButton4);
        r4.setOnClickListener(this);

        radioGroup = findViewById(R.id.radioGroupMusic);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // show the user a toast based on what song they pick
                if(checkedId == R.id.musicButton1) {
                    Toast t = Toast.makeText(getApplicationContext(), "Beat It: Michael Jackson",
                            Toast.LENGTH_SHORT);

                    t.setGravity(Gravity.FILL_HORIZONTAL, 10, 1500);
                    t.show();
                }
                else if(checkedId == R.id.musicButton2) {
                    Toast t = Toast.makeText(getApplicationContext(), "System of a Down: BYOB",
                            Toast.LENGTH_SHORT);
                    t.setGravity(Gravity.FILL_HORIZONTAL, 10, 1500);
                    t.show();
                }
                else if(checkedId == R.id.musicButton3) {
                    Toast t = Toast.makeText(getApplicationContext(), "Red: Pieces",
                            Toast.LENGTH_SHORT);
                    t.setGravity(Gravity.FILL_HORIZONTAL, 10, 1500);
                    t.show();
                }
                else if(checkedId == R.id.musicButton4) {
                    Toast t = Toast.makeText(getApplicationContext(), "System of a Down: Toxicity",
                            Toast.LENGTH_SHORT);
                    t.setGravity(Gravity.FILL_HORIZONTAL, 10, 1500);
                    t.show();
                }

            }
        });
    }

    @Override
    public void onClick(View v) {
        int selectedId = radioGroup.getCheckedRadioButtonId();
        // Set media player based on selection
        if(selectedId == r1.getId()) {
            App.createMediaPlayer("beat");
            App.setMediaString("beat");
        }
        if(selectedId == r2.getId()) {
            App.createMediaPlayer("byob");
            App.setMediaString("byob");
        }
        if(selectedId == r3.getId()) {
            App.createMediaPlayer("pieces");
            App.setMediaString("pieces");
        }
        if(selectedId == r4.getId()) {
            App.createMediaPlayer("toxic");
            App.setMediaString("toxic");
        }
        // Go back to the main activity screen
        if(v.getId() == R.id.saveButton){
            finish();
        }
    }
}
