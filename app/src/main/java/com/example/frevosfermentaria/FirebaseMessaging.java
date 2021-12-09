package com.example.frevosfermentaria;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.core.app.NotificationCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class FirebaseMessaging extends FirebaseMessagingService {

    public FirebaseMessaging() {
    }

    public void onMessageReceived(RemoteMessage message){

        Notification notification = new Notification(this);
        notification.notifyNotfication();

    }




}
