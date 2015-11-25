package com.arp.practicasqlite.recetaingrediente;

import android.database.Cursor;

import com.arp.practicasqlite.bd.Contrato;

/**
 * Created by Alex on 13/11/2015.
 */
public class RecetaIngrediente {

    private long id,idreceta,idingrediente;
    private String cantidad;

    public RecetaIngrediente() {
    }

    public RecetaIngrediente(long id, String cantidad, long idingrediente, long idreceta) {
        this.id = id;
        this.cantidad = cantidad;
        this.idingrediente = idingrediente;
        this.idreceta = idreceta;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getIdreceta() {
        return idreceta;
    }

    public void setIdreceta(long idreceta) {
        this.idreceta = idreceta;
    }

    public long getIdingrediente() {
        return idingrediente;
    }

    public void setIdingrediente(long idingrediente) {
        this.idingrediente = idingrediente;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RecetaIngrediente that = (RecetaIngrediente) o;

        return id == that.id;

    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }

    @Override
    public String toString() {
        return "RecetaIngrediente{" +
                "id=" + id +
                ", idreceta=" + idreceta +
                ", idingrediente=" + idingrediente +
                ", cantidad='" + cantidad + '\'' +
                '}';
    }

    public RecetaIngrediente getRow(Cursor c) {
        RecetaIngrediente ri = new RecetaIngrediente();
        ri.setId(c.getLong(c.getColumnIndex(Contrato.TablaRecetaIngrediente._ID)));
        ri.setIdingrediente(c.getLong(c.getColumnIndex(Contrato.TablaRecetaIngrediente.IDINGREDIENTE)));
        ri.setIdreceta(c.getLong(c.getColumnIndex(Contrato.TablaRecetaIngrediente.IDRECETA)));
        ri.setCantidad(c.getString(c.getColumnIndex(Contrato.TablaRecetaIngrediente.CANTIDAD)));
        return ri;
    }
}
