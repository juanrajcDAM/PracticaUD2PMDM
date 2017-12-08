package com.htcdiurno.practicaud2pmdm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

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
     * Método que crea el menú principal en la activity principal.
     *
     * @param menu
     * @return Booleano
     */
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    /**
     * Método que, según el item del menú pulsado, abre la activity correspondiente.
     *
     * @param item
     * @return Booleano
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.acercaDe:
                abrirAcercaDe();
                return true;
            case R.id.ayuda:
                abrirAyuda();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    /**
     * Método que abre el registro del usuario.
     *
     * @param view
     */
    public void abrirRegistro(View view){
        startActivity(new Intent(this, Registro.class));
    }

    /**
     * Método que abre las apuestas.
     *
     * @param view
     */
    public void abrirApuestas(View view){
        startActivity(new Intent(this, Apuestas.class));
    }

    /**
     * Método que abre los ajustes de las apuestas.
     *
     * @param view
     */
    public void abrirAjustes(View view){
        startActivity(new Intent(this, Ajustes.class));
    }

    /**
     * Método que, por ahora, muestra un mensaje de aviso respecto a la la funionalidad de Sorteo.
     *
     * @param view
     */
    public void abrirSorteo(View view){

        //startActivity(new Intent(this, Sorteo.class));

        Toast.makeText(getApplicationContext(), "Funcionalidad aún no implementada.", Toast.LENGTH_SHORT).show();

    }

    /**
     * Método que abre Acerca de...
     */
    public void abrirAcercaDe(){
        startActivity(new Intent(this, AcercaDe.class));
    }

    /**
     * Método que abre Ayuda.
     */
    public void abrirAyuda(){
        startActivity(new Intent(this, Ayuda.class));
    }

}
