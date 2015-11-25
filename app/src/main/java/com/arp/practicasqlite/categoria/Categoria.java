package com.arp.practicasqlite.categoria;

import android.database.Cursor;

import com.arp.practicasqlite.bd.Contrato;

/**
 * Created by Alex on 13/11/2015.
 */
public class Categoria {

    private long id;
    private String nombre;

    public Categoria() {
    }

    public Categoria(long id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Categoria categoria = (Categoria) o;

        if (id != categoria.id) return false;
        return !(nombre != null ? !nombre.equals(categoria.nombre) : categoria.nombre != null);

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (nombre != null ? nombre.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Categoria{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                '}';
    }

    public Categoria getRow(Cursor c) {
        Categoria ct = new Categoria();
        ct.setId(c.getLong(c.getColumnIndex(Contrato.TablaCategoria._ID)));
        ct.setNombre(c.getString(c.getColumnIndex(Contrato.TablaCategoria.NOMBRE)));
        return ct;
    }
}
