package com.example.frevosfermentaria;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;
import com.google.firebase.database.ValueEventListener;

import java.sql.Date;
import java.sql.Timestamp;


public class ActivityReservar extends AppCompatActivity {

    private DatabaseReference DBRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservar);


        Button reservar = (Button) findViewById(R.id.button7);


        reservar.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {

                DBRef = FirebaseDatabase.getInstance().getReference("Reservas");


                EditText editText3 = (EditText) findViewById(R.id.editText3); //nome
                EditText editText4 = (EditText) findViewById(R.id.editText4); //idade
                EditText editText5 = (EditText) findViewById(R.id.editText5); //celular
                EditText editText6 = (EditText) findViewById(R.id.editText6); //Bouchet
                EditText editText7 = (EditText) findViewById(R.id.editText7); //Tradicional


                if( editText3.getText().toString().trim().length() > 0 && editText4.getText().toString().trim().length() > 0 && editText5.getText().toString().trim().length() > 0 && editText6.getText().toString().trim().length() > 0 && editText7.getText().toString().trim().length() > 0 ) {

                    DBRef = FirebaseDatabase.getInstance().getReference("Reservas");
                    DBRef.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            Toast.makeText(ActivityReservar.this, error.toString(),
                                    Toast.LENGTH_LONG).show();
                        }

                    });


                    DBRef.child(editText5.getText().toString()).child("Nome").setValue(editText3.getText().toString());
                    DBRef.child(editText5.getText().toString()).child("Idade").setValue(editText4.getText().toString());
                    DBRef.child(editText5.getText().toString()).child("Celular").setValue(editText5.getText().toString());
                    DBRef.child(editText5.getText().toString()).child("hBouchet").setValue(editText6.getText().toString());
                    DBRef.child(editText5.getText().toString()).child("hTradicional").setValue(editText7.getText().toString());
                    DBRef.child(editText5.getText().toString()).child("Timestamp").setValue(ServerValue.TIMESTAMP);
                    DBRef.child(editText5.getText().toString()).child("Vendedor").setValue(FirebaseAuth.getInstance().getCurrentUser().getDisplayName());
                    DBRef.child(editText5.getText().toString()).child("Vendido").setValue(false);

                    Toast.makeText(ActivityReservar.this, "Reservado!", Toast.LENGTH_LONG).show();

                }

                else{
                    Toast.makeText(ActivityReservar.this, "Preencha todos os dados!", Toast.LENGTH_LONG).show();
                }




            }
        });



    }
}