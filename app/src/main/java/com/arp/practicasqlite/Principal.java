package com.arp.practicasqlite;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import com.arp.practicasqlite.adaptadores.Adaptador;
import com.arp.practicasqlite.bd.Contrato;
import com.arp.practicasqlite.categoria.GestorCategoria;
import com.arp.practicasqlite.ingrediente.GestorIngrediente;
import com.arp.practicasqlite.ingrediente.Ingrediente;
import com.arp.practicasqlite.receta.GestorReceta;
import com.arp.practicasqlite.receta.Receta;
import com.arp.practicasqlite.recetaingrediente.GestorRecetaIngrediente;

public class Principal extends AppCompatActivity {


    private ListView lv;
    private GestorReceta gr;
    private GestorCategoria gc;
    private GestorIngrediente gi;
    private GestorRecetaIngrediente gri;
    private Adaptador abd;
    private Cursor c;
    private int posicion;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
    }

    /********************************************/
    /**************ACTIVIDAD*************************/
    /********************************************/
    /********************************************/
    @Override
    protected void onResume() {
        gr=new GestorReceta(this);
        gc=new GestorCategoria(this);
        gi=new GestorIngrediente(this);
        gri=new GestorRecetaIngrediente(this);
        gri.open();
        gi.open();
        gr.open();
        gc.open();
        ini();
        super.onResume();
    }

    @Override
    protected void onPause() {
        gr.close();
        gc.close();
        gri.close();
        gi.close();
        super.onPause();
    }

    /********************************************/
    /**************MENUS*************************/
    /********************************************/
    /********************************************/
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_principal, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.anadirIngrediente) {
            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setTitle("Añadir Ingrediente");
            LayoutInflater inflater = LayoutInflater.from(this);
            final View vista = inflater.inflate(R.layout.anadir_ingrediente, null);
            alert.setView(vista);
            final EditText et1 = (EditText) vista.findViewById(R.id.etIngrediente);
            alert.setPositiveButton(android.R.string.ok,
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {
                            Ingrediente igr=new Ingrediente();
                            igr.setNombre(et1.getText().toString());
                            gi.insert(igr);
                        }
                    });
            alert.setNegativeButton(android.R.string.cancel,null);
            alert.show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu_receta, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        posicion=info.position;
        c=(Cursor)lv.getItemAtPosition(posicion);
        Receta r=new Receta();
        r=r.getRow(c);
        switch (item.getItemId()) {
            case R.id.modificar:
                Intent i=new Intent(this,Modifica.class);
                Bundle b=new Bundle();
                b.putLong("id",r.getId());
                b.putInt("modifica",1);
                i.putExtras(b);
                startActivity(i);
                return true;
            case R.id.borrar:
                gr.delete(r.getId());
                gri.delete(r.getId());
                abd.changeCursor(gr.getCursor());
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    /********************************************/
    /**************OTROS*************************/
    /********************************************/
    /********************************************/

    private void ini(){
        c=gr.getCursor();
        lv=(ListView)findViewById(R.id.lvReceta);
        abd=new Adaptador(this,c,gc);
        lv.setAdapter(abd);
        registerForContextMenu(lv);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Receta r=gr.getRow(id);
                Intent i=new Intent(Principal.this,Detalle.class);
                Bundle b=new Bundle();
                b.putLong("id",r.getId());
                b.putString("nombre",r.getNombre());
                b.putString("pasos",r.getPasos());
                i.putExtras(b);
                startActivity(i);
            }
        });
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(Principal.this,Modifica.class);
                startActivity(i);
            }
        });
    }
}
