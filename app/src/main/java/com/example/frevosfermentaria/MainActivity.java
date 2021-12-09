package com.example.frevosfermentaria;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.messaging.FirebaseMessaging;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button btnCalculadora = (Button) findViewById(R.id.button);
        Button btniSpindel = (Button) findViewById(R.id.button4);
        Button btnReservar = (Button) findViewById(R.id.button2);
        Button btnReservas = (Button) findViewById(R.id.button3);


        btnCalculadora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCalculadora();
            }
        });


        btniSpindel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openiSpindel();
            }
        });


        btnReservar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openReservar();
            }
        });


        btnReservas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openReservas();
            }
        });


        Button btnLogin = findViewById(R.id.button10);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signOut();
            }
        });

    }



    public void signOut() {
        FirebaseAuth.getInstance().signOut();
        FirebaseMessaging.getInstance().unsubscribeFromTopic("FERMENTARIA");
        Intent intent = new Intent(this, ActivityLogin.class);
        startActivity(intent);
    }


    public void openReservar() {
        Intent intent = new Intent(this, ActivityReservar.class);
        startActivity(intent);
    }


    public void openCalculadora() {
        Intent intent = new Intent(this, ActivityCalculadora.class);
        startActivity(intent);
    }


    public void openReservas() {
        Intent intent = new Intent(this, ActivityReservas.class);
        startActivity(intent);
    }


    public void openiSpindel() {
        Intent intent = new Intent(this, ActivityiSpindel.class);
        startActivity(intent);
    }


}