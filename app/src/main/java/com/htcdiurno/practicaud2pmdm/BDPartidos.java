package com.htcdiurno.practicaud2pmdm;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by juanrajc on 06/02/2018.
 */

public class BDPartidos extends SQLiteOpenHelper {

    //Nombre y versión de la base de datos.
    public static final String DATABASE_NAME = "Partidos.db";
    public static final int DATABASE_VERSION = 1;

    /**
     * Constructor de la clase.
     *
     * @param context
     */
    public BDPartidos(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        //Creo las tablas.
        sqLiteDatabase.execSQL("create table futbol(id integer primary key, eq1 text, eq2 text, res1 integer, res2 integer)");
        sqLiteDatabase.execSQL("create table tenis(id integer primary key, eq1 text, eq2 text, res1 integer, res2 integer)");
        sqLiteDatabase.execSQL("create table baloncesto(id integer primary key, eq1 text, eq2 text, res1 integer, res2 integer)");
        sqLiteDatabase.execSQL("create table balonmano(id integer primary key, eq1 text, eq2 text, res1 integer, res2 integer)");

        //Introduzco los partidos y su resultado final.
        partidos(sqLiteDatabase);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        //Borro las tablas.
        sqLiteDatabase.execSQL("drop table if exists futbol");
        sqLiteDatabase.execSQL("drop table if exists tenis");
        sqLiteDatabase.execSQL("drop table if exists baloncesto");
        sqLiteDatabase.execSQL("drop table if exists balonmano");

        //Creo de nuevo las tablas.
        sqLiteDatabase.execSQL("create table futbol(id integer primary key, eq1 text, eq2 text, res1 integer, res2 integer)");
        sqLiteDatabase.execSQL("create table tenis(id integer primary key, eq1 text, eq2 text, res1 integer, res2 integer)");
        sqLiteDatabase.execSQL("create table baloncesto(id integer primary key, eq1 text, eq2 text, res1 integer, res2 integer)");
        sqLiteDatabase.execSQL("create table balonmano(id integer primary key, eq1 text, eq2 text, res1 integer, res2 integer)");

        //Introduzco los partidos y su resultado final.
        partidos(sqLiteDatabase);

    }

    /**
     * Método que rellena las tablas con los partidos y sus resultados finales.
     *
     * @param sqLiteDatabase Instancia de la clase que gestiona la base de datos.
     */
    private void partidos(SQLiteDatabase sqLiteDatabase){

        //Fútbol.
        sqLiteDatabase.execSQL("INSERT INTO futbol (id, eq1, eq2, res1, res2) VALUES (1, 'Real Madrid', 'Barcelona', 5, 0), " +
                "(2, 'Getafe', 'Espanyol', 0, 0), " +
                "(3, 'Valencia', 'Deportivo', 2, 1), " +
                "(4, 'Sevilla', 'Málaga', 3, 4)") ;

        //Tenis
        sqLiteDatabase.execSQL("INSERT INTO tenis (id, eq1, eq2, res1, res2) VALUES (1, 'Rafael Nadal', 'Roger Federer', 3, 2), " +
                "(2, 'Grigor Dimitrov', 'Kevin Anderson', 3, 0), " +
                "(3, 'Lucas Pouille', 'J.M. Del Potro', 1, 3), " +
                "(4, 'Andy Murray', 'David Goffin', 2, 3)") ;

        //Baloncesto
        sqLiteDatabase.execSQL("INSERT INTO baloncesto (id, eq1, eq2, res1, res2) VALUES (1, 'Barcelona', 'Estudiantes', 66, 50), " +
                "(2, 'Baskonia', 'Joventut de Badalona', 81, 77), " +
                "(3, 'Obradoiro', 'Fuenlabrada', 64, 71), " +
                "(4, 'Unicaja', 'Valencia', 55, 40)") ;

        //Balonmano
        sqLiteDatabase.execSQL("INSERT INTO balonmano (id, eq1, eq2, res1, res2) VALUES (1, 'NaturHouse', 'Granollers', 9, 12), " +
                "(2, 'Alcobendas', 'Anaitasuna', 21, 13), " +
                "(3, 'Ademar León', 'Puerto Sagunto', 17, 17), " +
                "(4, 'Bidasoa', 'Barcelona', 15, 22)") ;

    }
}
