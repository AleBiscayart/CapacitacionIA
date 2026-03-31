package com.capacitacion.ia.model;

/**
 * Entidad de dominio Producto.
 * Capa: Model
 */
public class Producto {

    private String id;
    private String descripcion;

    public Producto() {}

    public Producto(String id, String descripcion) {
        this.id = id;
        this.descripcion = descripcion;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "Producto{id='" + id + "', descripcion='" + descripcion + "'}";
    }
}

