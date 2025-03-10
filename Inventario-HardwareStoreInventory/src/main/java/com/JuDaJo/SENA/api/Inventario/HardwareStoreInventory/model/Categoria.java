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

    /**
     * Obtiene el identificador de la categoría.
     *
     * @return Identificador de la categoría.
     */
    public Integer getIdCategoria() {
        return idCategoria;
    }

    /**
     * Establece el identificador de la categoría.
     *
     * @param idCategoria Identificador de la categoría.
     */
    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    /**
     * Obtiene el nombre de la categoría.
     *
     * @return Nombre de la categoría.
     */
    public String getNombreCategoria() {
        return nombreCategoria;
    }

    /**
     * Establece el nombre de la categoría.
     *
     * @param nombreCategoria Nombre de la categoría.
     */
    public void setNombreCategoria(String nombreCategoria) {
        this.nombreCategoria = nombreCategoria;
    }

    /**
     * Obtiene la lista de productos asociados a esta categoría.
     *
     * @return Lista de productos.
     */
    public List<Producto> getProductos() {
        return productos;
    }

    /**
     * Establece la lista de productos asociados a esta categoría.
     *
     * @param productos Lista de productos.
     */
    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }

    /**
     * Agrega un producto a la lista de productos asociados a esta categoría.
     *
     * @param producto Producto a agregar.
     */
    public void agregarProducto(Producto producto) {
        this.productos.add(producto);
        producto.setCategoria(this); // Relación bidireccional
    }

    /**
     * Elimina un producto de la lista de productos asociados a esta categoría.
     *
     * @param producto Producto a eliminar.
     */
    public void eliminarProducto(Producto producto) {
        this.productos.remove(producto);
        producto.setCategoria(null); // Rompe la relación bidireccional
    }

}
