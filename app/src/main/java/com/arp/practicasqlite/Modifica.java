package com.arp.practicasqlite;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;

import com.arp.practicasqlite.bd.Contrato;
import com.arp.practicasqlite.categoria.GestorCategoria;
import com.arp.practicasqlite.ingrediente.GestorIngrediente;
import com.arp.practicasqlite.receta.GestorReceta;
import com.arp.practicasqlite.receta.Receta;
import com.arp.practicasqlite.recetaingrediente.GestorRecetaIngrediente;
import com.arp.practicasqlite.recetaingrediente.RecetaIngrediente;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class Modifica extends AppCompatActivity {

    private GestorCategoria gc;
    private GestorIngrediente gi;
    private GestorRecetaIngrediente gri;
    private GestorReceta gr;
    private Receta r;
    private Spinner spCategoria;
    private EditText etNombre,etPasos,etTiempo,etDificultad,etCantidad;
    private ImageView iv;
    private Spinner sp;
    private LinearLayout layout,ll;
    private SimpleCursorAdapter sca;
    private final static int CAMARA=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modifica);
    }

    /********************************************/
    /**************MENUS*************************/
    /********************************************/
    /********************************************/

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_modifica, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.aceptar) {
            if(r!=null) {
           /* modifica el objeto*/
                String condicion = Contrato.TablaRecetaIngrediente.IDRECETA + " =?";
                String parametros[] = {r.getId() + ""};
                Cursor c = gri.getCursor(condicion, parametros);
                c.moveToFirst();
                while (c.moveToNext()) {
                    RecetaIngrediente ri = new RecetaIngrediente();
                    ri = ri.getRow(gri.getCursor(condicion, parametros));
                    gri.update(ri);
                }
                r.setNombre(etNombre.getText().toString());
                r.setIdcategoria(spCategoria.getSelectedItemId());
                r.setDificultad(etDificultad.getText().toString());
                r.setTiempo(etTiempo.getText().toString());
                r.setPasos(etPasos.getText().toString());
                r.setFoto("");
                gr.update(r);
            }else {
                //añade uno nuevo
                r = new Receta();
                r.setNombre(etNombre.getText().toString());
                r.setIdcategoria(spCategoria.getSelectedItemId());
                r.setDificultad(etDificultad.getText().toString());
                r.setTiempo(etTiempo.getText().toString());
                r.setPasos(etPasos.getText().toString());
                r.setFoto("");
                long idreceta = gr.insert(r);
                for (int i = 0; i < layout.getChildCount(); i++) {
                    RecetaIngrediente ri = new RecetaIngrediente();
                    ri.setIdreceta(idreceta);
                    ri.setIdingrediente(((Spinner) ((LinearLayout) layout.getChildAt(i)).getChildAt(0)).getSelectedItemId());
                    ri.setCantidad(((EditText) ((LinearLayout) layout.getChildAt(i)).getChildAt(1)).getText().toString());
                    Log.v("NUMERO DE HIJOS", ri.toString());
                    gri.insert(ri);
                }
            }
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /********************************************/
    /**************ACTIVIDAD*************************/
    /********************************************/
    /********************************************/
    @Override
    protected void onResume() {
        gc=new GestorCategoria(this);
        gi=new GestorIngrediente(this);
        gr=new GestorReceta(this);
        gri=new GestorRecetaIngrediente(this);
        gri.open();
        gr.open();
        gi.open();
        gc.open();
        ini();
        super.onResume();

    }

    @Override
    protected void onPause() {
        super.onPause();
        gc.close();
        gri.close();
        gi.close();
        gr.close();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==CAMARA && resultCode==RESULT_OK) {
           /* Bitmap foto = (Bitmap) data.getExtras().get("data");
            FileOutputStream salida;
            //salida = new FileOutputStream(getExternalFilesDir(Environment.DIRECTORY_PICTURES) + "/"+data.toString()+".jpg");
            //foto.compress(Bitmap.CompressFormat.JPEG, 90, salida);
            iv.setImageBitmap(foto);*/
        }
    }

    /********************************************/
    /**************OTROS*************************/
    /********************************************/
    /********************************************/

    private void ini(){
        iv=(ImageView)findViewById(R.id.ivFoto);
        spCategoria=(Spinner)findViewById(R.id.spCategoria);
        layout=(LinearLayout)findViewById(R.id.lIgr);
        adaptarSpinner(gc.getCursor());
        spCategoria.setAdapter(sca);
        etNombre=(EditText)findViewById(R.id.etNombre);
        etPasos=(EditText)findViewById(R.id.etPasos);
        etTiempo=(EditText)findViewById(R.id.etTiempo);
        etDificultad=(EditText)findViewById(R.id.etDificultad);
        Bundle b=getIntent().getExtras();
        if(b!=null){
            r=gr.getRow(b.getLong("id"));
            etNombre.setText(r.getNombre());
            etPasos.setText(r.getPasos());
            etDificultad.setText(r.getDificultad());
            etTiempo.setText(r.getTiempo());
            spCategoria.setSelection((int) r.getIdcategoria());
        }
        FloatingActionButton fab= (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent("android.media.action.IMAGE_CAPTURE");
                startActivityForResult(i, CAMARA);
            }
        });
    }

    private void adaptarSpinner(Cursor c){
        sca=new SimpleCursorAdapter(this,android.R.layout.simple_spinner_item,c,
                new String[]{Contrato.TablaCategoria.NOMBRE},new int[]{android.R.id.text1},SimpleCursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        sca.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    }

    public void insertar(View v){
        nuevoIngrediente(0,"");
    }

    private void nuevoIngrediente(int posicion,String cantidad){
        ll=new LinearLayout(this);
        ll.setOrientation(LinearLayout.HORIZONTAL);
        etCantidad=new EditText(this);
        etCantidad.setHint("Cantidad");
        etCantidad.setText(cantidad);
        sp=new Spinner(this);
        adaptarSpinner(gi.getCursor());
        sp.setAdapter(sca);
        sp.setSelection(posicion);
        ViewGroup.LayoutParams parametros=new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        ll.addView(sp,parametros);
        ll.addView(etCantidad, parametros);
        layout.addView(ll);
    }
}
