package com.example.oscarruizpatricio.practicaimc.Controlador;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.oscarruizpatricio.practicaimc.Modelo.BaseDatosUsuario;
import com.example.oscarruizpatricio.practicaimc.R;
import com.example.oscarruizpatricio.practicaimc.Vista.CalculaActivity;
import com.example.oscarruizpatricio.practicaimc.Vista.InfoActivity;
import com.example.oscarruizpatricio.practicaimc.Vista.MainActivity;
import com.example.oscarruizpatricio.practicaimc.Vista.RegistroActivity;


/**
 * Created by oscarruizpatricio on 27/12/16.
 */

public class Listening implements View.OnClickListener {
    private Context context;
    private EditText vistaUsuario;
    private EditText vistaPassword;
    private Integer contador = 0;

    public Listening (Context context) {this.context = context;}

    public Listening (Context context, EditText vistaUsuario, EditText vistaPassword) {
        this.context = context;
        this.vistaUsuario = vistaUsuario;
        this.vistaPassword = vistaPassword;
    }

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

            //Entra en la actividad CalculaActivity
            case R.id.buttonCalcula:
                Log.d(getClass().getCanonicalName(), "Ha pulsado el botón Calcula");
                //ha pulsado el botón calcular IMC, así que hacemos intent para ir a la Activity
                Intent intent = new Intent(context, CalculaActivity.class);
                //Le mandamos a la actividad destino el valor de IMC
                intent.putExtra("valor", calcularIMC());
                context.startActivity(intent);
                break;

            //Vuelve al menú principal
            case R.id.button_Volver:
                Log.d(getClass().getCanonicalName(), "Ha pulsado el botón Volver");
                //ha pulsado el botón Volver de la actividad CalculaActivity
                Intent intento = new Intent(context, MainActivity.class);
                context.startActivity(intento);
                break;

            //Muestra la actividad InfoActivity
            case R.id.buttonTabla:
                Log.d(getClass().getCanonicalName(), "Ha pulsado el botón Tabla");
                //ha pulsado el botón info tabla IMC.
                Log.d(Listening.class.getCanonicalName(), "Ha pulsado botón Info Tabla");
                intento = new Intent(context, InfoActivity.class);
                context.startActivity(intento);
                break;

            //Vuelve al menú principal
            case R.id.boton_volver:
                Log.d(getClass().getCanonicalName(), "Ha pulsado el botón Volver");
                intento = new Intent(context, MainActivity.class);
                context.startActivity(intento);
                break;

            //Comprueba si el usuario y contraseña son correctos, mostrando alertas si no lo son
            //Cierra la app al tercer intento de login fallido
            case R.id.loginButton:
                BaseDatosUsuario bd = new BaseDatosUsuario(context, "Mibd", null, 1);
                String textoUsuario = vistaUsuario.getText().toString();
                String textoPassword = vistaPassword.getText().toString();
                //No funciona la clase Codifica
                //String textoPasswordDecodificado = Codifica.decodifica(textoPassword);
                //Log.d(getClass().getCanonicalName(),"passworddeCodificado: " +textoPasswordDecodificado);

                //login correcto
                if (bd.login(textoUsuario,textoPassword)) {

                    //Guardamos en shared preferences que el usuario ya ha hecho login correcto
                    SharedPreferences sharedPreferences = context.getSharedPreferences("userLogged", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putBoolean("userLogged", true);
                    editor.commit();

                    Intent intentoMain = new Intent(context, MainActivity.class);
                    context.startActivity(intentoMain);

                //login incorrecto
                } else {
                    Log.d(getClass().getCanonicalName(), "Login Incorrecto");
                    contador = contador + 1;
                    AlertDialog alerta = new AlertDialog.Builder(context).create();
                    alerta.setTitle("Error");
                    alerta.setMessage("Datos incorrectos");
                    alerta.setButton(AlertDialog.BUTTON_NEUTRAL, "Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
                    alerta.show();
                    Log.d(getClass().getCanonicalName(),"contador: " + contador);
                    if (contador == 3) {
                        //Cierra la App
                        Intent homeIntent = new Intent(Intent.ACTION_MAIN);
                        homeIntent.addCategory( Intent.CATEGORY_HOME );
                        homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        context.startActivity(homeIntent);
                    }
                }
                break;

            //Entra en la actividad RegistroActivity
            case R.id.registerButton:
                Intent intentoRegistro = new Intent(context, RegistroActivity.class);
                context.startActivity(intentoRegistro);
                break;
            case R.id.imageView:
                ImageView imagen = new HacerFoto(context).realizarFoto();
        }

    }

}
