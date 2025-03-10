package com.JuDaJo.SENA.api.Inventario.HardwareStoreInventory.repository;

import com.JuDaJo.SENA.api.Inventario.HardwareStoreInventory.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio para la entidad Categoria.
 * * Este repositorio proporciona operaciones CRUD básicas para la entidad Categoria
 * utilizando Spring Data JPA.
 */
@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {
}
