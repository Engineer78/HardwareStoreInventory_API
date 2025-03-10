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

    /**
     * Crea un nuevo producto en el inventario.
     *
     * @param productCreationDTO DTO con los datos necesarios para crear el producto.
     * @param bindingResult Resultado de la validación de los datos enviados.
     * @return Producto creado en formato DTO o detalles del error.
     */
    @PostMapping
    @Transactional
    public ResponseEntity<?> addProducto(@Valid @RequestBody ProductoCreationDTO productCreationDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            ValidationErrorDTO errorDTO = new ValidationErrorDTO(bindingResult.getAllErrors());
            return ResponseEntity.badRequest().body(errorDTO);
        }

        try {
            Categoria categoria = categoriaRepository.findById(productCreationDTO.getIdCategoria())
                    .orElseThrow(() -> new IllegalArgumentException("La categoría con ID " + productCreationDTO.getIdCategoria() + " no existe."));

            Proveedor proveedor = proveedorRepository.findById(productCreationDTO.getIdProveedor())
                    .orElseThrow(() -> new IllegalArgumentException("El proveedor con ID " + productCreationDTO.getIdProveedor() + " no existe."));

            Producto nuevoProducto = new Producto();
            ProductoDTO productoDTO = productCreationDTO.getProducto();
            nuevoProducto.setCodigoProducto(productoDTO.getCodigoProducto());
            nuevoProducto.setNombreProducto(productoDTO.getNombreProducto());
            nuevoProducto.setCantidad(productoDTO.getCantidad());
            nuevoProducto.setValorUnitarioProducto(productoDTO.getValorUnitarioProducto());
            nuevoProducto.setCategoria(categoria);

            ProductoProveedor productoProveedor = new ProductoProveedor();
            productoProveedor.setProducto(nuevoProducto);
            productoProveedor.setProveedor(proveedor);
            productoProveedor.setPrecioCompra(productoDTO.getValorUnitarioProducto());
            nuevoProducto.setProductoProveedores(List.of(productoProveedor));

            productoRepository.save(nuevoProducto);

            return new ResponseEntity<>(new ProductoDTO(nuevoProducto), HttpStatus.CREATED);

        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al crear el producto: " + e.getMessage());
        }
    }

    // Actualizar un producto existente
    @PutMapping("/{idProducto}")
    @Transactional
    public ResponseEntity<?> updateProducto(
            @PathVariable Integer idProducto,
            @Valid @RequestBody Producto productoActualizado,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            ValidationErrorDTO errorDTO = new ValidationErrorDTO(bindingResult.getAllErrors());
            return ResponseEntity.badRequest().body(errorDTO);
        }

        try {
            Producto productoExistente = productoRepository.findById(idProducto)
                    .orElseThrow(() -> new IllegalArgumentException("El producto con ID " + idProducto + " no existe."));

            productoExistente.setCodigoProducto(productoActualizado.getCodigoProducto());
            productoExistente.setNombreProducto(productoActualizado.getNombreProducto());
            productoExistente.setCantidad(productoActualizado.getCantidad());
            productoExistente.setValorUnitarioProducto(productoActualizado.getValorUnitarioProducto());

            Categoria nuevaCategoria = categoriaRepository.findById(productoActualizado.getCategoria().getIdCategoria())
                    .orElseThrow(() -> new IllegalArgumentException("La categoría con ID " + productoActualizado.getCategoria().getIdCategoria() + " no existe."));
            productoExistente.setCategoria(nuevaCategoria);

            List<ProductoProveedor> relacionesExistentes = new ArrayList<>(productoExistente.getProductoProveedores());
            relacionesExistentes.forEach(relacion -> productoExistente.eliminarProductoProveedor(relacion));

            productoActualizado.getProductoProveedores().stream()
                    .distinct()
                    .forEach(nuevaRelacion -> {
                        Proveedor proveedor = proveedorRepository.findById(nuevaRelacion.getProveedor().getIdProveedor())
                                .orElseThrow(() -> new IllegalArgumentException(
                                        "El proveedor con ID " + nuevaRelacion.getProveedor().getIdProveedor() + " no existe."));

                        ProductoProveedor productoProveedor = new ProductoProveedor();
                        productoProveedor.setProducto(productoExistente);
                        productoProveedor.setProveedor(proveedor);
                        productoProveedor.setPrecioCompra(nuevaRelacion.getPrecioCompra());

                        productoExistente.agregarProductoProveedor(productoProveedor);
                    });

            productoRepository.save(productoExistente);
            return ResponseEntity.ok(new ProductoDTO(productoExistente));

        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al actualizar el producto: " + e.getMessage());
        }
    }

    // Eliminar un producto existente
    @DeleteMapping("/{idProducto}")
    public ResponseEntity<Void> deleteProducto(@PathVariable Integer idProducto) {
        Optional<Producto> productoOptional = productoRepository.findById(idProducto);

        if (productoOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Producto producto = productoOptional.get();
        if (producto.getProductoProveedores() != null) {
            List<ProductoProveedor> copiaProductoProveedores = new ArrayList<>(producto.getProductoProveedores());
            for (ProductoProveedor productoProveedor : copiaProductoProveedores) {
                if (productoProveedor.getProveedor() != null) {
                    productoProveedor.getProveedor().getProductoProveedores().remove(productoProveedor);
                }
                producto.eliminarProductoProveedor(productoProveedor);
                productoProveedorRepository.delete(productoProveedor);
            }
        }

        productoRepository.delete(producto);

        return ResponseEntity.noContent().build();
    }


}
