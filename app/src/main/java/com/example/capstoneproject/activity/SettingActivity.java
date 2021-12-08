package com.example.capstoneproject.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.Toast;

import com.example.capstoneproject.AlarmReceiver;
import com.example.capstoneproject.R;
import com.example.capstoneproject.SettingPreference;

public class SettingActivity extends AppCompatActivity {

    Button btnLogout;

    Switch notifikasi;

    private static final String PRIMARY_CHANNEL_ID = "primary_notification_channel";

    private NotificationManager mNotifyManager;

    private static final int NOTIFICATION_ID = 0;

    private SettingPreference settingPreference;

    private SharedPreferences mPreferences;

    private final String SWITCH_KEY = "switch";

    private String sharedPrefFile = "com.example.capstoneproject.activity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        btnLogout = findViewById(R.id.btnLogout);
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SettingActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        mPreferences = getSharedPreferences(sharedPrefFile, MODE_PRIVATE);

        mNotifyManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        Intent notifyIntent = new Intent(this, AlarmReceiver.class);

        PendingIntent notifyPendingIntent = PendingIntent.getBroadcast
                (this, NOTIFICATION_ID, notifyIntent, PendingIntent.FLAG_IMMUTABLE);

        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);


        notifikasi = findViewById(R.id.notifikasi);

        settingPreference = new SettingPreference(this);

        setSwitchReminder();
        notifikasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (notifikasi.isChecked())
                {
                    long repeatInterval = AlarmManager.INTERVAL_FIFTEEN_MINUTES;

                    long triggerTime = SystemClock.elapsedRealtime() + repeatInterval;

                    // If the Toggle is turned on, set the repeating alarm with
                    // a 15 minute interval.
                    if (alarmManager != null) {
                        alarmManager.setInexactRepeating
                                (AlarmManager.ELAPSED_REALTIME_WAKEUP,
                                        triggerTime, repeatInterval,
                                        notifyPendingIntent);
                    }

                    settingPreference.setDailyReminder(true);
                    Toast.makeText(getApplicationContext(), "Pengingat Harian Diaktifkan", Toast.LENGTH_SHORT).show();
                }else
                {
                    mNotifyManager.cancelAll();

                    settingPreference.setDailyReminder(false);
                    if (alarmManager != null) {
                        alarmManager.cancel(notifyPendingIntent);
                    }
                    Toast.makeText(getApplicationContext(), "Pengingat Harian Dinonaktifkan", Toast.LENGTH_SHORT).show();
                }
            }
        });

        createNotificationChannel();
    }

    public void createNotificationChannel()
    {
        mNotifyManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            // Create a NotificationChannel

            NotificationChannel notificationChannel = new NotificationChannel(PRIMARY_CHANNEL_ID,
                    "Check Film Notification", NotificationManager
                    .IMPORTANCE_HIGH);

            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.RED);
            notificationChannel.enableVibration(true);
            notificationChannel.setDescription("Jangan Lupa Check Film Terbaru:)");
            mNotifyManager.createNotificationChannel(notificationChannel);

        }
    }

    private void setSwitchReminder() {
        if (settingPreference.getDailyReminder()) {
            notifikasi.setChecked(true);
        } else {
            notifikasi.setChecked(false);
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            this.finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}