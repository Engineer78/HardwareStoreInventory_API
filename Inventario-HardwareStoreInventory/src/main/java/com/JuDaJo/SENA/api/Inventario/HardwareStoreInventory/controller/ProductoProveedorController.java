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

}
