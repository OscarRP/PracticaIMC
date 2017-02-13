package com.example.oscarruizpatricio.practicaimc.Vista;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.oscarruizpatricio.practicaimc.Listening;
import com.example.oscarruizpatricio.practicaimc.R;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        boolean userLogged = true;

        EditText textUsuario = (EditText)findViewById(R.id.userEditText);
        EditText textPassword = (EditText)findViewById(R.id.passEditText);
        Button botonLogin = (Button)findViewById(R.id.loginButton);
        Button botonRegistrar = (Button)findViewById(R.id.registerButton);

        View.OnClickListener listener = new Listening(this, textUsuario, textPassword);

        botonLogin.setOnClickListener(listener);
        botonRegistrar.setOnClickListener(listener);
    }
}
