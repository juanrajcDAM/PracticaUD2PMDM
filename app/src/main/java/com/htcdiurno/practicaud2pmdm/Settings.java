package com.htcdiurno.practicaud2pmdm;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatDelegate;

/**
 * Created by juanrajc on 28/01/2018.
 */

public class Settings extends PreferenceActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        compruebaDN();
        getFragmentManager().beginTransaction()
                .replace(android.R.id.content, new SettingsFragment())
                .commit();
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

}