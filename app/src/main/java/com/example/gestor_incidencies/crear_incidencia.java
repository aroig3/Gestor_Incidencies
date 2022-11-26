package com.example.gestor_incidencies;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.jetbrains.annotations.NotNull;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


//**En aquesta classe tenim tot lo necessari per crear una incidencia.
public class crear_incidencia extends AppCompatActivity {

    //OBJ de la classe Connexio_bbdd per poder utilitzar el metode inserts() per afegir
    //les dades del Content Values a la BBDD
    Connexio_bbdd con_bbdd = new Connexio_bbdd(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.crear_incidencia_lyt);


        //Referenciar elements del xml : crear_incidencia_lyt.xml

        Button btnregistrar = (Button) findViewById(R.id.botoRegistrar);
        EditText etUsuari = (EditText) findViewById(R.id.etNomUsuari);
        EditText etElementAf = (EditText) findViewById(R.id.etAfectat);
        EditText etTipusElem = (EditText) findViewById(R.id.etTipusElement);
        EditText etUbi = (EditText) findViewById(R.id.etUbicacio);
        EditText etDescrp = (EditText) findViewById(R.id.etDescripcio);


        //Notificacio creem canal
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel channel = new NotificationChannel("Mi notificacion", "Incidencia", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }

        //Listener del boto de registrar
        btnregistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //creem un objecte de la clase ContentValues
                ContentValues cv = new ContentValues();
                //carraguem el contentValues amb la informació dels camps EditText del lyt
                cv.put("nom_usuari", etUsuari.getText().toString());
                cv.put("nom_element", etElementAf.getText().toString());
                cv.put("tipus_element", etTipusElem.getText().toString());
                cv.put("ubicacio", etUbi.getText().toString());
                cv.put("descripcio", etDescrp.getText().toString());

                //CONTROL DE ERRORS: DATA
                //Setejem la data del dia d'avui per defecte
                long sysdate = System.currentTimeMillis();
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                Date fechasys = new Date(sysdate);
                String fechaStr = sdf.format(fechasys);
                    cv.put("data",fechaStr);
                    cv.put("resolta", 0);

                    //realitzem l'insert amb el Content Values carregat de informació
                    con_bbdd.inserts(cv);

                    //Creem un Intent cap al menú principal un cop la incidència ha estat registrada
                    Intent intent = new Intent( crear_incidencia.this, menu_principal.class); //crear intent
                    startActivity(intent); //executar intent

                //Creem la notificacio
                //----------------------------------------------------------------------------------------------------------------------
                //Editem la informació que ens apareixarà quan salti la notificacio
                NotificationCompat.Builder builder = new NotificationCompat.Builder(crear_incidencia.this, "Mi notificacion");
                builder.setContentTitle("Incidència creada per l'usuari " + etUsuari.getText().toString().trim());
                builder.setContentText(etDescrp.getText().toString().trim());
                builder.setSmallIcon(R.drawable.ic_launcher_background);
                builder.setAutoCancel(true);
                //Executem el build de la notificació creada anteriorment
                NotificationManagerCompat managerCompat = NotificationManagerCompat.from(crear_incidencia.this);
                managerCompat.notify(1,builder.build());

                //-----------------------------------------------------------------------------------------------------------------------

            }
        });


        }

}