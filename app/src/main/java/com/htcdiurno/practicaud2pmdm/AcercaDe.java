package com.htcdiurno.practicaud2pmdm;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.view.View;

public class AcercaDe extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        compruebaDN();
        setContentView(R.layout.activity_acerca_de);
    }

    /**
     * Método que comprueba si el modo noche está activado.
     */
    private void compruebaDN(){

        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
        if(pref.getBoolean("modoNoche", false))
            AppCompatDelegate.setDefaultNightMode (AppCompatDelegate.MODE_NIGHT_YES);
        else
            AppCompatDelegate.setDefaultNightMode (AppCompatDelegate.MODE_NIGHT_NO);

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
