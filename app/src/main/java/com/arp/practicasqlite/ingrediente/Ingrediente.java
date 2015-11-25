package com.arp.practicasqlite.ingrediente;

import android.database.Cursor;

import com.arp.practicasqlite.bd.Contrato;

/**
 * Created by Alex on 13/11/2015.
 */
public class Ingrediente {

    private long id;
    private String nombre;

    public Ingrediente() {
    }

    public Ingrediente(long id, String nombre) {
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

        Ingrediente that = (Ingrediente) o;

        if (id != that.id) return false;
        return !(nombre != null ? !nombre.equals(that.nombre) : that.nombre != null);

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (nombre != null ? nombre.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Ingrediente{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                '}';
    }

    public Ingrediente getRow(Cursor c) {
        Ingrediente igr = new Ingrediente();
        igr.setId(c.getLong(c.getColumnIndex(Contrato.TablaIngrediente._ID)));
        igr.setNombre(c.getString(c.getColumnIndex(Contrato.TablaIngrediente.NOMBRE)));
        return igr;
    }
}
