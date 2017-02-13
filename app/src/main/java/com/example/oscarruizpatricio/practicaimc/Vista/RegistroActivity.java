package com.example.oscarruizpatricio.practicaimc.Vista;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.oscarruizpatricio.practicaimc.BaseDatosUsuario;
import com.example.oscarruizpatricio.practicaimc.R;
import com.example.oscarruizpatricio.practicaimc.Usuario;

public class RegistroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

    }

    //Método que verifica y registra cuando se pulsa el botón registrar
    public void Registrar (View view) {

        BaseDatosUsuario baseDatosUsuario = new BaseDatosUsuario(this, "Mibd", null, 1);

        String usuario;
        String password;
        boolean existe;
        boolean campoVacio = false;

        EditText user = (EditText)findViewById(R.id.editTextUser);
        EditText pass = (EditText)findViewById(R.id.editTextPass);

        usuario = user.getText().toString();
        password = pass.getText().toString();

        Log.d(getClass().getCanonicalName(),"usuario: "+ usuario);
        Log.d(getClass().getCanonicalName(),"pass: " + password);

        //Error por campo vacio
        if (usuario.equals("")||password.equals("")) {
            campoVacio = true;
            //Mostrar Alerta
            AlertDialog alerta = new AlertDialog.Builder(this).create();
            alerta.setTitle("Error");
            alerta.setMessage("Alguno de los campos está vacio");
            alerta.setButton(AlertDialog.BUTTON_NEUTRAL, "Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
            alerta.show();
        }

        //Error por usuario existente
        existe = baseDatosUsuario.comprobarUsuario(usuario);
        if (existe) {
            //Mostrar alerta el usuario ya existe
            AlertDialog alerta = new AlertDialog.Builder(this).create();
            alerta.setTitle("Error");
            alerta.setMessage("El usuario ya existe");
            alerta.setButton(AlertDialog.BUTTON_NEUTRAL, "Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
            alerta.show();
        }

        //el usuario se crea correctamente
        if (campoVacio == false && existe == false) {
            //No funciona la clase Codificia
            //String passwordCodificado = Codifica.codifica(password);
            Usuario nuevoUsuario = new Usuario(usuario, password);
            //Log.d(getClass().getCanonicalName(),"passwordCodificad: " +passwordCodificado);
            baseDatosUsuario.crearUsuario(nuevoUsuario);


            //Mostrar alerta el usuario creado
            Toast toast = Toast.makeText(this, "Usuario creado correctamente", 8);
            toast.show();

            Intent intent = new Intent(this, LoginActivity.class);
            this.startActivity(intent);
        }

    }
}
