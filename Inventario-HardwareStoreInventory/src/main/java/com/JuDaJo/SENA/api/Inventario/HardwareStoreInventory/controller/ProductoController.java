package com.JuDaJo.SENA.api.Inventario.HardwareStoreInventory.controller;

import com.JuDaJo.SENA.api.Inventario.HardwareStoreInventory.dto.ProductoCreationDTO;
import com.JuDaJo.SENA.api.Inventario.HardwareStoreInventory.dto.ProductoDTO;
import com.JuDaJo.SENA.api.Inventario.HardwareStoreInventory.dto.ValidationErrorDTO;
import com.JuDaJo.SENA.api.Inventario.HardwareStoreInventory.model.Categoria;
import com.JuDaJo.SENA.api.Inventario.HardwareStoreInventory.model.Producto;
import com.JuDaJo.SENA.api.Inventario.HardwareStoreInventory.model.ProductoProveedor;
import com.JuDaJo.SENA.api.Inventario.HardwareStoreInventory.model.Proveedor;
import com.JuDaJo.SENA.api.Inventario.HardwareStoreInventory.repository.CategoriaRepository;
import com.JuDaJo.SENA.api.Inventario.HardwareStoreInventory.repository.ProductoProveedorRepository;
import com.JuDaJo.SENA.api.Inventario.HardwareStoreInventory.repository.ProductoRepository;
import com.JuDaJo.SENA.api.Inventario.HardwareStoreInventory.repository.ProveedorRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Controlador REST para gestionar los productos en el inventario.
 * Proporciona funcionalidades como crear, actualizar, eliminar y consultar productos.
 */
@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    private final ProductoRepository productoRepository;
    private final ProductoProveedorRepository productoProveedorRepository;
    private final CategoriaRepository categoriaRepository;
    private final ProveedorRepository proveedorRepository;

    /**
     * Constructor para inicializar los repositorios necesarios.
     *
     * @param productoRepository Repositorio para la gestión de productos.
     * @param productoProveedorRepository Repositorio para relaciones Producto-Proveedor.
     * @param categoriaRepository Repositorio para categorías.
     * @param proveedorRepository Repositorio para proveedores.
     */
    public ProductoController(
            ProductoRepository productoRepository,
            ProductoProveedorRepository productoProveedorRepository,
            CategoriaRepository categoriaRepository,
            ProveedorRepository proveedorRepository
    ) {
        this.productoRepository = productoRepository;
        this.productoProveedorRepository = productoProveedorRepository;
        this.categoriaRepository = categoriaRepository;
        this.proveedorRepository = proveedorRepository;
    }

    /**
     * Obtiene todos los productos disponibles en el inventario.
     *
     * @return Lista de productos en formato DTO.
     */
    @GetMapping
    public ResponseEntity<List<ProductoDTO>> getAllProductos() {
        List<Producto> productos = productoRepository.findAll(); // Obtiene todos los productos
        List<ProductoDTO> productosDTO = productos.stream()
                .map(ProductoDTO::new) // Convierte cada Producto en un ProductoDTO
                .collect(Collectors.toList());
        return ResponseEntity.ok(productosDTO);
    }

    /**
     * Obtiene un producto específico por su ID.
     *
     * @param idProducto Identificador único del producto.
     * @return Producto en formato DTO si se encuentra, o código 404 si no existe.
     */
    @GetMapping("/{idProducto}")
    public ResponseEntity<ProductoDTO> getProductoById(@PathVariable Integer idProducto) {
        return productoRepository.findById(idProducto)
                .map(ProductoDTO::new) // Convierte el Producto en un ProductoDTO
                .map(ResponseEntity::ok) // Envuelve el DTO en una respuesta con código 200
                .orElse(ResponseEntity.notFound().build()); // Devuelve 404 si no se encuentra
    }

    /**
     * Obtiene un producto específico por su código.
     *
     * @param codigoProducto Código único del producto.
     * @return Producto en formato DTO si se encuentra, o código 404 si no existe.
     */
    @GetMapping("/codigo/{codigoProducto}")
    public ResponseEntity<ProductoDTO> getProductoByCodigo(@PathVariable Integer codigoProducto) {
        return productoRepository.findByCodigoProducto(codigoProducto) // Método del repositorio
                .map(ProductoDTO::new) // Convierte el Producto en un ProductoDTO
                .map(ResponseEntity::ok) // Envuelve el DTO en una respuesta con código 200
                .orElse(ResponseEntity.notFound().build()); // Devuelve 404 si no se encuentra
    }

    


}
