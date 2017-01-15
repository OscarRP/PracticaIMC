package com.example.oscarruizpatricio.practicaimc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Creo el objeto que escucha en esta Activity
        View.OnClickListener listener = new Listening(this);

        //Asocio Boton Calcular al listener
        Button boton_calcular = (Button)findViewById(R.id.buttonCalcula);
        boton_calcular.setOnClickListener(listener);

        //Asocio Boton Tabla al listener
        Button boton_tabla = (Button)findViewById(R.id.buttonTabla);
        boton_tabla.setOnClickListener(listener);
    }

}
