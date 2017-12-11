package com.htcdiurno.practicaud2pmdm;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author Juan Ramón Jover Cruz
 */
public class Registro extends AppCompatActivity {

    private EditText nombreRegistro, emailRegistro, fNacRegistro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

    }

    /**
     *Método que muestra un DatePicker, en el que seleccionaremos una fecha y la escribirá
     * en el EditText de la fecha de nacimiento.
     *
     * @param view
     */
    public void mostrarCalendario(View view) {

        //Enlace al recurso de la interfaz en el que se escribe la fecha de nacimento.
        fNacRegistro =findViewById(R.id.fNacRegistro);

        DatePickerFragment newFragment = DatePickerFragment.newInstance(new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                // +1 because january is zero
                final String selectedDate = day + " / " + (month + 1) + " / " + year;
                fNacRegistro.setText(selectedDate);
            }
        });

        newFragment.show(getFragmentManager(), "datePicker");

    }

    /**
     *Método que valida los datos introducidos.
     *
     * @param view
     */
    public void validar(View view){

        //Enlace a los recursos de la interfaz.
        nombreRegistro = findViewById(R.id.nombreRegistro);
        emailRegistro = findViewById(R.id.emailRegistro);
        fNacRegistro = findViewById(R.id.fNacRegistro);

        //Si algún campo de texto está vacío, muestra un mensaje...
        if(nombreRegistro.getText().length()<1 || emailRegistro.getText().length()<1 || fNacRegistro.getText().length()<1) {
            Toast.makeText(getApplicationContext(), getString(R.string.faltaCampo), Toast.LENGTH_SHORT).show();
        //Si no...
        }else{
            //Comprueba si es mayor de edad. Si no lo es, muestra un mensaje...
            if(!calculaEdad(fNacRegistro.getText().toString()))
                Toast.makeText(getApplicationContext(), getString(R.string.menorEdad), Toast.LENGTH_SHORT).show();
            //Si es mayor de edad...
            else {
                //Muestra un mensaje de duración larga...
                Toast.makeText(getApplicationContext(), getString(R.string.usuReg), Toast.LENGTH_LONG).show();

                //Envía un intent al main para confirmarle el registro...
                Intent intent= new Intent (this, MainActivity. class);
                Bundle bundle = new Bundle();

                bundle.putBoolean("registroValidado",true);
                intent.putExtras(bundle);
                setResult(RESULT_OK, intent);

                //Y cierra la activity.
                finish();
            }
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
     * Método que comprueba si la fecha de nacimiento corresponde a una persona mayor o menor de edad.
     *
     * @param fecha Parámetro con la fecha de nacimento en formato cadena de caracteres.
     * @return True o false, dependiendo de si es o no mayor de edad.
     */
    public boolean calculaEdad(String fecha){

        Date fechaNac=null;

        try {
            //Pasa el String con la fecha a formato Date.
            fechaNac = new SimpleDateFormat("dd / MM / yyyy").parse(fecha);
        } catch (Exception ex) {
            Toast.makeText(getApplicationContext(), getString(R.string.errorFecha), Toast.LENGTH_SHORT).show();
        }

        Calendar fechaNacimiento = Calendar.getInstance();

        //Se crea un objeto con la fecha actual.
        Calendar fechaActual = Calendar.getInstance();

        //Se asigna la fecha recibida a la fecha de nacimiento.
        fechaNacimiento.setTime(fechaNac);

        //Se restan la fecha actual y la fecha de nacimiento.
        int anio = fechaActual.get(Calendar.YEAR)- fechaNacimiento.get(Calendar.YEAR);
        int mes =fechaActual.get(Calendar.MONTH)- fechaNacimiento.get(Calendar.MONTH);
        int dia = fechaActual.get(Calendar.DATE)- fechaNacimiento.get(Calendar.DATE);

        //Se ajusta el año dependiendo el mes y el día.
        if(mes<0 || (mes==0 && dia<0)){
            anio--;
        }

        //Si la persona tiene menos de 18 años...
        if(anio<18)
            //Devuelve false.
            return false;
        //Si tiene mas de 18 años...
        else
            //Devuelve true.
            return true;

    }

}