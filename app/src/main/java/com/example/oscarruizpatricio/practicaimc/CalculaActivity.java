package com.example.oscarruizpatricio.practicaimc;

import android.content.Intent;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import static com.example.oscarruizpatricio.practicaimc.R.id.textView;
import static com.example.oscarruizpatricio.practicaimc.R.id.text_View_Resultado;
import static com.example.oscarruizpatricio.practicaimc.R.id.text_View_Resultado2;

public class CalculaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calcula);

        //Mostramos valor IMC que viene del Listening
        Bundle valor_IMC = getIntent().getExtras();
        if (valor_IMC == null) {
            return;
        }
        Double valor_recibido = 0.0;
        valor_recibido = valor_IMC.getDouble("valor");
        String valor_recibido_String = "" + valor_recibido;

        TextView texto_resultado = (TextView)findViewById(R.id.text_View_Resultado);
        texto_resultado.setText(valor_recibido_String);

        //Mostrar cadena de texto en función del valor de IMC
        TextView texto_resultado2 = (TextView)findViewById(R.id.text_View_Resultado2);
        if (valor_recibido < 16) {
                texto_resultado2.setText("Tienes un IMC demasiado bajo, estas en desnutrición"); }
        else if (valor_recibido >= 16 && valor_recibido < 18.5) {
            texto_resultado2.setText("Tienes un IMC bajo, estas por debajo del peso ideal"); }
        else if (valor_recibido >= 18.5 && valor_recibido <= 25) {
            texto_resultado2.setText("Tu IMC está en la media recomendada"); }
        else if (valor_recibido > 25 && valor_recibido <=31) {
            texto_resultado2.setText("Tu IMC está por encima de lo recomendado, tienes sobrepeso"); }
        else  {
            texto_resultado2.setText("Tu IMC está muy alto. Tienes obesidad"); }


        //Listener botón Volver
        View.OnClickListener listener = new Listening(this);
        Button boton_volver = (Button)findViewById(R.id.button_Volver);
        boton_volver.setOnClickListener(listener);



    }
}
