package com.example.capstoneproject;

import android.app.Application;

import com.google.firebase.messaging.FirebaseMessaging;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        FirebaseMessaging.getInstance().subscribeToTopic("sample_notification");

    }
}
