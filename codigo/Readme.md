# 🚗 Ramirez Cars Service - Sistema de Gestión Automotriz

**Sistema Desktop para la Gestión Técnica y Trazabilidad del Historial Clínico Automotriz**

Este proyecto es un prototipo operacional desarrollado en **Java** para automatizar y gestionar los procesos diarios de un taller mecánico. Permite llevar un registro unificado de clientes y vehículos, agendar turnos, abrir y cerrar Órdenes de Trabajo (OT), registrar consumos e insumos, visualizar métricas operativas y emitir Fichas de Servicio en formato PDF.

---

## 🚀 Características Principales (Módulos)

* **Gestión de Clientes y Vehículos:** Registro unificado respetando la integridad referencial.
* **Agenda de Turnos:** Asignación de fechas y horarios para la recepción de vehículos.
* **Órdenes de Trabajo (OT):** Ciclo de vida completo de la historia clínica (Apertura y Cierre), con validación estricta de reglas de negocio (ej. kilometraje histórico).
* **Registro de Tareas e Insumos:** Interfaz dinámica para vincular el trabajo realizado a una OT activa.
* **Inteligencia Operativa (Dashboard):** Generación de reportes estadísticos consolidados del taller.
* **Emisión de PDF:** Generación automática de la Ficha Técnica de Servicio al finalizar una Orden de Trabajo.

---

## 🛠️ Tecnologías y Patrones Utilizados

El sistema fue construido cumpliendo estrictas restricciones académicas (Cero Frameworks), demostrando un dominio profundo del lenguaje y la arquitectura de software:

* **Lenguaje:** Java SE (JDK 8+)
* **Interfaz Gráfica (GUI):** Java Swing nativo.
* **Base de Datos:** MySQL (Diseño en 3FN).
* **Conexión a BD:** JDBC (Java Database Connectivity) puro.
* **Librerías Externas:** `mysql-connector-j` (Driver) y `itextpdf-5.5.13.3` (Generación de PDF).
* **Patrón de Arquitectura:** MVC (Modelo - Vista - Controlador) estricto.
* **Paradigmas POO:** Aplicación práctica de Abstracción, Encapsulamiento, Herencia (clase `Persona` a `Cliente/Mecanico`) y Polimorfismo (método `@Override obtenerDetalleRol()`).

---

## 📁 Arquitectura del Proyecto

El código fuente está rigurosamente separado para respetar el Principio de Responsabilidad Única (SRP):

```text
RamirezCarsService/
├── sql/
│   └── crearDb.sql                      <-- Script DDL/DML para instanciar la BD
├── codigo/
│   ├── controllers/                     <-- Capa Lógica (Orquestadores transaccionales)
│   │   ├── ClienteController.java       
│   │   ├── FichaServicioController.java 
│   │   └── ...
│   ├── dao/                             <-- Acceso a Datos (Persistencia Física SQL)
│   │   ├── ConexionBD.java              
│   │   ├── ClienteDAO.java              
│   │   └── ...
│   ├── models/                          <-- Entidades de Dominio (POO Pura)
│   │   ├── Persona.java                 <-- Clase Abstracta Base
│   │   ├── Cliente.java                 <-- Hereda de Persona
│   │   ├── Mecanico.java                <-- Hereda de Persona (Polimorfismo)
│   │   └── ...
│   ├── views/                           <-- Capa de Presentación (Java Swing)
│   │   ├── DashboardPrincipal.java      <-- Punto de entrada (Main)
│   │   └── ...
│   └── libs/                            <-- Dependencias de terceros (.jar)
