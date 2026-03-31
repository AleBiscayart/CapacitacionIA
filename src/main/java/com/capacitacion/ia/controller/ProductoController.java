package com.capacitacion.ia.controller;

import com.capacitacion.ia.dto.ProductoRequestDTO;
import com.capacitacion.ia.dto.ProductoResponseDTO;
import com.capacitacion.ia.service.ProductoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Productos", description = "Operaciones CRUD sobre la entidad Producto")
public class ProductoController {

    private final ProductoService productoService;

    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @Operation(summary = "Listar todos los productos", description = "Retorna la lista completa de productos registrados.")
    @ApiResponse(responseCode = "200", description = "Lista de productos obtenida exitosamente")
    @GetMapping
    public ResponseEntity<List<ProductoResponseDTO>> getAllProductos() {
        List<ProductoResponseDTO> productos = productoService.getAllProductos();
        return ResponseEntity.ok(productos);
    }

    @Operation(summary = "Obtener producto por ID", description = "Retorna un producto específico según su identificador.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Producto encontrado"),
        @ApiResponse(responseCode = "404", description = "Producto no encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<ProductoResponseDTO> getProductoById(
            @Parameter(description = "ID del producto", required = true) @PathVariable String id) {
        ProductoResponseDTO producto = productoService.getProductoById(id);
        return ResponseEntity.ok(producto);
    }

    @Operation(summary = "Crear un nuevo producto", description = "Registra un nuevo producto en el sistema.")
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Producto creado exitosamente"),
        @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos")
    })
    @PostMapping
    public ResponseEntity<ProductoResponseDTO> createProducto(
            @Valid @RequestBody ProductoRequestDTO request) {
        ProductoResponseDTO creado = productoService.createProducto(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    @Operation(summary = "Actualizar un producto", description = "Actualiza los datos de un producto existente.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Producto actualizado exitosamente"),
        @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos"),
        @ApiResponse(responseCode = "404", description = "Producto no encontrado")
    })
    @PutMapping("/{id}")
    public ResponseEntity<ProductoResponseDTO> updateProducto(
            @Parameter(description = "ID del producto a actualizar", required = true) @PathVariable String id,
            @Valid @RequestBody ProductoRequestDTO request) {
        ProductoResponseDTO actualizado = productoService.updateProducto(id, request);
        return ResponseEntity.ok(actualizado);
    }

    @Operation(summary = "Eliminar un producto", description = "Elimina un producto del sistema por su ID.")
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "Producto eliminado exitosamente"),
        @ApiResponse(responseCode = "404", description = "Producto no encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProducto(
            @Parameter(description = "ID del producto a eliminar", required = true) @PathVariable String id) {
        productoService.deleteProducto(id);
        return ResponseEntity.noContent().build();
    }
}
