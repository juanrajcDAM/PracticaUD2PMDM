package com.htcdiurno.practicaud2pmdm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

public class Ajustes extends AppCompatActivity {

    private String[] arraySpinner= new String[] {"1 €", "2 €", "5 €", "10 €"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajustes);


        TabHost tabs= (TabHost) findViewById(R.id.tabHost);
        tabs.setup();

        TabHost.TabSpec tab1=tabs.newTabSpec("tab1");
        TabHost.TabSpec tab2=tabs.newTabSpec("tab2");

        tab1.setIndicator(getString(R.string.dinero)).setContent(R.id.tab1);
        tab2.setIndicator(getString(R.string.combinacion)).setContent(R.id.tab2);

        tabs.addTab(tab1);
        tabs.addTab(tab2);


        Spinner s = findViewById(R.id.spinnerDinero);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arraySpinner);
        s.setAdapter(adapter);


        TextView etiquetaPartido= (TextView) findViewById(R.id.etiquetaPartido);

        //Al crearse la activity, recoge el tipo de apuesta enviada por la activity principal...
        Bundle bundle = getIntent().getExtras();

        //Y, mediante un método, muestra el partido correspondiente.
        etiquetaPartido.setText(combinacion(bundle.getString("tipoApuesta")));

    }

    /**
     * Método que valida y guarda los ajustes.
     *
     * @param view
     */
    public void guardar(View view){

        Spinner spinner=findViewById(R.id.spinnerDinero);
        EditText res1=findViewById(R.id.res1);
        EditText res2=findViewById(R.id.res2);
        TextView etiquetaPartido= (TextView) findViewById(R.id.etiquetaPartido);

        //Si en el spinner no hay ningún valor seleccionado...
        if(spinner.getItemAtPosition(spinner.getSelectedItemPosition()).toString()==null)
            //Muestra un mensaje.
            Toast.makeText(getApplicationContext(), getString(R.string.eligeApu), Toast.LENGTH_LONG).show();
        //Si tiene un valor seleccionado y la combinación tiene algún campo vacío...
        else if(res1.getText().length()<1 && res2.getText().length()<1)
            //Muestra un mensaje.
            Toast.makeText(getApplicationContext(), getString(R.string.eligeComb), Toast.LENGTH_LONG).show();
        //Si el spinner tiene un valor seleccionado, la combinación tiene un valor introducido pero ese valor no es válido...
        else if(!esValido(res1.getText().toString()) || !esValido(res2.getText().toString()))
            //Muestra un mensaje.
            Toast.makeText(getApplicationContext(), getString(R.string.errorAjustes), Toast.LENGTH_LONG).show();
        //Si no se da ninguna de las situaciones anteriores...
        else{

            //Muestra un mensaje...
            Toast.makeText(getApplicationContext(), getString(R.string.ajustesGuardados), Toast.LENGTH_LONG).show();

            //Envía un intent al main con los datos seleccionados e introducidos...
            Intent intent= new Intent (this, MainActivity. class);
            Bundle bundle = new Bundle();

            bundle.putString("apuesta",spinner.getItemAtPosition(spinner.getSelectedItemPosition()).toString());
            bundle.putString("contendientes",etiquetaPartido.getText().toString());
            bundle.putString("resultadoUno",res1.getText().toString());
            bundle.putString("resultadoDos",res2.getText().toString());
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
     * Método que, según el tipo de apuesta recibida, retorna el partido correspondiente.
     *
     * @param tipoApuesta Tipo de apuesta seleccionada.
     *
     * @return Cadena con el partido correspondiente.
     */
    private String combinacion (String tipoApuesta){

        switch(tipoApuesta){

            case "Futbol":
                return "Real Madrid - Barcelona";

            case "Tenis":
                return "Rafael Nadal - Roger Federer";

            case "Baloncesto":
                return "Barcelona - Estudiantes";

            case "Balonmano":
                return "NaturHouse - Granollers";

            default:
                return null;

        }

    }

    /**
     * Método que valida la cadena introducida en la combinación.
     *
     * @param cadena Cadena de caracteres introducida en la combinación.
     *
     * @return Booleano indicando si es una cadena válida (true) o no válida (false).
     */
    private boolean esValido (String cadena){

        if(cadena.length()>3)
            return false;

        for(int i=0;i<cadena.length();i++)
            if(!Character.isDigit(cadena.charAt(i)))
                return false;

        return true;

    }

}
