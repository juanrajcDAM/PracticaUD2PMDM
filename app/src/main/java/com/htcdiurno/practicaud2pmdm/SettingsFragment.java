package com.htcdiurno.practicaud2pmdm;

import android.os.Bundle;
import android.preference.PreferenceFragment;

/**
 * Created by juanrajc on 28/01/2018.
 */

public class SettingsFragment extends PreferenceFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.settings);
    }

}
