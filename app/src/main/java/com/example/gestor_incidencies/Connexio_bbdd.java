package com.example.gestor_incidencies;

import static android.content.Context.NOTIFICATION_SERVICE;
import static androidx.core.content.ContextCompat.getSystemService;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Color;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class Connexio_bbdd extends SQLiteOpenHelper {

    //Definim els paràmetres de connexió
    private static final int DATABASE_VERSION =1;
    private static final String DATABASE_NOM = "incidencies.db";
    private static final String TABLE_INCIDENCIES="TABLE_INCIDENCIES";
    private static final String is_resolta = "resolta";
    private static final String TAG = null;


    public Connexio_bbdd(@Nullable Context context) {
        super(context, DATABASE_NOM, null, DATABASE_VERSION);
    }

    //CREACIO DE TAULES

    @Override
    public void onCreate(SQLiteDatabase DATABASE_NOM) {
        DATABASE_NOM.execSQL("CREATE TABLE TABLE_INCIDENCIES(id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "nom_usuari TEXT NOT NULL, " +
                "nom_element TEXT NOT NULL, " +
                "tipus_element TEXT NOT NULL, " +
                "ubicacio TEXT NOT NULL, " +
                "descripcio TEXT NOT NULL, " +
                "data TEXT NOT NULL, " +
                "resolta TINYINT NOT NULL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    //GESTIÓ DE ERRORS:  Si fa l insert correctament no salta la excepcio lo qual sabem que fa bé l insert
    //fem insert
    public boolean inserts(ContentValues cv) {
        SQLiteDatabase db = getWritableDatabase();
        try{
            db.insert("TABLE_INCIDENCIES", null, cv) ;
        } catch (SQLException e){
            Log.e(TAG, "Error al inserir" + cv , e);
        }
         return false;
    }

    //mostrem incidències
    public Cursor mostrarIncidencies(){

        String query = "select * from " + TABLE_INCIDENCIES; //fem la consulta
        SQLiteDatabase db = this.getReadableDatabase(); //llegim la BBDD seleccionada

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null); //Si no es NULL mostrem la info

        }
        return cursor;
    }

    //Mostrem incidencies resoltes
    public Cursor mostrarIncidenciesResoltes(){
        String query = "select * from " + TABLE_INCIDENCIES; //"select * from " + TABLE_INCIDENCIES + "where" + is_resolta = "1";
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }



}



