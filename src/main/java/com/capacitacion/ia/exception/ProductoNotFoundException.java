package com.capacitacion.ia.exception;

/**
 * Excepción lanzada cuando no se encuentra un Producto por su id.
 * Capa: Exception
 */
public class ProductoNotFoundException extends RuntimeException {

    public ProductoNotFoundException(String id) {
        super("Producto no encontrado con id: " + id);
    }
}

