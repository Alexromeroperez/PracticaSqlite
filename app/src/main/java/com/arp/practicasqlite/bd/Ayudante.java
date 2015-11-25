package com.arp.practicasqlite.bd;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Alex on 13/11/2015.
 */
public class Ayudante extends SQLiteOpenHelper{

    public static final String DATABASE_NAME = "recetario.sqlite";
    public static final int DATABASE_VERSION = 1;

    public Ayudante(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        Log.v("ENTRA 1", "ENTRA EN  EL CONSTRUCTOR");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql="";
        sql="create table "+Contrato.TablaReceta.TABLA+
                " ("+ Contrato.TablaReceta._ID+" integer primary key autoincrement, "+
                Contrato.TablaReceta.NOMBRE+" text, "+
                Contrato.TablaReceta.FOTO+" text, "+
                Contrato.TablaReceta.DIFICULTAD+" text, "+
                Contrato.TablaReceta.TIEMPO+" text," +
                Contrato.TablaReceta.IDCATEGORIA+" integer," +
                Contrato.TablaReceta.PASOS+" text )";
        db.execSQL(sql);
        Log.v("ENTRA 1", sql);

        String sql2="create table "+ Contrato.TablaIngrediente.TABLA+
                " ("+ Contrato.TablaIngrediente._ID+" integer primary key autoincrement, "+
                Contrato.TablaIngrediente.NOMBRE+" text )";
        db.execSQL(sql2);
        Log.v("ENTRA 2", sql2);

        String sql3="create table "+ Contrato.TablaCategoria.TABLA+
                " ("+ Contrato.TablaCategoria._ID+" integer primary key autoincrement, "+
                Contrato.TablaCategoria.NOMBRE+" text )";
        db.execSQL(sql3);
        Log.v("ENTRA 3", sql3);

        String sql4="create table "+Contrato.TablaRecetaIngrediente.TABLA+
                " ("+ Contrato.TablaRecetaIngrediente._ID+" integer primary key autoincrement, "+
                Contrato.TablaRecetaIngrediente.IDRECETA+" integer, "+
                Contrato.TablaRecetaIngrediente.IDINGREDIENTE+" integer, "+
                Contrato.TablaRecetaIngrediente.CANTIDAD+" text )";
        db.execSQL(sql4);

        sql="insert into "+Contrato.TablaCategoria.TABLA+" VALUES (1,'Carnes')";
        db.execSQL(sql);
        sql="insert into "+Contrato.TablaCategoria.TABLA+" VALUES (2,'Pescados')";
        db.execSQL(sql);
        sql="insert into "+Contrato.TablaCategoria.TABLA+" VALUES (3,'Arroz')";
        db.execSQL(sql);
        sql="insert into "+Contrato.TablaCategoria.TABLA+" VALUES (4,'Postres')";
        db.execSQL(sql);
        sql="insert into "+Contrato.TablaCategoria.TABLA+" VALUES (5,'Salsas')";
        db.execSQL(sql);
        sql="insert into "+Contrato.TablaCategoria.TABLA+" VALUES (6,'Guarnicion')";
        db.execSQL(sql);
        sql="insert into "+Contrato.TablaCategoria.TABLA+" VALUES (7,'Ensaladas')";
        db.execSQL(sql);
        sql="insert into "+Contrato.TablaCategoria.TABLA+" VALUES (8,'Pasta')";
        db.execSQL(sql);
        sql="insert into "+Contrato.TablaCategoria.TABLA+" VALUES (9,'Otros')";
        db.execSQL(sql);
        Log.v("ENTRA 4", sql4);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql="drop table if exists "
                + Contrato.TablaReceta.TABLA;
        db.execSQL(sql);
        sql="drop table if exists "
                + Contrato.TablaCategoria.TABLA;
        db.execSQL(sql);
        sql="drop table if exists "
                + Contrato.TablaRecetaIngrediente.TABLA;
        db.execSQL(sql);
        sql="drop table if exists "
                + Contrato.TablaIngrediente.TABLA;
        db.execSQL(sql);

        onCreate(db);
    }
}
