package com.JuDaJo.SENA.api.Inventario.HardwareStoreInventory.model;
import jakarta.persistence.*;
import java.util.Objects;

/**
 * Clase que representa la relación entre un producto y un proveedor.
 * Modela una asociación entre un producto y el proveedor que lo suministra, junto con el precio de compra.
 */
@Entity
public class ProductoProveedor {
    /**
     * Identificador único de la relación Producto-Proveedor.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idProductoProveedor;
    /**
     * Relación con el producto asociado.
     * Utiliza una clave foránea que referencia el ID del producto.
     */
    @ManyToOne
    @JoinColumn(name = "id_producto", nullable = false)
    private Producto producto;

}
