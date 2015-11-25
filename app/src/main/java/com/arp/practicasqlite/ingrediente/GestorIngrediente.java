package com.arp.practicasqlite.ingrediente;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.arp.practicasqlite.bd.Ayudante;
import com.arp.practicasqlite.bd.Contrato;

/**
 * Created by Alex on 13/11/2015.
 */
public class GestorIngrediente {

    private Ayudante abd;
    private SQLiteDatabase bd;

    public GestorIngrediente(Context c) {
        abd =new Ayudante(c);
    }

    public void open() {
        bd = abd.getWritableDatabase();
    }

    public void close() {
        abd.close();
    }

    public long insert(Ingrediente igr) {
        ContentValues valores = new ContentValues();
        valores.put(Contrato.TablaIngrediente.NOMBRE, igr.getNombre());
        long id = bd.insert(Contrato.TablaIngrediente.TABLA,null, valores);
        return id;
    }

    public int delete(long id){
        String condicion = Contrato.TablaIngrediente._ID + " = ?";
        String[] argumentos = { id + "" };
        int cuenta = bd.delete(Contrato.TablaIngrediente.TABLA, condicion, argumentos);
        return cuenta;
    }

    public int update(Ingrediente igr) {
        ContentValues valores = new ContentValues();
        valores.put(Contrato.TablaIngrediente.NOMBRE, igr.getNombre());
        String condicion = Contrato.TablaIngrediente._ID + " = ?";
        String[] argumentos = { igr.getId() + "" };
        int cuenta = bd.update(Contrato.TablaIngrediente.TABLA, valores, condicion, argumentos);
        return cuenta;
    }

    public Cursor getCursor() {
        return getCursor(null,null);
    }

    public Cursor getCursor(String condicion,String parametros[]) {
        Cursor cursor = bd.query(
                Contrato.TablaIngrediente.TABLA, null, condicion, parametros, null, null, Contrato.TablaIngrediente.NOMBRE);
        return cursor;
    }


}
