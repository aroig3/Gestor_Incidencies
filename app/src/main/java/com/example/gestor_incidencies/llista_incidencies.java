package com.example.gestor_incidencies;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class llista_incidencies extends AppCompatActivity {

    public Connexio_bbdd con = new Connexio_bbdd(this);
    CustomerAdapter customerAdapter;
    RecyclerView llista_incidencies;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.llista_incidencies_lyt);

        Cursor c  = con.mostrarIncidencies();

        //Referenciant el list view on tenim la llista
       llista_incidencies = findViewById(R.id.list_vieww);

       //Creem una array per posar els elements que volguem mostrar per cada fila de la llista
        ArrayList<String> n_elem, dt, ub;
        n_elem = new ArrayList<>();
        dt = new ArrayList<>();
        ub = new ArrayList<>();

        //Recorrem el cursor i afegim a les Arrays creades anteriorments

        if (c.getCount() == 0) { // el cursor esta buit
        } else {
            // Recorrem el cursor
            while (c.moveToNext()) {        //c -> és un objecte de la class Connexio_bbdd
                n_elem.add(c.getString(2));
                dt.add(c.getString(6));
                ub.add(c.getString(4));
            }
        }

        //Creem l'adaptador
        customerAdapter = new CustomerAdapter(llista_incidencies.this, n_elem,dt,ub);

        //El setejem
        llista_incidencies.setAdapter(customerAdapter);
        llista_incidencies.setLayoutManager(new LinearLayoutManager(llista_incidencies.this));


}




    }


