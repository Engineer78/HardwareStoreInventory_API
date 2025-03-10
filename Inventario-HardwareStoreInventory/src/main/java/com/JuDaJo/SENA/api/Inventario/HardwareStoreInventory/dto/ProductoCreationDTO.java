package com.JuDaJo.SENA.api.Inventario.HardwareStoreInventory.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

/**
 * DTO para la creación de un producto.
 * Este DTO se utiliza para recibir los datos necesarios para crear un nuevo producto
 * incluyendo información de la categoría y el proveedor.
 */

public class ProductoCreationDTO {

    @NotNull(message = "El producto es obligatorio")
    @Valid
    private ProductoDTO producto;

    @NotNull(message = "El ID de la categoría es obligatorio")
    private Integer idCategoria;

    @NotNull(message = "El ID del proveedor es obligatorio")
    private Integer idProveedor;

    // Constructor vacío para serialización/deserialización
    public ProductoCreationDTO() {}

    // Getters y Setters
    public ProductoDTO getProducto() {
        return producto;
    }

    public void setProducto(ProductoDTO producto) {
        this.producto = producto;
    }

    public Integer getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Integer idCategoria) {
        this.idCategoria = idCategoria;
    }

    public Integer getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(Integer idProveedor) {
        this.idProveedor = idProveedor;
    }
}
