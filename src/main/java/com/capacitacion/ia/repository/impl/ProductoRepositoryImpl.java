package com.capacitacion.ia.repository.impl;

import com.capacitacion.ia.model.Producto;
import com.capacitacion.ia.repository.ProductoRepository;
import org.springframework.stereotype.Repository;

import java.util.*;

/**
 * Implementación mock del repositorio de Productos.
 * Simula una base de datos usando un HashMap con datos precargados.
 * Capa: Repository
 */
@Repository
public class ProductoRepositoryImpl implements ProductoRepository {

    // Simula la "base de datos" en memoria
    private final Map<String, Producto> baseDeDatos = new HashMap<>();

    public ProductoRepositoryImpl() {
        // Datos mockeados precargados
        baseDeDatos.put("PROD-001", new Producto("PROD-001", "Laptop Dell XPS 15"));
        baseDeDatos.put("PROD-002", new Producto("PROD-002", "Monitor Samsung 27 pulgadas"));
        baseDeDatos.put("PROD-003", new Producto("PROD-003", "Teclado mecánico Logitech MX Keys"));
        baseDeDatos.put("PROD-004", new Producto("PROD-004", "Mouse inalámbrico Microsoft Arc"));
        baseDeDatos.put("PROD-005", new Producto("PROD-005", "Auriculares Sony WH-1000XM5"));
    }

    @Override
    public List<Producto> findAll() {
        return new ArrayList<>(baseDeDatos.values());
    }

    @Override
    public Optional<Producto> findById(String id) {
        return Optional.ofNullable(baseDeDatos.get(id));
    }

    @Override
    public Producto save(Producto producto) {
        String nuevoId = "PROD-" + String.format("%03d", baseDeDatos.size() + 1);
        // Garantiza que el id generado no colisione con uno existente
        while (baseDeDatos.containsKey(nuevoId)) {
            nuevoId = "PROD-" + UUID.randomUUID().toString().substring(0, 6).toUpperCase();
        }
        producto.setId(nuevoId);
        baseDeDatos.put(nuevoId, producto);
        return producto;
    }

    @Override
    public Optional<Producto> update(String id, Producto producto) {
        if (!baseDeDatos.containsKey(id)) {
            return Optional.empty();
        }
        producto.setId(id);
        baseDeDatos.put(id, producto);
        return Optional.of(producto);
    }

    @Override
    public boolean deleteById(String id) {
        return baseDeDatos.remove(id) != null;
    }
}

