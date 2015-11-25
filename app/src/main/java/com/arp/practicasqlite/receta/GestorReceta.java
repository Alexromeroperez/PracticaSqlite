package com.arp.practicasqlite.receta;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.arp.practicasqlite.bd.Ayudante;
import com.arp.practicasqlite.bd.Contrato;
import com.arp.practicasqlite.recetaingrediente.RecetaIngrediente;

/**
 * Created by Alex on 13/11/2015.
 */
public class GestorReceta {

    private Ayudante abd;
    private SQLiteDatabase bd;

    public GestorReceta(Context c) {
        abd =new Ayudante(c);
    }

    public void open() {
        bd = abd.getWritableDatabase();
    }

    public void close() {
        abd.close();
    }

    public long insert(Receta receta) {
        ContentValues valores = new ContentValues();
        valores.put(Contrato.TablaReceta.NOMBRE, receta.getNombre());
        valores.put(Contrato.TablaReceta.DIFICULTAD, receta.getDificultad());
        valores.put(Contrato.TablaReceta.FOTO, receta.getFoto());
        valores.put(Contrato.TablaReceta.PASOS, receta.getPasos());
        valores.put(Contrato.TablaReceta.IDCATEGORIA, receta.getIdcategoria());
        valores.put(Contrato.TablaReceta.TIEMPO, receta.getTiempo());
        return bd.insert(Contrato.TablaReceta.TABLA,null, valores);
    }

    public int delete(long id){
        String condicion = Contrato.TablaReceta._ID + " = ?";
        String[] argumentos = { id + "" };
        return bd.delete(Contrato.TablaReceta.TABLA, condicion, argumentos);
    }

    public int update(Receta receta) {
        ContentValues valores = new ContentValues();
        valores.put(Contrato.TablaReceta.NOMBRE, receta.getNombre());
        valores.put(Contrato.TablaReceta.DIFICULTAD, receta.getDificultad());
        valores.put(Contrato.TablaReceta.FOTO, receta.getFoto());
        valores.put(Contrato.TablaReceta.PASOS, receta.getPasos());
        valores.put(Contrato.TablaReceta.IDCATEGORIA, receta.getIdcategoria());
        valores.put(Contrato.TablaReceta.TIEMPO, receta.getTiempo());
        String condicion = Contrato.TablaReceta._ID + " = ?";
        String[] argumentos = { receta.getId() + "" };
        return  bd.update(Contrato.TablaReceta.TABLA, valores, condicion, argumentos);
    }

    public Cursor getCursor() {
        return getCursor(null,null);
    }

    public Cursor getCursor(String condicion,String []parametros) {
        Cursor cursor = bd.query(
                Contrato.TablaReceta.TABLA, null, condicion, parametros, null, null, Contrato.TablaReceta.IDCATEGORIA);
        return cursor;
    }

    public Receta getRow(long id){
        String[] parametros = new String[] { id+"" };
        Cursor c=bd.rawQuery("select * from "+Contrato.TablaReceta.TABLA+
                " where "+Contrato.TablaReceta._ID+" =?",parametros);
        c.moveToFirst();
        Receta r=new Receta();
        r= r.getRow(c);
        c.close();
        return r;
    }
}
