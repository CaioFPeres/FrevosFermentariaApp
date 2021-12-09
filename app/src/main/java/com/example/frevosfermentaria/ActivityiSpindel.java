package com.example.frevosfermentaria;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.renderscript.Sampler;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.text.DecimalFormat;


public class ActivityiSpindel extends AppCompatActivity {

    private DatabaseReference DBRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ispindel);

        final TextView textView30 = (TextView) findViewById(R.id.textView30);
        final TextView textView31 = (TextView) findViewById(R.id.textView31);
        final TextView textView23 = (TextView) findViewById(R.id.textView23);
        final TextView textView25 = (TextView) findViewById(R.id.textView25);
        final TextView textView39 = findViewById(R.id.textView39);
        final EditText editText = (EditText) findViewById(R.id.editText);
        final EditText editText2 = (EditText) findViewById(R.id.editText2);
        final Switch switch1 = findViewById(R.id.switch1);
        final Button buttonDef = (Button) findViewById(R.id.button5);
        final Button buttonLeva = (Button) findViewById(R.id.button6);


        DBRef = FirebaseDatabase.getInstance().getReference("iSpindel1").child("Densidade");
        DBRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                textView30.setText(snapshot.getValue().toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(ActivityiSpindel.this, error.toString(),
                        Toast.LENGTH_LONG).show();
            }

        });



        DBRef = FirebaseDatabase.getInstance().getReference("iSpindel1").child("Voltagem");
        DBRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                textView31.setText( snapshot.getValue().toString() + "V" );

                textView39.setText(  Double.toString( (Math.ceil( ( ( Float.parseFloat(snapshot.getValue().toString()) - 3.3)*100)/0.9) * 100) / 100  ) + "%" );
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(ActivityiSpindel.this, error.toString(),
                        Toast.LENGTH_LONG).show();
            }

        });


        DBRef = FirebaseDatabase.getInstance().getReference("iSpindel1").child("Temperatura");
        DBRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                textView23.setText(snapshot.getValue().toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(ActivityiSpindel.this, error.toString(),
                        Toast.LENGTH_LONG).show();
            }

        });


        DBRef = FirebaseDatabase.getInstance().getReference("iSpindel1Control").child("SetTemp");
        DBRef.addValueEventListener(new ValueEventListener() {
            @SuppressLint("DefaultLocale")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                textView25.setText( snapshot.getValue().toString() );
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(ActivityiSpindel.this, error.toString(),
                        Toast.LENGTH_LONG).show();
            }

        });


        DBRef = FirebaseDatabase.getInstance().getReference("iSpindel1Control").child("Leva");
        DBRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                editText2.setText(snapshot.getValue().toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(ActivityiSpindel.this, error.toString(),
                        Toast.LENGTH_LONG).show();
            }

        });


        DBRef = FirebaseDatabase.getInstance().getReference("iSpindel1Control").child("Ativo");
        DBRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                switch1.setChecked((Boolean) snapshot.getValue());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(ActivityiSpindel.this, error.toString(),
                        Toast.LENGTH_LONG).show();
            }

        });




        buttonDef.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(editText.getText().toString().trim().length() > 0) {
                    DBRef = FirebaseDatabase.getInstance().getReference("iSpindel1Control").child("SetTemp");


                    DBRef.setValue( Math.ceil( Float.parseFloat( editText.getText().toString() ) * 1000) / 1000 ) ;
                }
                else{
                    Toast.makeText(ActivityiSpindel.this, "Preencha o campo!", Toast.LENGTH_LONG).show();
                }
            }
        });


        switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // TODO Auto-generated method stub

                DBRef = FirebaseDatabase.getInstance().getReference("iSpindel1Control").child("Ativo");
                DBRef.setValue(switch1.isChecked());

            }
        });




        buttonLeva.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if(editText2.getText().toString().trim().length() > 0) {
                DBRef = FirebaseDatabase.getInstance().getReference("iSpindel1Control").child("Leva");
                DBRef.setValue(editText2.getText().toString());
                }
                else{
                    Toast.makeText(ActivityiSpindel.this, "Preencha o campo!", Toast.LENGTH_LONG).show();
                }
            }
        });








    }







}