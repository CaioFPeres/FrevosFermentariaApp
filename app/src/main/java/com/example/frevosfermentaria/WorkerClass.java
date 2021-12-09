package com.example.frevosfermentaria;

import android.content.Context;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class WorkerClass extends Worker {

    private Notification notification;
    private DatabaseReference DBRef;


    public WorkerClass(@NonNull Context context, @NonNull WorkerParameters params) {
        super(context, params);
        notification = new Notification(context);
        DBRef = FirebaseDatabase.getInstance().getReference("Reservas");
    }

    @Override
    public Result doWork() {

        long time = System.currentTimeMillis() - 400000;  //diferenca de tempo entre cliente e servidor

        DBRef.orderByChild("Timestamp").startAt(time).addChildEventListener(new ChildEventListener() {

            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                notification.notifyNotfication();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return Result.success();
    }
}
