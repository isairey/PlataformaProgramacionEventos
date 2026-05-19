<div align="center">

<img width="220" src="https://cdn-icons-png.flaticon.com/512/3082/3082037.png" />

# 🎭 Event Scheduling System (ESS)

### Plataforma inteligente para gestión y programación de eventos 🚀

<p align="center">
  <b>ESS - Event Scheduling System</b> es una solución empresarial desarrollada para administrar activos, reservas, facturación y programación de equipos para eventos de gran escala.
</p>

<p align="center">
  <img src="https://img.shields.io/badge/EventScheduling-System-blueviolet?style=for-the-badge">
  <img src="https://img.shields.io/badge/Java-Desktop_App-ED8B00?style=for-the-badge&logo=java&logoColor=white">
  <img src="https://img.shields.io/badge/MySQL-Database-4479A1?style=for-the-badge&logo=mysql&logoColor=white">
  <img src="https://img.shields.io/badge/ClientServer-Architecture-success?style=for-the-badge">
</p>

<p align="center">
  <a href="#-acerca-del-proyecto">Acerca</a> •
  <a href="#-módulos-del-sistema">Módulos</a> •
  <a href="#-características">Características</a> •
  <a href="#-tecnologías-utilizadas">Tecnologías</a> •
  <a href="#-diagramas-del-sistema">Diagramas</a>
</p>

</div>

---

# 🌌 Acerca del proyecto

**Event Scheduling System (ESS)** es una plataforma diseñada para empresas de entretenimiento y producción de eventos que necesitan administrar equipos, reservas y programación de activos de manera eficiente.

El sistema permite:

- 🎤 Gestionar equipos y activos
- 📅 Programar eventos
- 📦 Controlar inventario
- 💳 Administrar facturación
- 👥 Gestionar usuarios
- 📊 Generar reportes
- 🌐 Implementar arquitectura cliente/servidor
- ⚡ Optimizar la logística de eventos

---

# ✨ Características

## 🎤 Gestión de activos

- 📦 Registro de equipos
- 🎛️ Clasificación por categorías
- 📊 Control de inventario
- 🔄 Actualización de disponibilidad
- ⚡ Gestión de estado

---

## 📅 Programación de eventos

- 🗓️ Creación de eventos
- 📍 Gestión de ubicaciones
- ⏰ Programación de horarios
- 🚫 Prevención de conflictos
- 🎯 Asignación de recursos

---

## 💳 Sistema de facturación

- 🧾 Generación de facturas
- 💰 Control de pagos
- 📄 Emisión de recibos
- 📊 Gestión financiera
- 📈 Seguimiento de ingresos

---

## 🌐 Arquitectura cliente-servidor

- 🖥️ Aplicación cliente
- 🌍 Comunicación TCP/IP
- ⚡ Soporte multiusuario
- 🔐 Gestión de autenticación
- 📡 Conexión en red

---

# 👨‍💼 Módulos del sistema

## 📦 Asset Management Module

Este módulo permite administrar todos los activos y equipos almacenados en inventario.

### Funcionalidades:

- ➕ Registro de activos
- 📦 Gestión de inventario
- 🎛️ Clasificación de equipos
- 📊 Control de cantidades
- ⚡ Seguimiento de disponibilidad

---

## 📅 Scheduling Module

Módulo encargado de la programación de eventos y asignación de equipos.

### Funcionalidades:

- 🗓️ Agendar eventos
- ⏰ Configurar horarios
- 🚫 Evitar conflictos de reservas
- 🎯 Asignar equipos
- 📍 Gestionar ubicaciones

---

## 💳 Billing Module

Módulo financiero del sistema.

### Funcionalidades:

- 🧾 Crear facturas
- 💰 Gestionar pagos
- 📄 Emitir recibos
- 📊 Generar balances
- 📈 Monitorear ingresos

---

## 📊 Reporting Module

Módulo de generación de reportes y estadísticas.

### Funcionalidades:

- 📊 Reportes financieros
- 📅 Reportes por fecha
- 📈 Estadísticas de ingresos
- 📦 Reportes de activos
- 🧾 Exportación PDF

---

# 🛠️ Tecnologías utilizadas

## 🎨 Frontend / GUI

<p>
  <img src="https://skillicons.dev/icons?i=java" />
</p>

- Java Swing
- JavaFX
- Scene Builder
- GUI Components

---

## ⚙️ Backend

<p>
  <img src="https://skillicons.dev/icons?i=java" />
</p>

- Java 21
- Client/Server Architecture
- TCP/IP Sockets
- Multi-threading
- OOP Programming

---

## 🗄️ Base de datos

<p>
  <img src="https://skillicons.dev/icons?i=mysql" />
</p>

- MySQL
- SQL Queries
- ORM Integration
- Relational Database Design

---

## 🧰 Herramientas

<p>
  <img src="https://skillicons.dev/icons?i=git,github,idea,eclipse" />
</p>

- Git
- GitHub
- IntelliJ IDEA
- Eclipse
- Scene Builder

---

# 📂 Estructura del proyecto

```bash
EventSchedulingSystem/
│
├── client/                     # Aplicación cliente
├── server/                     # Servidor principal
├── database/                   # Scripts SQL y modelos
├── docs/                       # Documentación
├── assets/                     # Recursos multimedia
├── reports/                    # Reportes generados
├── diagrams/                   # Diagramas UML y ERD
├── src/                        # Código fuente Java
├── README.md
└── LICENSE
```

---

# ⚡ Instalación

## 📋 Requisitos

- Java 21
- MySQL
- IntelliJ IDEA / Eclipse
- Scene Builder
- Git

---

# 🚀 Configuración del proyecto

## 1️⃣ Clonar repositorio

```bash
git clone https://github.com/isairey/EventSchedulingSystem.git
```

---

## 2️⃣ Configurar base de datos

Crear base de datos:

```sql
CREATE DATABASE ess_system;
```

---

## 3️⃣ Configurar conexión

Editar archivo:

```bash
src/config/DatabaseConfig.java
```

Agregar credenciales:

```java
private static final String URL = "jdbc:mysql://localhost:3306/ess_system";
private static final String USER = "root";
private static final String PASSWORD = "";
```

---

## 4️⃣ Ejecutar servidor

```bash
java Server.java
```

---

## 5️⃣ Ejecutar cliente

```bash
java Client.java
```

---

# 📊 Funcionalidades principales

## 📦 Gestión de inventario

- Registro de activos
- Control de disponibilidad
- Gestión de estados
- Administración de categorías

---

## 📅 Gestión de eventos

- Programación de eventos
- Asignación de recursos
- Gestión de reservas
- Control de conflictos

---

## 💳 Gestión financiera

- Facturación
- Generación de recibos
- Reportes financieros
- Seguimiento de pagos

---

# 🧠 Diagramas del sistema

## 🗄️ Entity Relationship Diagram (ERD)

### Entidades principales:

- USER
- ASSET
- EVENT
- BOOKING
- PRICE
- INVOICE

---

## 🧩 Class Diagram

### Clases principales:

- User
- Asset
- Event
- Booking
- Invoice

---

# 📸 Vista previa

## 🖥️ Interfaces del sistema

<div align="center">

### 📦 Gestión de activos
<img src="https://cdn-icons-png.flaticon.com/512/679/679720.png" width="120">

### 📅 Programación de eventos
<img src="https://cdn-icons-png.flaticon.com/512/3652/3652191.png" width="120">

### 💳 Facturación
<img src="https://cdn-icons-png.flaticon.com/512/2489/2489756.png" width="120">

### 📊 Reportes
<img src="https://cdn-icons-png.flaticon.com/512/3135/3135715.png" width="120">

</div>

---

# 🧠 Objetivos del proyecto

## 🎯 Aprendizaje y desarrollo

- Programación orientada a objetos
- Arquitectura cliente-servidor
- Gestión de bases de datos
- Programación concurrente
- Diseño UML
- Desarrollo Java empresarial
- Sistemas de eventos

---

# 🚧 Roadmap

## 🔮 Próximas mejoras

- ☁️ Infraestructura cloud
- 📱 Aplicación móvil
- 🔔 Notificaciones en tiempo real
- 📊 Dashboard avanzado
- 🤖 Automatización inteligente
- 🌐 API REST moderna
- 📈 Analítica empresarial

---

# 🤝 Contribuciones

Las contribuciones son bienvenidas ❤️

## Cómo contribuir

1. Fork del proyecto

```bash
git checkout -b feature/nueva-funcionalidad
```

2. Commit

```bash
git commit -m "✨ Nueva funcionalidad"
```

3. Push

```bash
git push origin feature/nueva-funcionalidad
```

4. Pull Request 🚀

---

# 👨‍💻 Equipo de desarrollo

<div align="center">

## ESS Development Team

Proyecto académico enfocado en programación Java, arquitectura cliente-servidor y sistemas empresariales 🚀

</div>

---

# 🌟 Apoya el proyecto

⭐ Dale una estrella  
🍴 Haz fork  
📢 Comparte el proyecto

---

# 📜 Licencia

Proyecto académico y open source desarrollado con fines educativos y empresariales.

---

<div align="center">

### 🎭 Event Scheduling System — gestión inteligente de eventos y activos 🚀

</div>
