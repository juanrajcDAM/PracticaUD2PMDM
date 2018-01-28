package com.htcdiurno.practicaud2pmdm;

import android.os.Bundle;
import android.preference.PreferenceActivity;

/**
 * Created by juanrajc on 28/01/2018.
 */

public class Settings extends PreferenceActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getFragmentManager().beginTransaction()
                .replace(android.R.id.content, new SettingsFragment())
                .commit();
    }

}