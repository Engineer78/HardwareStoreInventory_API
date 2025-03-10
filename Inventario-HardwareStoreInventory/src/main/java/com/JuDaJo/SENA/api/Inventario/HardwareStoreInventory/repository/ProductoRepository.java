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
    // No se necesitan métodos personalizados por ahora, ya que JpaRepository
    // proporciona los métodos básicos (save, findById, findAll, delete, etc.).

    /**
     * Busca un producto por su código.
     *
     * @param codigoProducto El código del producto.
     * @return Un Optional que contiene el producto encontrado, o un Optional vacío si no se encuentra.
     */
    Optional<Producto> findByCodigoProducto(Integer codigoProducto);
}
