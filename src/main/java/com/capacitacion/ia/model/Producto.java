package com.capacitacion.ia.model;

import java.math.BigDecimal;

/**
 * Entidad de dominio Producto.
 * Capa: Model
 */
public class Producto {

    private String id;
    private String descripcion;
    private BigDecimal precio;
    private Long cantidad;
    private String codigo;

    public Producto() {}

    public Producto(String id, String descripcion, BigDecimal precio, Long cantidad, String codigo) {
        this.id = id;
        this.descripcion = descripcion;
        this.precio = precio;
        this.cantidad = cantidad;
        this.codigo = codigo;
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

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public Long getCantidad() {
        return cantidad;
    }

    public void setCantidad(Long cantidad) {
        this.cantidad = cantidad;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    @Override
    public String toString() {
        return "Producto{id='" + id + "', descripcion='" + descripcion + "', precio=" + precio
                + ", cantidad=" + cantidad + ", codigo='" + codigo + "'}";
    }
}

