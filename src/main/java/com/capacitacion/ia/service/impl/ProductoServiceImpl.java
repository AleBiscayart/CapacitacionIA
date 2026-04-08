package com.capacitacion.ia.service.impl;

import com.capacitacion.ia.dto.ProductoRequestDTO;
import com.capacitacion.ia.dto.ProductoResponseDTO;
import com.capacitacion.ia.exception.ProductoNotFoundException;
import com.capacitacion.ia.model.Producto;
import com.capacitacion.ia.repository.ProductoRepository;
import com.capacitacion.ia.service.ProductoService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementación del servicio de Productos.
 * Gestiona la lógica de negocio y el mapeo DTO <-> Modelo.
 * Capa: Service
 */
@Service
public class ProductoServiceImpl implements ProductoService {

    private final ProductoRepository productoRepository;

    public ProductoServiceImpl(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    @Override
    public List<ProductoResponseDTO> getAllProductos() {
        return productoRepository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ProductoResponseDTO getProductoById(String id) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new ProductoNotFoundException(id));
        return toResponseDTO(producto);
    }

    @Override
    public ProductoResponseDTO createProducto(ProductoRequestDTO request) {
        Producto nuevoProducto = new Producto();
        nuevoProducto.setDescripcion(request.getDescripcion());
        nuevoProducto.setPrecio(request.getPrecio());
        nuevoProducto.setCantidad(request.getCantidad());
        nuevoProducto.setCodigo(request.getCodigo());
        Producto guardado = productoRepository.save(nuevoProducto);
        return toResponseDTO(guardado);
    }

    @Override
    public ProductoResponseDTO updateProducto(String id, ProductoRequestDTO request) {
        Producto productoActualizado = new Producto();
        productoActualizado.setDescripcion(request.getDescripcion());
        productoActualizado.setPrecio(request.getPrecio());
        productoActualizado.setCantidad(request.getCantidad());
        productoActualizado.setCodigo(request.getCodigo());

        Producto resultado = productoRepository.update(id, productoActualizado)
                .orElseThrow(() -> new ProductoNotFoundException(id));
        return toResponseDTO(resultado);
    }

    @Override
    public void deleteProducto(String id) {
        boolean eliminado = productoRepository.deleteById(id);
        if (!eliminado) {
            throw new ProductoNotFoundException(id);
        }
    }

    // ── Mapeo privado ───────────────────────────────────────────────────────────

    private ProductoResponseDTO toResponseDTO(Producto producto) {
        return new ProductoResponseDTO(
                producto.getId(),
                producto.getDescripcion(),
                producto.getPrecio(),
                producto.getCantidad(),
                producto.getCodigo()
        );
    }

}

