package com.example.oscarruizpatricio.practicaimc.Vista;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.oscarruizpatricio.practicaimc.Listening;
import com.example.oscarruizpatricio.practicaimc.R;

public class AjustesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajustes);

        ImageView imageView = (ImageView)findViewById(R.id.imageView);
        View.OnClickListener listener = new Listening(this);
        imageView.setOnClickListener(listener);
    }
}
