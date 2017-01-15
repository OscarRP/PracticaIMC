package com.example.oscarruizpatricio.practicaimc;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by oscarruizpatricio on 7/1/17.
 */

public class Adapter extends BaseAdapter {

    //Se crean las variables locales
    private Context context;
    private String[] info;
    private String[] info2;
    private int id_layout_celda;
    private Integer[] arrayImagenes;

    public Adapter (Context c, String[] info, String [] info2,  Integer[] images, int id_layout_celda){
        //Asiganamos en el constructor los datos que nos pasan a las variables locales
        context = c;
        this.info = info;
        this.info2 = info2;
        this.arrayImagenes = images;
        this.id_layout_celda = id_layout_celda;
    }
    public int getCount () {
        return info.length;
    }
    public long getItemId (int position){ //Método obligatorio
        return position;
    }
    public Object getItem (int position) { //Método obligatorio
        return null;
    }

    public View getView (int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if (v==null) { //Se infla la vista si es la primera vez que se va a mostrar
            LayoutInflater layoutInflater = LayoutInflater.from(context);
            v = layoutInflater.inflate(id_layout_celda, null);
        }

        //Creamos variables con los datos que hay que mostrar
        String item1 = info[position];
        String item2 = info2[position];
        Integer itemImagen = arrayImagenes[position];

        //Creamos los objetos textView del XML fila
        TextView textView_info = (TextView) v.findViewById(R.id.text_view_info);
        TextView textView_info2 = (TextView) v.findViewById(R.id.text_view_info2);
        ImageView imageView = (ImageView) v.findViewById(R.id.image);

        //Establecemos el texto a mostrar en la fila
        Log.d(getClass().getCanonicalName(),"El valor de textView_info es: " + item1);
        Log.d(getClass().getCanonicalName(),"El valor de textView_info2 es: " + item2);

        textView_info.setText(item1);
        textView_info2.setText(item2);
        imageView.setImageResource(itemImagen);

        //Devolvemos el valor de la vista
        return v;
    }

}
