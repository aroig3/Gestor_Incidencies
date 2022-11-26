package com.example.gestor_incidencies;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class menu_principal extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_principal_lyt);

        //Referenciació de elements xml al codi:

        Button b_crearInc = (Button) findViewById(R.id.btn_crearInc);
        Button b_llisInc = (Button) findViewById(R.id.btn_llisInc);
        Button b_gestInc = (Button) findViewById(R.id.bnt_gestInc);

        //canvi de activity -> CREAR INCIDÈNCIA

        b_crearInc.setOnClickListener(new View.OnClickListener() { //LISTENER AL ACCIONAR EL BOTÓ
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( menu_principal.this, crear_incidencia.class); //crear intent
                startActivity(intent); //executar intent
            }
        });

        //canvi de activity -> LLISTAR INCIDÈNCIES

        b_llisInc.setOnClickListener(new View.OnClickListener() { //LISTENER AL ACCIONAR EL BOTÓ
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( menu_principal.this, llista_incidencies.class); //crear intent
                startActivity(intent); //executar intent
            }
        });

        //canvi de activity -> GESTIONAR INCIDÈNCIES

        b_gestInc.setOnClickListener(new View.OnClickListener() { //LISTENER AL ACCIONAR EL BOTÓ
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( menu_principal.this, gestionar_incidencia.class); //crear intent
                startActivity(intent); //executar intent
            }
        });
    }
}