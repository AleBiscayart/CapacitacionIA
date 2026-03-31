package com.capacitacion.ia.repository;

import com.capacitacion.ia.model.Producto;

import java.util.List;
import java.util.Optional;

/**
 * Contrato del repositorio de Productos.
 * Capa: Repository
 */
public interface ProductoRepository {

    List<Producto> findAll();

    Optional<Producto> findById(String id);

    Producto save(Producto producto);

    Optional<Producto> update(String id, Producto producto);

    boolean deleteById(String id);
}

