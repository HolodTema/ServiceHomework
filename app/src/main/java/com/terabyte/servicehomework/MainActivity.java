package com.terabyte.servicehomework;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.NumberPicker;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private int mode;

    //only in sleep mode
    NumberPicker numberPickerHours, numberPickerMinutes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences shPreferences = getSharedPreferences(Const.SH_PREFERENCES_NAME, MODE_PRIVATE);

        mode = shPreferences.getInt(Const.SH_PREFERENCES_KEY_MODE, Const.MODE_SLEEP);
        if(mode==Const.MODE_SLEEP) {
            setSleepLayout();
        }
        if(mode==Const.MODE_RUN) {
            int hours = shPreferences.getInt(Const.SH_PREFERENCES_KEY_HOURS, -1);
            int minutes = shPreferences.getInt(Const.SH_PREFERENCES_KEY_MINUTES, -1);
            if (hours ==-1 | minutes==-1) {
                mode = Const.MODE_SLEEP;
                setSleepLayout();
            }
            else {
                setRunLayout(hours, minutes);
            }
        }
    }

    public void onClickButtonApply(View view) {
        if(mode==Const.MODE_SLEEP) {
            int hours = numberPickerHours.getValue();
            int minutes = numberPickerMinutes.getValue();
            Intent intent = new Intent(getApplicationContext(), NotificationService.class);
            intent.putExtra(Const.INTENT_KEY_HOURS, hours);
            intent.putExtra(Const.INTENT_KEY_MINUTES, minutes);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) startForegroundService(intent);
            else startService(intent);

            mode = Const.MODE_RUN;
            setRunLayout(hours, minutes);
        }
    }

    public void onClickButtonCancel(View view) {
        if(mode==Const.MODE_RUN) {
            Intent intent = new Intent(getApplicationContext(), NotificationService.class);
            stopService(intent);

            mode = Const.MODE_SLEEP;
            setSleepLayout();

        }
    }

    private void setSleepLayout() {
        setContentView(R.layout.activity_main_sleep);
        numberPickerHours = findViewById(R.id.numberPickerHours);
        numberPickerMinutes = findViewById(R.id.numberPickerMinutes);
        numberPickerHours.setMaxValue(23);
        numberPickerMinutes.setMaxValue(59);
    }

    private void setRunLayout(int hours, int minutes) {
        setContentView(R.layout.activity_main_run);
        TextView textServiceIsRunning = findViewById(R.id.textServiceIsRunning);
        textServiceIsRunning.setText("Notification will be shown at "+String.format("%02d:%02d", hours, minutes));
    }
}