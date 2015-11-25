package com.arp.practicasqlite.categoria;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.arp.practicasqlite.bd.Ayudante;
import com.arp.practicasqlite.bd.Contrato;

/**
 * Created by Alex on 13/11/2015.
 */
public class GestorCategoria {

    private Ayudante abd;
    private SQLiteDatabase bd;

    public GestorCategoria(Context c) {
        abd=new Ayudante(c);
    }

    public void open() {
        bd = abd.getWritableDatabase();
    }

    public void close() {
        abd.close();
    }

    public long insert(Categoria ct) {
        ContentValues valores = new ContentValues();
        valores.put(Contrato.TablaCategoria.NOMBRE, ct.getNombre());
        return bd.insert(Contrato.TablaCategoria.TABLA,null, valores);
    }

    public int delete(long id){
        String condicion = Contrato.TablaCategoria._ID + " = ?";
        String[] argumentos = { id + "" };
        return bd.delete(Contrato.TablaCategoria.TABLA, condicion, argumentos);
    }

    public int update(Categoria ct) {
        ContentValues valores = new ContentValues();
        valores.put(Contrato.TablaCategoria.NOMBRE, ct.getNombre());
        String condicion = Contrato.TablaCategoria._ID + " = ?";
        String[] argumentos = { ct.getId() + "" };
        return  bd.update(Contrato.TablaCategoria.TABLA, valores, condicion, argumentos);
    }

    public Cursor getCursor() {
        return getCursor(null,null);
    }
    public Cursor getCursor(String condicion,String[] parametros) {
        Cursor cursor = bd.query(
                Contrato.TablaCategoria.TABLA, null, condicion, parametros, null, null, Contrato.TablaCategoria.NOMBRE);
        return cursor;
    }

}
