package com.htcdiurno.practicaud2pmdm;

import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.view.View;
import android.widget.TextView;


public class Resultado extends AppCompatActivity {

    private SharedPreferences pref;
    private TextView[] arTVid, arTVca, arTVcb, arTVra, arTVrb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        compruebaDN();
        setContentView(R.layout.activity_resultado);

        //Creo arrays con los recursos de la interfaz.
        enlazaTV();

        String deporte=null;

        //Según el deporte seleccionado...
        switch(pref.getString("prefApuesta","")){

            case "Futbol":
                deporte="futbol";
                break;
            case "Tenis":
                deporte="tenis";
                break;
            case "Baloncesto":
                deporte="baloncesto";
                break;
            case "Balonmano":
                deporte="balonmano";

        }

        //si se ha seleccionado algún deporte...
        if(deporte!=null)
            //muestra los resultados de ese deporte.
            muestraResultados(deporte);

    }

    /**
     * Método que comprueba si el modo noche está activado.
     */
    private void compruebaDN(){

        pref = PreferenceManager.getDefaultSharedPreferences(this);

        if(pref.getBoolean("modoNoche", false))
            AppCompatDelegate.setDefaultNightMode (AppCompatDelegate.MODE_NIGHT_YES);
        else
            AppCompatDelegate.setDefaultNightMode (AppCompatDelegate.MODE_NIGHT_NO);

    }

    /**
     * Método que abre la conexión con la base de datos y extrae la información
     * que posteriormente se mostrará en la activity.
     *
     * @param deporte String con el tipo de deporte seleccionado.
     */
    private void muestraResultados(String deporte){

        //Instancia la clase gestora de la base de datos.
        BDPartidos bdPartidos=new BDPartidos(this);

        //Abre la base de datos en modo lectura.
        SQLiteDatabase bd = bdPartidos.getReadableDatabase();

        //Si la base de datos está abierta...
        if(bd.isOpen())
            //envía a siguente método un Cursor con la información extraida de la consulta.
            recorreTabla(bd.rawQuery("SELECT * FROM "+deporte, null));

        //Cierra la conexión con la base de datos.
        bd.close();

    }

    /**
     * Método que recorre una tabla y escribe en la interfaz de la activity
     * la información extraida.
     *
     * @param cur Cursor con la información consultada a la base de datos.
     */
    private void recorreTabla(Cursor cur){

        int cont=0;

        //Si la consulta no está vacía y se mueve al primer resultado...
        if (cur.moveToFirst()) {

            //escribe en los TextView de la interfaz los valores de cada fila de la tabla.
            do {

                arTVid[cont].setText(String.valueOf(cur.getInt(0)));
                arTVca[cont].setText(cur.getString(1));
                arTVcb[cont].setText(cur.getString(2));
                arTVra[cont].setText(String.valueOf(cur.getInt(3)));
                arTVrb[cont].setText(String.valueOf(cur.getInt(4)));

                cont++;

            } while(cur.moveToNext());
        }

    }

    /**
     * Método que crea arrays con los recursos de la interfaz.
     */
    private void enlazaTV(){

        //Arrays con las cinco columnas de la interfaz.
        arTVid=new TextView[4];
        arTVca=new TextView[4];
        arTVcb=new TextView[4];
        arTVra=new TextView[4];
        arTVrb=new TextView[4];

        //Columna ID.
        arTVid[0]=findViewById(R.id.id1);
        arTVid[1]=findViewById(R.id.id2);
        arTVid[2]=findViewById(R.id.id3);
        arTVid[3]=findViewById(R.id.id4);

        //Columna Contendiente 1.
        arTVca[0]=findViewById(R.id.ca1);
        arTVca[1]=findViewById(R.id.ca2);
        arTVca[2]=findViewById(R.id.ca3);
        arTVca[3]=findViewById(R.id.ca4);

        //Columna Contendiente 2.
        arTVcb[0]=findViewById(R.id.cb1);
        arTVcb[1]=findViewById(R.id.cb2);
        arTVcb[2]=findViewById(R.id.cb3);
        arTVcb[3]=findViewById(R.id.cb4);

        //Columna Resultado 1.
        arTVra[0]=findViewById(R.id.ra1);
        arTVra[1]=findViewById(R.id.ra2);
        arTVra[2]=findViewById(R.id.ra3);
        arTVra[3]=findViewById(R.id.ra4);

        //Columna Resultado 2.
        arTVrb[0]=findViewById(R.id.rb1);
        arTVrb[1]=findViewById(R.id.rb2);
        arTVrb[2]=findViewById(R.id.rb3);
        arTVrb[3]=findViewById(R.id.rb4);

    }


    /**
     * Método que finaliza la view.
     *
     * @param view
     */
    public void volver(View view){

        finish();

    }

}
