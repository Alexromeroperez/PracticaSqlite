package com.arp.practicasqlite.recetaingrediente;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.arp.practicasqlite.bd.Ayudante;
import com.arp.practicasqlite.bd.Contrato;

/**
 * Created by Alex on 13/11/2015.
 */
public class GestorRecetaIngrediente {

    private Ayudante abd;
    private SQLiteDatabase bd;

    public GestorRecetaIngrediente(Context c) {
        abd =new Ayudante(c);
    }

    public void open() {
        bd = abd.getWritableDatabase();
    }

    public void close() {
        abd.close();
    }

    public long insert(RecetaIngrediente ri) {
        ContentValues valores = new ContentValues();
        valores.put(Contrato.TablaRecetaIngrediente.IDRECETA, ri.getIdreceta());
        valores.put(Contrato.TablaRecetaIngrediente.IDINGREDIENTE, ri.getIdingrediente());
        valores.put(Contrato.TablaRecetaIngrediente.CANTIDAD, ri.getCantidad());
        long id = bd.insert(Contrato.TablaRecetaIngrediente.TABLA,null, valores);
        return id;
    }

    public int delete(long id){
        String condicion = Contrato.TablaRecetaIngrediente.IDRECETA + " = ?";
        String[] argumentos = { id + "" };
        int cuenta = bd.delete(Contrato.TablaRecetaIngrediente.TABLA, condicion, argumentos);
        return cuenta;
    }

    public int update(RecetaIngrediente ri) {
        ContentValues valores = new ContentValues();
        valores.put(Contrato.TablaRecetaIngrediente.IDINGREDIENTE, ri.getIdingrediente());
        valores.put(Contrato.TablaRecetaIngrediente.CANTIDAD, ri.getCantidad());
        String condicion = Contrato.TablaRecetaIngrediente.IDRECETA + " = ?";
        String[] argumentos = { ri.getIdreceta() + "" };
        int cuenta = bd.update(Contrato.TablaRecetaIngrediente.TABLA, valores, condicion, argumentos);
        return cuenta;
    }

    public Cursor getCursor() {
        return getCursor(null,null);
    }

    public Cursor getCursor(String condicion,String []parametros) {
        Cursor cursor = bd.query(
                Contrato.TablaRecetaIngrediente.TABLA, null, condicion, parametros, null, null, Contrato.TablaRecetaIngrediente.IDINGREDIENTE+" , "+Contrato.TablaRecetaIngrediente.IDRECETA+" , "+Contrato.TablaRecetaIngrediente.CANTIDAD);
        return cursor;
    }
}
