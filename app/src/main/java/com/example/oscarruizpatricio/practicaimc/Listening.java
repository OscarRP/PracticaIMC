package com.example.oscarruizpatricio.practicaimc;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.EditText;


/**
 * Created by oscarruizpatricio on 27/12/16.
 */

public class Listening implements View.OnClickListener {
    Context context;

    public Listening (Context context) {this.context = context;}

    //Calcular IMC
    private double calcularIMC () {

        //Declaro las variables que voy a necesitar
        double altura = 0;
        String altura_cadena = null;
        double peso = 0;
        String peso_cadena = null;
        double imc = 0;
        Activity a = null;

        //Asigno la Activity de MainActivity para recuperar los datos de peso y altura introducidos
        a = (Activity)context;
        //Asigno a las variables los valores de las cajas de texto
        EditText caja_altura = (EditText) a.findViewById(R.id.editTextAltura);
        EditText caja_peso = (EditText) a.findViewById(R.id.editTextPeso);

        altura_cadena = caja_altura.getText().toString();
        peso_cadena = caja_peso.getText().toString();

        //Convierto en enteros y Double los valores de las cajas de texto
        //altura = Integer.parseInt(altura_cadena);
        altura = Double.parseDouble(altura_cadena);
        peso = Double.parseDouble(peso_cadena);
        Log.d(Listening.class.getCanonicalName(), "valor de altura " + altura);
        Log.d(Listening.class.getCanonicalName(), "valor de peso " + peso);

        //Paso la altura a metros
        altura = altura / 100;
        Log.d(Listening.class.getCanonicalName(), "valor de altura " + altura);

        //Calculo el IMC
        imc = peso / (altura * altura);
        Log.d(Listening.class.getCanonicalName(), "valor de IMC " + imc);
        return imc;
    }

    @Override
    public void onClick (View boton_pulsado) {
        //Asignamos a la variable el id del botón pulsado
        int id_boton_pulsado = boton_pulsado.getId();

        //Con la sentencia Switch sabremos qué botón ha pulsado para así poder ir a la actividad correspondiente
        switch (id_boton_pulsado) {
            case R.id.buttonCalcula:
                Log.d(getClass().getCanonicalName(), "Ha pulsado el botón Calcula");
                //ha pulsado el botón calcular IMC, así que hacemos intent para ir a la Activity
                Intent intent = new Intent(context, CalculaActivity.class);
                //Le mandamos a la actividad destino el valor de IMC
                intent.putExtra("valor", calcularIMC());
                context.startActivity(intent);
                break;
            case R.id.button_Volver:
                Log.d(getClass().getCanonicalName(), "Ha pulsado el botón Volver");
                //ha pulsado el botón Volver de la actividad CalculaActivity
                Intent intento = new Intent(context, MainActivity.class);
                context.startActivity(intento);
                break;
            case R.id.buttonTabla:
                Log.d(getClass().getCanonicalName(), "Ha pulsado el botón Tabla");
                //ha pulsado el botón info tabla IMC.
                Log.d(Listening.class.getCanonicalName(), "Ha pulsado botón Info Tabla");
                intento = new Intent(context, InfoActivity.class);
                context.startActivity(intento);
                break;
            case R.id.boton_volver:
                Log.d(getClass().getCanonicalName(), "Ha pulsado el botón Volver");
                intento = new Intent(context, MainActivity.class);
                context.startActivity(intento);
        }

    }

}
