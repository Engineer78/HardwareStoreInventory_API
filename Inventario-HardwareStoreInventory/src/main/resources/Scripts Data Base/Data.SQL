USE inventario_api;

-- Inserción de 7 registros en la tabla 'proveedor'
INSERT INTO proveedor (direccion_proveedor, nit_proveedor, nombre_proveedor, telefono_proveedor) VALUES
('Calle 10 # 5-20, Bogotá', '900123456-1', 'Aceros S.A.', '3101234567'),
('Carrera 15 # 25-10, Medellín', '890987654-2', 'Pinturas Colombianas', '3159876543'),
('Avenida 30 # 40-30, Cali', '800112233-3', 'Herramientas Eléctricas Ltda.', '3201122334'),
('Diagonal 45 # 55-40, Barranquilla', '910445566-4', 'Tuberías y Conexiones S.A.S.', '3184455667'),
('Transversal 50 # 65-50, Cartagena', '870778899-5', 'Materiales de Construcción del Caribe', '3167788990'),
('Circular 55 # 75-60, Pereira', '920223344-6', 'Ferretería El Tornillo Feliz', '3222233445'),
('Manzana 60 # 85-70, Bucaramanga', '880556677-7', 'Insumos para Ferreterías S.A.', '3195566778');

-- Inserción de 7 registros en la tabla 'categoria'
INSERT INTO categoria (nombre_categoria) VALUES
('Herramientas Manuales'),
('Herramientas Eléctricas'),
('Pinturas y Accesorios'),
('Tuberías y Conexiones'),
('Materiales de Construcción'),
('Ferretería en General'),
('Insumos Eléctricos');

-- Inserción de 7 registros en la tabla 'producto'
INSERT INTO producto (cantidad, codigo_producto, nombre_producto, valor_unitario_producto, categoria_id) VALUES
(100, 1001, 'Martillo de uña', 15.00, 1),
(50, 2002, 'Taladro eléctrico', 80.00, 2),
(200, 3003, 'Pintura blanca galón', 30.00, 3),
(75, 4004, 'Tubo PVC 1/2 pulgada', 5.00, 4),
(30, 5005, 'Cemento saco 50kg', 10.00, 5),
(100, 6006, 'Tornillos varios', 2.00, 6),
(60, 7007, 'Cable eléctrico 12 AWG', 1.50, 7);

-- Inserción de 7 registros en la tabla 'producto_proveedor'
INSERT INTO producto_proveedor (precio_compra, id_producto, id_proveedor) VALUES
(12.00, 1, 1),
(60.00, 2, 2),
(25.00, 3, 3),
(3.00, 4, 4),
(8.00, 5, 5),
(1.50, 6, 6),
(1.00, 7, 7);