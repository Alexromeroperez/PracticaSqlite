package com.arp.practicasqlite;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.arp.practicasqlite.bd.Contrato;
import com.arp.practicasqlite.ingrediente.GestorIngrediente;
import com.arp.practicasqlite.receta.GestorReceta;
import com.arp.practicasqlite.receta.Receta;
import com.arp.practicasqlite.recetaingrediente.GestorRecetaIngrediente;

public class Detalle extends AppCompatActivity {

    private TextView tvNombre,tvIngrediente,tvPasos;
    private GestorIngrediente gi;
    private GestorRecetaIngrediente gri;
    private long id;
    private String pasos,nombre;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle);
    }

    /********************************************/
    /**************ACTIVIDAD*************************/
    /********************************************/
    /********************************************/

    @Override
    protected void onResume() {
        ini();
        gi.open();
        gri.open();
        ingredientes();
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        gi.close();
        gri.close();
    }

    /********************************************/
    /**************OTROS*************************/
    /********************************************/
    /********************************************/

    private void ini(){
        gi=new GestorIngrediente(this);
        gri=new GestorRecetaIngrediente(this);
        Bundle b=getIntent().getExtras();
        id = b.getLong("id");
        pasos=b.getString("pasos");
        nombre=b.getString("nombre");
        tvNombre=(TextView)findViewById(R.id.tvNombre);
        tvIngrediente=(TextView)findViewById(R.id.tvIngredientes);
        tvPasos=(TextView)findViewById(R.id.tvPasos);
        tvNombre.setText(nombre);
        tvPasos.setText(pasos);


    }

    private void ingredientes(){
        String total="";
        String condicion=Contrato.TablaRecetaIngrediente.IDRECETA +" =?";
        String parametros[]={id+""};
        Cursor c=gri.getCursor(condicion, parametros);
        c.moveToFirst();
        while (c.moveToNext()){
            total+=c.getString(c.getColumnIndex(Contrato.TablaRecetaIngrediente.CANTIDAD))+"\n";
        }
        tvIngrediente.setText(total);
    }
}
