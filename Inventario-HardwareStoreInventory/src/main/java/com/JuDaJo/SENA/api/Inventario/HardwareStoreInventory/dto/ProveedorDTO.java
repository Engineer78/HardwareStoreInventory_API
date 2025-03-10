package com.JuDaJo.SENA.api.Inventario.HardwareStoreInventory.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * DTO para gestionar la información de un proveedor.
 */
public class ProveedorDTO {

    private int idProveedor;

    @NotBlank(message = "El nombre del proveedor es obligatorio")
    @Size(max = 100, message = "El nombre no puede exceder los 100 caracteres")
    private String nombreProveedor;

    @NotBlank(message = "El NIT del proveedor es obligatorio")
    @Size(max = 20, message = "El NIT no puede exceder los 20 caracteres")
    private String nitProveedor;

    @Size(max = 15, message = "El teléfono no puede superar los 15 caracteres")
    private String telefonoProveedor;

    @Size(max = 255, message = "La dirección no puede superar los 255 caracteres")
    private String direccionProveedor;

    // Constructor vacío
    public ProveedorDTO() {}

    // Constructor con todos los atributos
    public ProveedorDTO(int idProveedor, String nombreProveedor, String nitProveedor, String telefonoProveedor, String direccionProveedor) {
        this.idProveedor = idProveedor;
        this.nombreProveedor = nombreProveedor;
        this.nitProveedor = nitProveedor;
        this.telefonoProveedor = telefonoProveedor;
        this.direccionProveedor = direccionProveedor;
    }
    

}
