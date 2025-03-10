-- Crear la base de datos
CREATE DATABASE inventario_api;

-- Usar la base de datos
USE inventario_api;

-- Tabla proveedor
CREATE TABLE proveedor (
    id_proveedor INT AUTO_INCREMENT PRIMARY KEY,
    direccion_proveedor VARCHAR(255) NOT NULL,
    nit_proveedor VARCHAR(50) UNIQUE NOT NULL,
    nombre_proveedor VARCHAR(100) NOT NULL,
    telefono_proveedor VARCHAR(15) NOT NULL
);

-- Tabla categoria
CREATE TABLE categoria (
    id_categoria INT AUTO_INCREMENT PRIMARY KEY,
    nombre_categoria VARCHAR(100) NOT NULL
);

-- Tabla producto
CREATE TABLE producto (
    id_producto INT AUTO_INCREMENT PRIMARY KEY,
    cantidad INT NOT NULL,
    codigo_producto VARCHAR(50) UNIQUE NOT NULL,
    nombre_producto VARCHAR(100) NOT NULL,
    valor_unitario_producto DECIMAL(10, 2) NOT NULL,
    categoria_id INT,
    FOREIGN KEY (categoria_id) REFERENCES categoria(id_categoria)
);

-- Tabla producto_proveedor
CREATE TABLE producto_proveedor (
    id_producto_proveedor INT AUTO_INCREMENT PRIMARY KEY,
    precio_compra DECIMAL(10, 2) NOT NULL,
    id_producto INT NOT NULL,
    id_proveedor INT NOT NULL,
    FOREIGN KEY (id_producto) REFERENCES producto(id_producto),
    FOREIGN KEY (id_proveedor) REFERENCES proveedor(id_proveedor)
);
