package com.htcdiurno.practicaud2pmdm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class AcercaDe extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acerca_de);
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
