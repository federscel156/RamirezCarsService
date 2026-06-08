# 🚗 RamirezCarsService
**Sistema Desktop para la Gestión Técnica y Trazabilidad del Historial Clínico Automotriz**

Este proyecto es un prototipo operacional desarrollado íntegramente en **Java nativo**. Su objetivo es digitalizar y automatizar los procesos diarios de un taller mecánico: desde la recepción del vehículo y asignación de turnos, hasta la gestión operativa de las Órdenes de Trabajo (OT), el registro de insumos y la emisión automatizada de Fichas de Servicio en formato PDF.

El desarrollo destaca por la aplicación estricta del paradigma de **Programación Orientada a Objetos (POO)** —incluyendo herencia y polimorfismo— y el uso del patrón de arquitectura **MVC (Modelo-Vista-Controlador)** sin la dependencia de frameworks externos.

---

## 📁 Estructura del Repositorio

El repositorio está organizado en dos grandes directorios para separar la lógica de la aplicación de la infraestructura de la base de datos:

### 1. Carpeta `codigo/` (El Sistema Java)
Contiene el código fuente del proyecto y sus dependencias, estructurado bajo el patrón MVC:
* **`models/`**: Entidades de dominio puro (Ej: `Persona`, `Cliente`, `Mecanico`) aplicando los pilares de la POO.
* **`views/`**: Interfaces gráficas de usuario interactivas y dinámicas, construidas nativamente con **Java Swing**.
* **`controllers/`**: Orquestadores transaccionales que comunican la vista con el acceso a datos aplicando reglas de negocio.
* **`dao/`**: Clases *Data Access Object* encargadas de la persistencia física en MySQL utilizando **JDBC puro**.
* **`libs/`**: Librerías de terceros necesarias para la ejecución (`mysql-connector-j` para la BD e `itextpdf` para emitir reportes físicos).

### 2. Carpeta `sql/` (Base de Datos MySQL)
Contiene todo lo necesario para inicializar el motor relacional (diseñado en 3FN):
* **`crearDb.sql`**: Script principal DDL/DML. Crea la base de datos `RamirezCarsService_DB`, estructura todas las tablas con sus respectivas claves foráneas (Integridad Referencial) y carga datos iniciales de prueba.
* *(Otros scripts auxiliares para limpiar o reiniciar las tablas en entornos de testeo).*


### 3. Carpeta `bin/` (compilados y jar ejecutable)
Contiene todos los .class y el jar RamirezCarsService.jar ejecutable. 

---
## ⚙️ Tecnologías Implementadas

* **Lenguaje:** Java SE
* **Interfaz de Usuario:** Java Swing
* **Base de Datos:** MySQL
* **Reportes:** iTextPDF
* **Arquitectura:** MVC, DAO

---

## 🚀 Guía Rápida de Ejecución

Para poder correr este sistema en un entorno local:

1. **Preparar la Base de Datos:**
   * Inicie su servidor MySQL local (ej. a través de XAMPP o Workbench).
   * Ejecute el archivo `sql/crearDb.sql` para instanciar el esquema y las tablas obligatorias.
2. **Preparar el Entorno Java:**
   * Abra la carpeta `codigo/` en su IDE de preferencia (Visual Studio Code, Eclipse, IntelliJ).
   * Asegúrese de tener agregados los archivos `.jar` de la carpeta `libs/` al *Classpath* de su proyecto.
3. **Ejecutar:**
   * Inicie la aplicación compilando y ejecutando el archivo principal: `codigo/views/DashboardPrincipal.java`.

---
*Desarrollado para la materia Seminario de Práctica de Informática - Universidad Siglo 21.*
