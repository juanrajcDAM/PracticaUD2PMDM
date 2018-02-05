package com.htcdiurno.practicaud2pmdm;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

public class Apuestas extends AppCompatActivity {

    private CheckBox checkFutbol, checkTenis, checkBaloncesto, checkBalonmano;
    private SharedPreferences pref;
    private SharedPreferences.Editor editorPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        compruebaDN();
        setContentView(R.layout.activity_apuestas);

        //Enlace a los CheckBox de la interfaz.
        checkFutbol = findViewById(R.id.checkFutbol);
        checkTenis = findViewById(R.id.checkTenis);
        checkBaloncesto = findViewById(R.id.checkBaloncesto);
        checkBalonmano = findViewById(R.id.checkBalonmano);

        /*
        Al iniciar la activity, seleccionamos automáticamente el tipo de apuesta
        guardada en preferencias (si la hay).
        */
        switch(pref.getString("prefApuesta","")){

            case "Futbol":
                checkFutbol.setChecked(true);
                break;
            case "Tenis":
                checkTenis.setChecked(true);
                break;
            case "Baloncesto":
                checkBaloncesto.setChecked(true);
                break;
            case "Balonmano":
                checkBalonmano.setChecked(true);

        }

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
     * Método que valida y acepta la selección de una apuesta.
     *
     * @param view
     */
    public void aceptar(View view){

        //Guardamos el resultado del controlador de los CheckBox en una variable.
        String seleccionado=controlCheckBox(checkFutbol, checkTenis, checkBaloncesto, checkBalonmano);

        //Según el resultado que devuelva...
        switch (seleccionado){

            //Si se han selecionado varios...
            case "varios":

                //Muestra un mensaje.
                Toast.makeText(getApplicationContext(), getString(R.string.soloUno), Toast.LENGTH_LONG).show();

                break;

            //Si no se ha seleccionado ninguno...
            case "ninguno":

                //Muestra un mensaje.
                Toast.makeText(getApplicationContext(), getString(R.string.apuNoSelec), Toast.LENGTH_LONG).show();

                break;

            //Si se ha seleccionado una de las opciones disponibles...
            case "Futbol":case "Tenis":case "Baloncesto":case "Balonmano":

                //Muestra un mensaje...
                Toast.makeText(getApplicationContext(), getString(R.string.apuGuardada), Toast.LENGTH_LONG).show();

                //Guarda el deporte seleccionado en preferencias...
                pref=getApplicationContext().getSharedPreferences("com.htcdiurno.practicaud2pmdm_preferences", MODE_PRIVATE);
                editorPref=pref.edit();

                editorPref.putString("prefApuesta", seleccionado);

                editorPref.commit();

                //Envía un intent al main con la opción seleccionada...
                Intent intent= new Intent (this, MainActivity. class);
                Bundle bundle = new Bundle();

                bundle.putString("tipoApuesta",seleccionado);
                intent.putExtras(bundle);
                setResult(RESULT_OK, intent);

                //Y cierra la activity.
                finish();

        }

    }

    /**
     * Método que finaliza la view.
     *
     * @param view
     */
    public void volver(View view){

        finish();

    }

    /**
     * Método que controla la correcta selección de los CheckBox.
     *
     * @param cbFutbol CheckBox de futbol.
     * @param cbTenis CheckBox de tenis.
     * @param cbBaloncesto CheckBox de baloncesto.
     * @param cbBalonmano CheckBox de balonmano.
     *
     * @return String con el tipo de apuesta seleccionada.
     */
    public String controlCheckBox(CheckBox cbFutbol, CheckBox cbTenis, CheckBox cbBaloncesto, CheckBox cbBalonmano){

        int numSeleccionados=0;
        String cbSeleccionado="";

        //Comprueba cada CheckBox y va sumando los seleccionados.
        if(cbFutbol.isChecked()) {

            numSeleccionados += 1;
            cbSeleccionado="Futbol";

        }if(cbTenis.isChecked()) {

            numSeleccionados += 1;
            cbSeleccionado="Tenis";

        }if(cbBaloncesto.isChecked()) {

            numSeleccionados += 1;
            cbSeleccionado="Baloncesto";

        }if(cbBalonmano.isChecked()) {

            numSeleccionados += 1;
            cbSeleccionado="Balonmano";

        }

        //Si no se ha seleccionado ninguno...
        if(numSeleccionados==0)
            //Devuelve "ninguno".
            return "ninguno";
        //Si se ha seleccionado uno...
        else if(numSeleccionados==1){

            //Devuelve el seleccionado.
            return cbSeleccionado;

        //Si se ha seleccionado más de uno...
        }else
            //Devuelve "varios".
            return "varios";

    }

}
