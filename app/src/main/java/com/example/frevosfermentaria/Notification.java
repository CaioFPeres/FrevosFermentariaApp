package com.example.frevosfermentaria;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;


public class Notification {

    private Context context;
    private NotificationCompat.Builder notification;



    public Notification(Context context) {
        this.context = context;

        Intent notificationIntent = new Intent(context, MainActivity.class);
        notificationIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0,
            notificationIntent, PendingIntent.FLAG_ONE_SHOT);

        notification = new NotificationCompat.Builder(context, "FERMENTARIA")
            .setSmallIcon(R.drawable.aa)
            .setContentTitle("Reserva Feita!")
            .setContentText("Alguem reservou uma garrafa pelo site.")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(pendingIntent);

    }

    public void notifyNotfication(){
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
        notificationManager.notify(1337, notification.build());
    }



}
