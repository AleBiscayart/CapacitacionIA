package com.capacitacion.ia.service;

import com.capacitacion.ia.dto.ProductoRequestDTO;
import com.capacitacion.ia.dto.ProductoResponseDTO;

import java.util.List;

/**
 * Contrato del servicio de Productos.
 * Capa: Service
 */
public interface ProductoService {

    List<ProductoResponseDTO> getAllProductos();

    ProductoResponseDTO getProductoById(String id);

    ProductoResponseDTO createProducto(ProductoRequestDTO request);

    ProductoResponseDTO updateProducto(String id, ProductoRequestDTO request);

    void deleteProducto(String id);
}

