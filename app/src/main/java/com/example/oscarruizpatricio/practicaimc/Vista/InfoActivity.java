package com.example.oscarruizpatricio.practicaimc.Vista;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.oscarruizpatricio.practicaimc.Adapter;
import com.example.oscarruizpatricio.practicaimc.Listening;
import com.example.oscarruizpatricio.practicaimc.R;

public class InfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        //Creamos el Array
        String[] array_info = new String[] {
                "IMC < 16",
                "IMC 16.0 - 18.5",
                "IMC 18.5 - 25",
                "IMC 25-0 - 31",
                "IMC > 31"
        };
        String[] array_info2 = new String[] {
                "Desnutrición",
                "Por debajo del peso",
                "En la media recomendada",
                "Sobrepeso",
                "Obesidad"
        };
        Integer[] arrayImages = new Integer[] {
                R.drawable.desnutrido,
                R.drawable.delgado,
                R.drawable.normal,
                R.drawable.sobrepeso,
                R.drawable.obeso1
        };

        Log.d(InfoActivity.class.getCanonicalName(),"Hemos creado el array");
        //Creamos el Adapter
        ListAdapter adaptador = new Adapter(this, array_info, array_info2, arrayImages, R.layout.fila);
        //Asociar el Adapter a la vista
        ListView listview = (ListView) findViewById(R.id.tabla_info);
        listview.setAdapter(adaptador);

        //Listener botón Volver
        View.OnClickListener listener = new Listening(this);
        Button boton_volver = (Button)findViewById(R.id.boton_volver);
        boton_volver.setOnClickListener(listener);
    }
}
