package com.JuDaJo.SENA.api.Inventario.HardwareStoreInventory.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

/**
 * Entidad que representa una categoría de productos.
 */
@Entity
public class Categoria {

    /**
     * Identificador único de la categoría.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCategoria;

    /**
     * Nombre de la categoría.
     */
    @NotBlank(message = "El nombre de la categoría no puede estar en blanco")
    @Size(max = 100, message = "El nombre de la categoría no puede exceder los 100 caracteres")
    private String nombreCategoria;

    /**
     * Lista de productos asociados a la categoría.
     */
    @OneToMany(mappedBy = "categoria", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Producto> productos = new ArrayList<>();

    /**
     * Constructor vacío requerido por JPA.
     */
    public Categoria() {
    }

    /**
     * Constructor con parámetros para inicializar una categoría.
     *
     * @param nombreCategoria Nombre de la categoría.
     */
    public Categoria(String nombreCategoria) {
        this.nombreCategoria = nombreCategoria;
    }
}
