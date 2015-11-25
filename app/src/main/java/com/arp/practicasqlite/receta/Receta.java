package com.arp.practicasqlite.receta;

import android.database.Cursor;

import com.arp.practicasqlite.bd.Contrato;

/**
 * Created by Alex on 13/11/2015.
 */
public class Receta {

    private long id,idcategoria;
    private String nombre,foto,tiempo,dificultad,pasos;

    public Receta() {
    }

    public Receta(long id, long idcategoria, String nombre, String foto, String tiempo, String dificultad, String pasos) {
        this.id = id;
        this.idcategoria = idcategoria;
        this.nombre = nombre;
        this.foto = foto;
        this.tiempo = tiempo;
        this.dificultad = dificultad;
        this.pasos = pasos;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getIdcategoria() {
        return idcategoria;
    }

    public void setIdcategoria(long idcategoria) {
        this.idcategoria = idcategoria;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getTiempo() {
        return tiempo;
    }

    public void setTiempo(String tiempo) {
        this.tiempo = tiempo;
    }

    public String getDificultad() {
        return dificultad;
    }

    public void setDificultad(String dificultad) {
        this.dificultad = dificultad;
    }

    public String getPasos() {
        return pasos;
    }

    public void setPasos(String pasos) {
        this.pasos = pasos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Receta receta = (Receta) o;

        return id == receta.id;

    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }

    @Override
    public String toString() {
        return "Receta{" +
                "id=" + id +
                ", idcategoria=" + idcategoria +
                ", nombre='" + nombre + '\'' +
                ", foto='" + foto + '\'' +
                ", tiempo='" + tiempo + '\'' +
                ", dificultad='" + dificultad + '\'' +
                ", pasos='" + pasos + '\'' +
                '}';
    }

    public Receta getRow(Cursor c) {
        Receta r = new Receta();
        r.setId(c.getLong(c.getColumnIndex(Contrato.TablaReceta._ID)));
        r.setNombre(c.getString(c.getColumnIndex(Contrato.TablaReceta.NOMBRE)));
        r.setFoto(c.getString(c.getColumnIndex(Contrato.TablaReceta.FOTO)));
        r.setIdcategoria(c.getLong(c.getColumnIndex(Contrato.TablaReceta.IDCATEGORIA)));
        r.setPasos(c.getString(c.getColumnIndex(Contrato.TablaReceta.PASOS)));
        r.setDificultad(c.getString(c.getColumnIndex(Contrato.TablaReceta.DIFICULTAD)));
        r.setTiempo(c.getString(c.getColumnIndex(Contrato.TablaReceta.TIEMPO)));
        return r;
    }
}
