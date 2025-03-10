Hardware Store Inventory API

Este proyecto consiste en la creación de una API REST utilizando Spring Boot, desarrollada como parte del programa Hardware Store Inventory FFIG, específicamente para gestionar el módulo de inventario de una ferretería.


📋 Descripción
La API ofrece funcionalidades para:

Gestionar proveedores, categorías, productos y las relaciones entre ellos.

Realizar operaciones CRUD (Crear, Leer, Actualizar, Eliminar) sobre los recursos de inventario.

Estandarizar y optimizar la gestión de inventario mediante servicios REST.

Este proyecto fue diseñado como un trabajo formativo y colaborativo en el marco del SENA Centro de Comercio y Turismo de Armenia - Regional Quindío.


🌟 Estructura del Proyecto
El desarrollo se dividió en tres ramas principales trabajadas por el equipo de desarrollo:

develop1: Joaquín H. Jiménez Rosas (Ficha 2879723)

develop2: Juan David Gallego López (Ficha 2879723)

develop3: David Ricardo Graffe Rodríguez (Ficha 2879724)

Todas las ramas fueron finalmente integradas en la rama principal (main), asegurando la cohesión y funcionalidad del proyecto.


📚 Propósito Formativo
Este proyecto tiene como objetivo formar habilidades en:

Diseño y desarrollo de APIs REST utilizando Spring Boot.

Gestión colaborativa del código mediante Git y GitHub.

Aplicación de conceptos básicos de arquitectura y gestión de proyectos software.

Instructora a cargo: Diana María Valencia Rebellón, del SENA Centro de Comercio y Turismo - Regional Quindío.


🛠️ Tecnologías Utilizadas
Lenguaje de programación: Java

Framework: Spring Boot

Gestión de dependencias: Maven

Base de datos: MySQL

Control de versiones: Git / GitHub

🚀 Configuración e Instalación
Sigue estos pasos para ejecutar el proyecto en tu entorno local:

Clona este repositorio:

bash
git clone https://github.com/tu-usuario/hardware-store-inventory-api.git
cd hardware-store-inventory-api
Configura la base de datos en el archivo application.properties:

properties
spring.datasource.url=jdbc:mysql://localhost:3306/inventario_api
spring.datasource.username=TU_USUARIO
spring.datasource.password=TU_CONTRASEÑA
Ejecuta el proyecto:

bash
mvn spring-boot:run
Accede a la API en: http://localhost:8080.


👥 Contribuidores
Joaquín H. Jiménez Rosas - develop1 (Ficha 2879723)

Juan David Gallego López - develop2 (Ficha 2879723)

David Ricardo Graffe Rodríguez - develop3 (Ficha 2879724)


📝 Licencia
Este proyecto es formativo y no posee una licencia de distribución. Su uso es exclusivo para fines educativos dentro del SENA.
