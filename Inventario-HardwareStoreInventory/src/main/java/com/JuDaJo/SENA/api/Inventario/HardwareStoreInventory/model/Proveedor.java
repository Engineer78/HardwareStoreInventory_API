package com.JuDaJo.SENA.api.Inventario.HardwareStoreInventory.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Entidad que representa un proveedor de productos.
 */
@Entity
public class Proveedor {

    /**
     * Identificador único del proveedor.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idProveedor;
}
