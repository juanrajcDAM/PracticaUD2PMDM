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

    private boolean registroValidado;
    private String tipoApuesta, contendientes, apuesta, resultadoUno, resultadoDos;

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

        Intent intent= new Intent (this, Registro. class);
        startActivityForResult(intent, 1);

    }

    /**
     * Método que abre las apuestas.
     *
     * @param view
     */
    public void abrirApuestas(View view){

        //Si no está registrado...
        if(!registroValidado)
            //Muestra un mensaje.
            Toast.makeText(getApplicationContext(), getString(R.string.errorApuestas), Toast.LENGTH_SHORT).show();
        //Si lo está...
        else {

            //inicia la activity.
            Intent intent= new Intent (this, Apuestas. class);
            startActivityForResult(intent, 2);

        }

    }

    /**
     * Método que abre los ajustes de las apuestas.
     *
     * @param view
     */
    public void abrirAjustes(View view){

        //Si no se ha seleccionado previamente un tipo de apuesta...
        if(tipoApuesta==null)
            //Muestra un mensaje.
            Toast.makeText(getApplicationContext(), getString(R.string.avisoAjustes), Toast.LENGTH_SHORT).show();
        //Si ya se ha seleccionado...
        else {

            //inicia la activity enviándole el tipo de apuesta.
            Intent intent= new Intent (this, Ajustes. class);
            Bundle bundle = new Bundle();
            bundle.putString("tipoApuesta",tipoApuesta);
            intent.putExtras(bundle);
            startActivityForResult(intent, 3);

        }

    }

    /**
     * Método que, por ahora, muestra un mensaje de aviso respecto a la la funionalidad de Sorteo.
     *
     * @param view
     */
    public void abrirSorteo(View view){

        //startActivity(new Intent(this, Sorteo.class));

        Toast.makeText(getApplicationContext(), getString(R.string.noFuncion), Toast.LENGTH_SHORT).show();

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

    /**
     * Método sobreescrito para recibir datos de las activities.
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //Si la activity "Registro" devuelve un resultado...
        if (requestCode == 1 && resultCode == RESULT_OK) {

            //Lo guarda en la variable de clase.
            registroValidado=data.getExtras().getBoolean("registroValidado");

        //Si la activity "Apuestas" devuelve un resultado...
        }else if(requestCode == 2 && resultCode == RESULT_OK) {

            //Lo guarda en la variable de clase...
            tipoApuesta=data.getExtras().getString("tipoApuesta");

            //Y muestra el tipo de apuesta seleccionada.
            Toast.makeText(getApplicationContext(), getString(R.string.tipoApuestaSelec)+tipoApuesta, Toast.LENGTH_LONG).show();

        //Si la activity "Ajustes" devuelve un resultado...
        }else if(requestCode == 3 && resultCode == RESULT_OK) {

            //Lo guarda en las variables de clase...
            apuesta=data.getExtras().getString("apuesta");
            contendientes=data.getExtras().getString("contendientes");
            resultadoUno=data.getExtras().getString("resultadoUno");
            resultadoDos=data.getExtras().getString("resultadoDos");

            //Y muestra la apuesta.
            Toast.makeText(getApplicationContext(), getString(R.string.toastComb)+contendientes+" ("+resultadoUno+") - ("+resultadoDos+getString(R.string.toastApu)+apuesta, Toast.LENGTH_LONG).show();

        }


    }
}
