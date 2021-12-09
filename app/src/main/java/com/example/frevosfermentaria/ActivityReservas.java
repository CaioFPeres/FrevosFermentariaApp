package com.example.frevosfermentaria;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CheckedTextView;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.util.ArrayList;

import static android.view.View.generateViewId;
import static android.webkit.ConsoleMessage.MessageLevel.LOG;

public class ActivityReservas extends AppCompatActivity {


    private DatabaseReference DBRef;
    int i = 0;


    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservas);


        final EditText searchInput = (EditText) findViewById(R.id.editText);
        final EditText deleteInput = (EditText) findViewById(R.id.editText2);
        final Button buttonDelete = (Button) findViewById(R.id.button8);


        final ArrayList<TextView> celulares = new ArrayList<TextView>();
        final ArrayList<TextView> vendedores = new ArrayList<TextView>();
        final ArrayList<CheckBox> checkboxes = new ArrayList<CheckBox>();
        final ArrayList<TableRow> rowList = new ArrayList<TableRow>();


        //tabela
        final TableLayout tableLayout = findViewById(R.id.tableLayout);


        //definindo constraint layout para colocar textView voando por aí
        final ConstraintLayout constraint = findViewById(R.id.constraint);
        final ConstraintSet constraintSet = new ConstraintSet();
        constraintSet.clone(constraint);
        final TextView nome = new TextView(ActivityReservas.this);
        nome.setId(generateViewId());
        nome.setBackground(getResources().getDrawable(R.drawable.borderandbackground));
        nome.setPadding(15, 10, 0, 0);
        nome.setTextColor(Color.parseColor("#FFFFFF"));
        nome.setVisibility(View.INVISIBLE);
        constraint.addView(nome);



        /*
        //para definir margem na table (nao ta fazendo nada agr mas pode ser util)
        TableLayout.LayoutParams lp = new TableLayout.LayoutParams(TableLayout.LayoutParams.WRAP_CONTENT, TableLayout.LayoutParams.WRAP_CONTENT);
        lp.setMargins(0, 0, 0, 0);
        tableRow.setLayoutParams(lp);
        */


        DBRef = FirebaseDatabase.getInstance().getReference("Reservas");
        DBRef.orderByChild("Timestamp").addListenerForSingleValueEvent(new ValueEventListener() {
            @SuppressLint("DefaultLocale")
            @Override

            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (final DataSnapshot child : snapshot.getChildren()) {

                    boolean backupVendido = false;

                    //criar linha na tabela
                    final TableRow tableRow = new TableRow(ActivityReservas.this);
                    //adicionar em uma lista
                    rowList.add(tableRow);

                    final CheckedTextView textView = new CheckedTextView(ActivityReservas.this);
                    TextView textView2 = new TextView(ActivityReservas.this);
                    TextView textView3 = new TextView(ActivityReservas.this);
                    TextView textView4 = new TextView(ActivityReservas.this);
                    TextView textView5 = new TextView(ActivityReservas.this);
                    CheckBox checkbox = new CheckBox(ActivityReservas.this);

                    celulares.add(textView4);
                    vendedores.add(textView5);
                    checkboxes.add(checkbox);

                    textView.setText(child.child("Nome").getValue().toString());
                    textView2.setText(child.child("hBouchet").getValue().toString());
                    textView3.setText(child.child("hTradicional").getValue().toString());
                    celulares.get(i).setText(child.child("Celular").getValue().toString());
                    vendedores.get(i).setText(child.child("Vendedor").getValue().toString());
                    checkboxes.get(i).setChecked(Boolean.parseBoolean(child.child("Vendido").getValue().toString()));


                    final Drawable d = getResources().getDrawable(R.drawable.border);
                    textView.setBackground(d);
                    textView2.setBackground(d);
                    textView3.setBackground(d);
                    celulares.get(i).setBackground(d);
                    vendedores.get(i).setBackground(d);
                    checkboxes.get(i).setBackground(d);


                    textView.setTextColor(Color.parseColor("#FFFFFF"));
                    textView.setPadding((int) getResources().getDimension(R.dimen.paddingleft), (int) getResources().getDimension(R.dimen.paddingtop), (int) getResources().getDimension(R.dimen.paddingright), (int) getResources().getDimension(R.dimen.paddingbottom));
                    textView.setGravity(Gravity.CENTER);
                    textView.setWidth((int) getResources().getDimension(R.dimen.width));
                    textView.setHeight((int) getResources().getDimension(R.dimen.height));


                    // outra opcao pra setar valor em sp:
/*
                    DisplayMetrics dm = Resources.getSystem().getDisplayMetrics();
                    int pixelSize = (int)scaledPixelSize * dm.scaledDensity;
*/


                    textView2.setTextColor(Color.parseColor("#FFFFFF"));
                    textView2.setPadding(12, 8, 12, 0);
                    textView2.setGravity(Gravity.CENTER);
                    textView2.setHeight((int) getResources().getDimension(R.dimen.height));


                    textView3.setTextColor(Color.parseColor("#FFFFFF"));
                    textView3.setPadding(6, 8, 6, 0);
                    textView3.setGravity(Gravity.CENTER);
                    textView3.setHeight((int) getResources().getDimension(R.dimen.height));


                    celulares.get(i).setTextColor(Color.parseColor("#FFFFFF"));
                    celulares.get(i).setPadding(10, 8, 10, 0);
                    celulares.get(i).setGravity(Gravity.CENTER);
                    celulares.get(i).setTextIsSelectable(true);
                    celulares.get(i).setHeight((int) getResources().getDimension(R.dimen.height));


                    vendedores.get(i).setTextColor(Color.parseColor("#FFFFFF"));
                    vendedores.get(i).setPadding(6, 8, 6, 0);
                    vendedores.get(i).setGravity(Gravity.CENTER);
                    vendedores.get(i).setHeight((int) getResources().getDimension(R.dimen.height));


                    checkboxes.get(i).setPadding(0, 10, 0, 2);
                    checkboxes.get(i).setHeight((int) getResources().getDimension(R.dimen.height));


                    tableLayout.addView(rowList.get(i));
                    rowList.get(i).addView(textView);
                    rowList.get(i).addView(textView2);
                    rowList.get(i).addView(textView3);
                    rowList.get(i).addView(celulares.get(i));
                    rowList.get(i).addView(vendedores.get(i));



///////////////////////////////////////verificar se é venda finalizada

                    for(int k=0;k<child.getKey().length();k++){

                        if(child.getKey().charAt(k) == '_' ){
                            backupVendido = true;
                        }

                    }

                    if(!backupVendido){
                        rowList.get(i).addView(checkboxes.get(i));
                    }


                    //Apagar reserva
                    buttonDelete.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            final int[] j = {0};
                            final int[] naoEncontrado = {0};

                            FirebaseDatabase.getInstance().getReference("Reservas").addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {

                                    for (final DataSnapshot child : snapshot.getChildren()){

                                        if ( deleteInput.getText().toString().equals( child.getKey() )){
                                            FirebaseDatabase.getInstance().getReference("Reservas").child(child.getKey()).removeValue();
                                            Toast.makeText(ActivityReservas.this, "Apagado!",
                                                    Toast.LENGTH_LONG).show();
                                        }
                                        else{
                                            naoEncontrado[0]++;
                                        }

                                        if( deleteInput.getText().toString().equals( ((TextView) rowList.get(j[0]).getChildAt(3)).getText().toString() )) {
                                            tableLayout.removeView(rowList.get(j[0]));
                                            rowList.remove(j[0]);
                                            return;
                                        }

                                        j[0]++;


                                    }

                                    if( naoEncontrado[0] == (int) snapshot.getChildrenCount() ){
                                        Toast.makeText(ActivityReservas.this, "Numero não encontrado!",
                                                Toast.LENGTH_LONG).show();
                                    }




                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {

                                }
                            });


                        }
                    });





                    //listener para a pesquisa
                    searchInput.addTextChangedListener(new TextWatcher() {

                        @Override
                        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                        }

                        @Override
                        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {


                            for (int j = 0; j < rowList.size(); j++) {

                                String substring = "";

                                if(searchInput.getText().toString().length() == 0 && rowList.get(j).getParent() != null)
                                    tableLayout.removeView(rowList.get(j));

                                if(searchInput.getText().toString().length() == 0) {
                                    //tableLayout.getChildAt(j).setVisibility(View.VISIBLE);
                                    tableLayout.addView(rowList.get(j));
                                }


                                // pesquisando os campos no layout atraves da arvore de elementos
/*
                                ViewGroup row = (ViewGroup) tableLayout.getChildAt(j);
                                View nomeView = row.getChildAt(0);
                                String nome = ((TextView) nomeView).getText().toString();
*/

                                String nome = ((TextView) rowList.get(j).getChildAt(0)).getText().toString();

                                for(int k=0; k < searchInput.getText().toString().length(); k++) {

                                    if( searchInput.length() <= nome.length() ){

                                        substring = substring + nome.charAt(k);


                                        if(substring.toLowerCase().equals(searchInput.getText().toString().toLowerCase()) && rowList.get(j).getParent() == null){
                                            tableLayout.addView(rowList.get(j));

                                        }
                                        if(!substring.toLowerCase().equals(searchInput.getText().toString().toLowerCase())) {
                                            tableLayout.removeView(rowList.get(j));

                                        }

                                    }


                                }


                            }


                        }

                        @Override
                        public void afterTextChanged(Editable editable) {

                        }
                    });



                    //colorindo a linha de verde se tiver marcada
                    if (checkboxes.get(i).isChecked()) {
                        rowList.get(i).setBackgroundColor(Color.parseColor("#B341bd08"));
                    }




                    CreateCheckEvent(checkboxes, i, child);



                    //caixa de texto expandida com nome completo da pessoa
                    textView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            if (nome.getVisibility() == View.VISIBLE && textView.getText().toString().equals(nome.getText().toString())) {

                                nome.setVisibility(View.INVISIBLE);

                                return;

                            } else {

                                nome.setVisibility(View.VISIBLE);

                                //pegar coordenadas do textView nome
                                int[] coords = new int[2];
                                textView.getLocationInWindow(coords);

                                nome.setText(textView.getText());

                                int width = Resources.getSystem().getDisplayMetrics().widthPixels;
                                int height = Resources.getSystem().getDisplayMetrics().heightPixels;


                                constraintSet.connect(nome.getId(), ConstraintSet.TOP, R.id.constraint, ConstraintSet.TOP, coords[1] - 90 - (int) getResources().getDimension(R.dimen.height));  // coordenada do nome - altura do nome - altura que eu quero pro nome expandido
                                constraintSet.connect(nome.getId(), ConstraintSet.BOTTOM, R.id.constraint, ConstraintSet.BOTTOM, height - coords[1]); // tamanho da tela - coordenada do nome (y)
                                constraintSet.connect(nome.getId(), ConstraintSet.LEFT, R.id.constraint, ConstraintSet.LEFT, 10);
                                constraintSet.connect(nome.getId(), ConstraintSet.RIGHT, R.id.constraint, ConstraintSet.RIGHT, width - 1100);  // tamanho da tela - comprimento que quero pro nome expandido


                                constraintSet.applyTo(constraint);


                            }

                        }
                    });


                    i++;

                }


                OnChildChangedEvent(celulares, vendedores, checkboxes);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(ActivityReservas.this, error.toString(),
                        Toast.LENGTH_LONG).show();
            }


        });


    }




    void CreateCheckEvent(final ArrayList<CheckBox> checkboxes, final int i, final DataSnapshot child) {


        checkboxes.get(i).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton group, boolean checkedId) {


                FirebaseDatabase.getInstance().getReference("Reservas").child(child.getKey()).child("Vendido").setValue(checkboxes.get(i).isChecked());


                if (checkboxes.get(i).isChecked()) {
                    FirebaseDatabase.getInstance().getReference("Reservas").child(child.getKey()).child("Vendedor").setValue(FirebaseAuth.getInstance().getCurrentUser().getDisplayName());
                } else {
                    FirebaseDatabase.getInstance().getReference("Reservas").child(child.getKey()).child("Vendedor").setValue("-");
                }


            }
        });

    }




    void OnChildChangedEvent(final ArrayList<TextView> celulares, final ArrayList<TextView> vendedores, final ArrayList<CheckBox> checkboxes) {


        FirebaseDatabase.getInstance().getReference("Reservas")
                .addChildEventListener(new ChildEventListener() {

                    @Override
                    public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                    }

                    @Override
                    public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {


                        for (int i = 0; i < celulares.size(); i++) {

                            if (celulares.get(i).getText().toString().equals(snapshot.getKey())) {


                                ////alteração da checkbox, sendo outra pessoa que alterou ou não
                                checkboxes.get(i).setChecked(Boolean.parseBoolean(snapshot.child("Vendido").getValue().toString()));

                                ////alteração do vendedor, sendo outra pessoa ou não
                                vendedores.get(i).setText(snapshot.child("Vendedor").getValue().toString());

                                if (Boolean.parseBoolean(snapshot.child("Vendido").getValue().toString())) {

                                    //// alteração da cor da tabela
                                    TableRow tableRow = (TableRow) celulares.get(i).getParent();
                                    tableRow.setBackgroundColor(Color.parseColor("#B341bd08"));

                                } else {
                                    //// alteração da cor da tabela
                                    TableRow tableRow = (TableRow) celulares.get(i).getParent();
                                    tableRow.setBackgroundColor(Color.parseColor("#00FFFFFF"));


                                }
                            }


                        }


                    }

                    @Override
                    public void onChildRemoved(@NonNull DataSnapshot snapshot) {

                    }

                    @Override
                    public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                    }

                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(ActivityReservas.this, error.toString(),
                                Toast.LENGTH_LONG).show();
                    }


                });


    }




}