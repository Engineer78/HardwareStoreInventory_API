package com.JuDaJo.SENA.api.Inventario.HardwareStoreInventory.controller;

import com.JuDaJo.SENA.api.Inventario.HardwareStoreInventory.dto.ProveedorDTO;
import com.JuDaJo.SENA.api.Inventario.HardwareStoreInventory.model.Proveedor;
import com.JuDaJo.SENA.api.Inventario.HardwareStoreInventory.repository.ProveedorRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/proveedores")
public class ProveedorController {

    private final ProveedorRepository proveedorRepository;

    public ProveedorController(ProveedorRepository proveedorRepository) {
        this.proveedorRepository = proveedorRepository;
    }

    // Obtener todos los proveedores
    @GetMapping
    public ResponseEntity<List<ProveedorDTO>> listarProveedores() {
        List<ProveedorDTO> proveedores = proveedorRepository.findAll().stream()
                .map(proveedor -> new ProveedorDTO(
                        proveedor.getIdProveedor(),
                        proveedor.getNombreProveedor(),
                        proveedor.getNitProveedor(),
                        proveedor.getTelefonoProveedor(),
                        proveedor.getDireccionProveedor()))
                .collect(Collectors.toList());
        return ResponseEntity.ok(proveedores);
    }

}
