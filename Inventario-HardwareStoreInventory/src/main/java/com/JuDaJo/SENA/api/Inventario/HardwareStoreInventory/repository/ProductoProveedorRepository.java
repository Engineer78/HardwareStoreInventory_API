package com.JuDaJo.SENA.api.Inventario.HardwareStoreInventory.repository;

import com.JuDaJo.SENA.api.Inventario.HardwareStoreInventory.model.ProductoProveedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface ProductoProveedorRepository extends JpaRepository<ProductoProveedor, Integer> {
    // Ajustando los nombres según Producto.idProducto
    Optional<ProductoProveedor>
    findByProductoIdProductoAndProveedorIdProveedor(Integer idProducto, Integer idProveedor);
}
