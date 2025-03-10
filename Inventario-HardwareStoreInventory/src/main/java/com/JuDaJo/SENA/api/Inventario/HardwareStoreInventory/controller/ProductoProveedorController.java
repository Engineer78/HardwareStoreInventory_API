package com.JuDaJo.SENA.api.Inventario.HardwareStoreInventory.controller;

import com.JuDaJo.SENA.api.Inventario.HardwareStoreInventory.dto.ProductoProveedorDTO;
import com.JuDaJo.SENA.api.Inventario.HardwareStoreInventory.model.Producto;
import com.JuDaJo.SENA.api.Inventario.HardwareStoreInventory.model.ProductoProveedor;
import com.JuDaJo.SENA.api.Inventario.HardwareStoreInventory.model.Proveedor;
import com.JuDaJo.SENA.api.Inventario.HardwareStoreInventory.repository.ProductoProveedorRepository;
import com.JuDaJo.SENA.api.Inventario.HardwareStoreInventory.repository.ProductoRepository;
import com.JuDaJo.SENA.api.Inventario.HardwareStoreInventory.repository.ProveedorRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Controlador REST para gestionar las relaciones entre productos y proveedores.
 * Proporciona los puntos de entrada necesarios para operaciones relacionadas con ProductoProveedor.
 */
@RestController
@RequestMapping("/api/producto-proveedor") // Define el endpoint base para las rutas relacionadas con ProductoProveedor
public class ProductoProveedorController {
    // Repositorio para acceder y gestionar las relaciones entre productos y proveedores en la base de datos
    private final ProductoProveedorRepository productoProveedorRepository;

    // Repositorio para gestionar las operaciones relacionadas con productos
    private final ProductoRepository productoRepository;

    // Repositorio para gestionar las operaciones relacionadas con proveedores
    private final ProveedorRepository proveedorRepository;
    /**
     * Constructor para inicializar los repositorios necesarios.
     *
     * @param productoProveedorRepository Repositorio para gestionar ProductoProveedor.
     * @param productoRepository Repositorio para gestionar los productos.
     * @param proveedorRepository Repositorio para gestionar los proveedores.
     */
    public ProductoProveedorController(ProductoProveedorRepository productoProveedorRepository,
                                       ProductoRepository productoRepository,
                                       ProveedorRepository proveedorRepository) {
        this.productoProveedorRepository = productoProveedorRepository;
        this.productoRepository = productoRepository;
        this.proveedorRepository = proveedorRepository;
    }
    public ResponseEntity<List<ProductoProveedorDTO>> listarProductoProveedor() {
        List<ProductoProveedorDTO> lista = productoProveedorRepository.findAll()
                .stream()
                .map(pp -> new ProductoProveedorDTO(
                        pp.getIdProductoProveedor(), // Usamos idProductoProveedor según tu solicitud
                        pp.getProducto().getIdProducto(),
                        pp.getProveedor().getIdProveedor(),
                        pp.getPrecioCompra()))
                .collect(Collectors.toList());
        return ResponseEntity.ok(lista);
    }
    // Crear una nueva relación producto-proveedor
    @PostMapping
    public ResponseEntity<ProductoProveedorDTO> crearProductoProveedor(@Valid @RequestBody ProductoProveedorDTO dto) {
        // Validar la existencia del producto y proveedor
        Optional<Producto> productoOpt = productoRepository.findById(dto.getIdProducto());
        Optional<Proveedor> proveedorOpt = proveedorRepository.findById(dto.getIdProveedor());

        if (productoOpt.isEmpty()) {
            return ResponseEntity.badRequest().body(null); // Producto no encontrado
        }
        if (proveedorOpt.isEmpty()) {
            return ResponseEntity.badRequest().body(null); // Proveedor no encontrado
        }

        ProductoProveedor productoProveedor = new ProductoProveedor();
        productoProveedor.setProducto(productoOpt.get());
        productoProveedor.setProveedor(proveedorOpt.get());
        productoProveedor.setPrecioCompra(dto.getPrecioCompra());

        ProductoProveedor nuevoProductoProveedor = productoProveedorRepository.save(productoProveedor);

        ProductoProveedorDTO nuevoDTO = new ProductoProveedorDTO(
                nuevoProductoProveedor.getIdProductoProveedor(), // Usamos idProductoProveedor según tu solicitud
                nuevoProductoProveedor.getProducto().getIdProducto(),
                nuevoProductoProveedor.getProveedor().getIdProveedor(),
                nuevoProductoProveedor.getPrecioCompra());

        return new ResponseEntity<>(nuevoDTO, HttpStatus.CREATED);
    }

}
