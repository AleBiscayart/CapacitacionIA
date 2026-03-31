package com.capacitacion.ia.dto;

import jakarta.validation.constraints.NotBlank;

/**
 * DTO de entrada para crear o actualizar un Producto.
 * Capa: DTO
 */
public class ProductoRequestDTO {

    @NotBlank(message = "La descripción no puede estar vacía")
    private String descripcion;

    public ProductoRequestDTO() {}

    public ProductoRequestDTO(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}

