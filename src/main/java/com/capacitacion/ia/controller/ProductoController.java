package com.capacitacion.ia.controller;

import com.capacitacion.ia.dto.ProductoRequestDTO;
import com.capacitacion.ia.dto.ProductoResponseDTO;
import com.capacitacion.ia.service.ProductoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST para el CRUD de Productos.
 * Capa: Controller
 *
 * Base URL: /api/productos
 */
@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    private final ProductoService productoService;

    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    /**
     * GET /api/productos
     * Retorna la lista completa de productos.
     */
    @GetMapping
    public ResponseEntity<List<ProductoResponseDTO>> getAllProductos() {
        List<ProductoResponseDTO> productos = productoService.getAllProductos();
        return ResponseEntity.ok(productos);
    }

    /**
     * GET /api/productos/{id}
     * Retorna un producto por su id.
     */
    @GetMapping("/{id}")
    public ResponseEntity<ProductoResponseDTO> getProductoById(@PathVariable String id) {
        ProductoResponseDTO producto = productoService.getProductoById(id);
        return ResponseEntity.ok(producto);
    }

    /**
     * POST /api/productos
     * Crea un nuevo producto.
     */
    @PostMapping
    public ResponseEntity<ProductoResponseDTO> createProducto(
            @Valid @RequestBody ProductoRequestDTO request) {
        ProductoResponseDTO creado = productoService.createProducto(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    /**
     * PUT /api/productos/{id}
     * Actualiza la descripción de un producto existente.
     */
    @PutMapping("/{id}")
    public ResponseEntity<ProductoResponseDTO> updateProducto(
            @PathVariable String id,
            @Valid @RequestBody ProductoRequestDTO request) {
        ProductoResponseDTO actualizado = productoService.updateProducto(id, request);
        return ResponseEntity.ok(actualizado);
    }

    /**
     * DELETE /api/productos/{id}
     * Elimina un producto por su id.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProducto(@PathVariable String id) {
        productoService.deleteProducto(id);
        return ResponseEntity.noContent().build();
    }
}

