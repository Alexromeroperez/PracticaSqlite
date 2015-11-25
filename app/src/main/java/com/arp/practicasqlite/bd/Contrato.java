package com.arp.practicasqlite.bd;

import android.provider.BaseColumns;

/**
 * Created by Alex on 13/11/2015.
 */
public class Contrato {

    public Contrato() {
    }

    public static abstract class TablaReceta implements BaseColumns {
        public static final String TABLA = "receta";
        public static final String NOMBRE = "nombre";
        public static final String TIEMPO = "tiempo";
        public static final String DIFICULTAD = "dificultad";
        public static final String FOTO = "foto";
        public static final String PASOS = "pasos";
        public static final String IDCATEGORIA = "idcategoria";
    }

    public static abstract class TablaCategoria implements BaseColumns {
        public static final String TABLA = "categoria";
        public static final String NOMBRE = "nombre";
    }

    public static abstract class TablaIngrediente implements BaseColumns {
        public static final String TABLA = "ingrediente";
        public static final String NOMBRE = "nombre";
    }

    public static abstract class TablaRecetaIngrediente implements BaseColumns {
        public static final String TABLA = "recetaingrediente";
        public static final String IDRECETA = "idreceta";
        public static final String IDINGREDIENTE = "idingrediente";
        public static final String CANTIDAD = "cantidad";
    }

}
