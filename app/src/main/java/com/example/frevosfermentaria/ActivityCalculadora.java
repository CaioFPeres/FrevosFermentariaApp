package com.example.frevosfermentaria;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ActivityCalculadora extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculadora);




        Button btn = (Button) findViewById(R.id.button);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                EditText edittext2 = (EditText) findViewById(R.id.editTextNumber);
                EditText edittext3 = (EditText) findViewById(R.id.editTextNumber2);
                EditText edittext4 = (EditText) findViewById(R.id.editTextNumber3);
                TextView textView14 = (TextView) findViewById(R.id.textView13);
                TextView textView15 = (TextView) findViewById(R.id.textView16);
                TextView textView16 = (TextView) findViewById(R.id.textView18);
                TextView textView17 = (TextView) findViewById(R.id.textView20);
                TextView textView18 = (TextView) findViewById(R.id.textView21);


                if( edittext2.getText().toString().trim().length() > 0 && edittext3.getText().toString().trim().length() > 0 && edittext4.getText().toString().trim().length() > 0 ) {

                    float mi, ma, mt, vf, ng;

                    String v1 = edittext2.getText().toString();
                    String v2 = edittext3.getText().toString();
                    String v3 = edittext4.getText().toString();

                    float vi = Float.valueOf(v1);
                    float abv = Float.valueOf(v2);
                    float df = Float.valueOf(v3);


                    mi = (float) 0.025298311 * abv * vi;
                    vf = (float) (-(2.5 * (Math.pow(10, 8)) * vi) / ((6.15942029 * (Math.pow(10, 8) * df) - 8.65942029 * (Math.pow(10, 8)))));
                    ma = (float) ((df - 1) * 3.4 * vf);
                    mt = ma + mi;
                    ng = (float) (vf / 0.75);

                    String v4 = new Float(mi).toString();
                    String v5 = new Float(ma).toString();
                    String v6 = new Float(mt).toString();
                    String v7 = new Float(vf).toString();
                    String v8 = new Float(ng).toString();

                    textView14.setText(v4);
                    textView15.setText(v5);
                    textView16.setText(v6);
                    textView17.setText(v7);
                    textView18.setText(v8);

                }
                else{
                    Toast.makeText(ActivityCalculadora.this, "Preencha todos os campos!", Toast.LENGTH_LONG).show();
                }


            }
        });


    }
}