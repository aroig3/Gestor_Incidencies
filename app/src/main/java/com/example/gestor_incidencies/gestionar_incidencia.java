package com.example.gestor_incidencies;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;

import java.util.ArrayList;

public class gestionar_incidencia extends AppCompatActivity {

    public Connexio_bbdd con = new Connexio_bbdd(this);
    CustomerAdapter customerAdapter;
    RecyclerView llista_incidencies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gestionar_incidencia_lyt);

        Cursor c  = con.mostrarIncidenciesResoltes();

        llista_incidencies = findViewById(R.id.list_vieww);

        ArrayList<String> n_elem, dt, ub;
        n_elem = new ArrayList<>();
        dt = new ArrayList<>();
        ub = new ArrayList<>();

        if (c.getCount() == 0) { // el cursor est� vac�o
        } else {
            // Recorremos el cursor
            while (c.moveToNext()) {
                n_elem.add(c.getString(2));
                dt.add(c.getString(6));
                ub.add(c.getString(4));
            }
        }

        customerAdapter = new CustomerAdapter(gestionar_incidencia.this, n_elem,dt,ub);
        llista_incidencies.setAdapter(customerAdapter);
        llista_incidencies.setLayoutManager(new LinearLayoutManager(gestionar_incidencia.this));


    }




}

