package com.example.oscarruizpatricio.practicaimc.Vista;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.oscarruizpatricio.practicaimc.Listening;
import com.example.oscarruizpatricio.practicaimc.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        boolean userLogged;

        SharedPreferences sharedPreferences = getSharedPreferences("userLogged", MODE_PRIVATE);

        userLogged = sharedPreferences.getBoolean("userLogged", false);

        if (userLogged == false) {

            Intent intent = new Intent(this, LoginActivity.class);
            this.startActivity(intent);

        }

        //Creo el objeto que escucha en esta Activity
        View.OnClickListener listener = new Listening(this);

        //Asocio Boton Calcular al listener
        Button boton_calcular = (Button)findViewById(R.id.buttonCalcula);
        boton_calcular.setOnClickListener(listener);

        //Asocio Boton Tabla al listener
        Button boton_tabla = (Button)findViewById(R.id.buttonTabla);
        boton_tabla.setOnClickListener(listener);
    }

    //Creación del menú
    @Override
    public boolean onCreateOptionsMenu (Menu menu) {

        super.onCreateOptionsMenu(menu);
        MenuItem ajustes = menu.add(Menu.NONE, Menu.FIRST, Menu.NONE, "Ajustes");
        MenuItem cerrarSesion = menu.add(Menu.NONE, Menu.FIRST+1, Menu.NONE, "Cerrar sesión");
        MenuItem salir = menu.add(Menu.NONE, Menu.FIRST+2, Menu.NONE, "Salir");

        return true;
    }

    @Override
    public boolean onOptionsItemSelected (MenuItem item) {

        super.onOptionsItemSelected(item);
        switch (item.getItemId()) {
            case (Menu.FIRST):
                Intent intentAjustes = new Intent(this, AjustesActivity.class);
                this.startActivity(intentAjustes);
                return true;
            case (Menu.FIRST+1):
                Intent intent = new Intent(this, LoginActivity.class);
                this.startActivity(intent);
                return true;
            case (Menu.FIRST+2):
                //Cierra la App
                Intent homeIntent = new Intent(Intent.ACTION_MAIN);
                homeIntent.addCategory( Intent.CATEGORY_HOME );
                homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(homeIntent);
                return true;
            default:
                return true;
        }
    }
}
