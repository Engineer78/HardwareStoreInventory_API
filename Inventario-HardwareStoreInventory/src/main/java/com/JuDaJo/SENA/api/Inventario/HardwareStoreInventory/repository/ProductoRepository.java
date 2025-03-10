package com.JuDaJo.SENA.api.Inventario.HardwareStoreInventory.repository;

import com.JuDaJo.SENA.api.Inventario.HardwareStoreInventory.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repositorio para la entidad Producto.
 * Este repositorio proporciona operaciones CRUD básicas para la entidad Producto
 * utilizando Spring Data JPA.
 */
@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer> {
}
