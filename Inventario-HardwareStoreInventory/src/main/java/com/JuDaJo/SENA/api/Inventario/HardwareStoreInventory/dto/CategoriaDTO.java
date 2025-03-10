package com.JuDaJo.SENA.api.Inventario.HardwareStoreInventory.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CategoriaDTO {

    private Integer idCategoria;

    // Validación condicional: Solo requerido si idCategoria es nulo
    @NotBlank(message = "El nombre de la categoría es obligatorio")
    @Size(max = 100, message = "El nombre de la categoría no puede exceder los 100 caracteres")
    private String nombreCategoria;
}
