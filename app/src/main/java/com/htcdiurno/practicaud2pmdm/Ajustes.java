package com.htcdiurno.practicaud2pmdm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TabHost;

public class Ajustes extends AppCompatActivity {

    private String[] arraySpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajustes);


        TabHost tabs= (TabHost) findViewById(R.id.tabHost);
        tabs.setup();

        TabHost.TabSpec tab1=tabs.newTabSpec("tab1");
        TabHost.TabSpec tab2=tabs.newTabSpec("tab2");

        tab1.setIndicator("DINERO").setContent(R.id.tab1);
        tab2.setIndicator("COMBINACIÓN").setContent(R.id.tab2);

        tabs.addTab(tab1);
        tabs.addTab(tab2);


        this.arraySpinner = new String[] {
                "1 €", "2 €", "5 €", "10 €"
        };
        Spinner s = findViewById(R.id.spinnerDinero);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, arraySpinner);
        s.setAdapter(adapter);

    }
}
