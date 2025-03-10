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
}
