package com.htcdiurno.practicaud2pmdm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

/**
 * @author Juan Ramón Jover Cruz
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * Método que abre el registro del usuario.
     *
     * @param view
     */
    public void abrirRegistro(View view){
        startActivity(new Intent(this, Registro.class));
    }

    public void abrirApuestas(View view){
        startActivity(new Intent(this, Apuestas.class));
    }

    /*public void abrirAjustes(View view){
        startActivity(new Intent(this, Ajustes.class));
    }*/

    /*public void abrirSorteo(View view){
        startActivity(new Intent(this, Sorteo.class));
    }*/

}
