package com.capacitacion.ia.dto;

/**
 * DTO de salida con los datos de un Producto.
 * Capa: DTO
 */
public class ProductoResponseDTO {

    private String id;
    private String descripcion;

    public ProductoResponseDTO() {}

    public ProductoResponseDTO(String id, String descripcion) {
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
}

