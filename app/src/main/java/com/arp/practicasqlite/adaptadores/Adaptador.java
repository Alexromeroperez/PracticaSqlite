package com.arp.practicasqlite.adaptadores;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.arp.practicasqlite.R;
import com.arp.practicasqlite.bd.Contrato;
import com.arp.practicasqlite.categoria.GestorCategoria;
import com.arp.practicasqlite.receta.Receta;
import com.squareup.picasso.Picasso;

/**
 * Created by Alex on 21/11/2015.
 */
public class Adaptador extends CursorAdapter {

    private TextView tvNombre,tvCategoria,tvDificultad,tvTiempo;
    private ImageView iv;
    private GestorCategoria gc;

    public Adaptador(Context context, Cursor c,GestorCategoria gc) {
        super(context, c,true);
        this.gc=gc;
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        LayoutInflater i=LayoutInflater.from(parent.getContext());
        View v=i.inflate(R.layout.lista_detalle,parent,false);
        return v;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        tvNombre=(TextView)view.findViewById(R.id.tvTitulo);
        tvCategoria=(TextView)view.findViewById(R.id.tvCategoria);
        tvDificultad=(TextView)view.findViewById(R.id.tvDificultad);
        tvTiempo=(TextView)view.findViewById(R.id.tvTiempo);
        iv=(ImageView)view.findViewById(R.id.ivFoto);
        Receta r=new Receta();
        r=r.getRow(cursor);
        String condicion= Contrato.TablaCategoria._ID+" =? ";
        String parametros[]= {r.getIdcategoria() + ""};
        cursor=gc.getCursor(condicion, parametros);
        cursor.moveToFirst();
        tvNombre.setText(r.getNombre());
        tvCategoria.setText(cursor.getString(cursor.getColumnIndex(Contrato.TablaCategoria.NOMBRE)));
        tvDificultad.setText(r.getDificultad());
        tvTiempo.setText(r.getTiempo());
        iv.setImageResource(R.drawable.images);
    }
}
