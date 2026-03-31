# 🛒 Capacitación IA — CRUD de Productos con Spring Boot 3

A service-oriented REST API built with **Java 17** and **Spring Boot 3.2.4** that exposes full CRUD operations for a `Producto` (Product) entity. The data layer uses an in-memory mock repository backed by a `HashMap` — no database required.

---

## 📋 Table of Contents

- [Tech Stack](#-tech-stack)
- [Project Structure](#-project-structure)
- [Architecture Overview](#-architecture-overview)
- [Entity & DTOs](#-entity--dtos)
- [Mock Repository (Data Layer)](#-mock-repository-data-layer)
- [REST API Endpoints](#-rest-api-endpoints)
- [Error Handling](#-error-handling)
- [Getting Started](#-getting-started)
- [Example Requests (cURL)](#-example-requests-curl)

---

## 🧰 Tech Stack

| Technology            | Version  |
|-----------------------|----------|
| Java                  | 17       |
| Spring Boot           | 3.2.4    |
| Spring Web (REST)     | 3.2.4    |
| Spring Validation     | 3.2.4    |
| Maven                 | 3.x      |

---

## 📁 Project Structure

```
src/main/java/com/capacitacion/ia/
│
├── CapacitacionIaApplication.java       # Spring Boot entry point
│
├── controller/
│   └── ProductoController.java          # REST endpoints (Layer: Controller)
│
├── dto/
│   ├── ProductoRequestDTO.java          # Input DTO (create / update)
│   └── ProductoResponseDTO.java         # Output DTO
│
├── exception/
│   ├── ProductoNotFoundException.java   # Custom runtime exception
│   └── GlobalExceptionHandler.java      # Centralized error handler (@RestControllerAdvice)
│
├── model/
│   └── Producto.java                    # Domain entity
│
├── repository/
│   ├── ProductoRepository.java          # Repository interface
│   └── impl/
│       └── ProductoRepositoryImpl.java  # Mock in-memory implementation
│
└── service/
    ├── ProductoService.java             # Service interface
    └── impl/
        └── ProductoServiceImpl.java     # Business logic implementation
```

---

## 🏛️ Architecture Overview

The application follows a **layered service-oriented architecture**:

```
HTTP Request
     │
     ▼
┌──────────────────┐
│   Controller     │  ← Receives HTTP requests, delegates to Service
└────────┬─────────┘
         │  DTOs
         ▼
┌──────────────────┐
│    Service       │  ← Business logic, DTO ↔ Model mapping
└────────┬─────────┘
         │  Domain Model
         ▼
┌──────────────────┐
│   Repository     │  ← Data access abstraction (interface)
└────────┬─────────┘
         │
         ▼
┌──────────────────────────────┐
│  ProductoRepositoryImpl      │  ← Mock: HashMap as in-memory "database"
│  (5 pre-loaded products)     │
└──────────────────────────────┘
```

Each layer only communicates with its adjacent layer, keeping concerns cleanly separated.

---

## 📦 Entity & DTOs

### `Producto` (Domain Model)

| Field        | Type     | Description          |
|--------------|----------|----------------------|
| `id`         | `String` | Unique identifier    |
| `descripcion`| `String` | Product description  |

### `ProductoRequestDTO` (Input)

| Field        | Type     | Validation              |
|--------------|----------|-------------------------|
| `descripcion`| `String` | `@NotBlank` — required  |

### `ProductoResponseDTO` (Output)

| Field        | Type     |
|--------------|----------|
| `id`         | `String` |
| `descripcion`| `String` |

---

## 🗄️ Mock Repository (Data Layer)

`ProductoRepositoryImpl` simulates a database using a `HashMap<String, Producto>`. It is pre-loaded with **5 mock products** at startup:

| ID        | Description                              |
|-----------|------------------------------------------|
| PROD-001  | Laptop Dell XPS 15                       |
| PROD-002  | Monitor Samsung 27 pulgadas              |
| PROD-003  | Teclado mecánico Logitech MX Keys        |
| PROD-004  | Mouse inalámbrico Microsoft Arc          |
| PROD-005  | Auriculares Sony WH-1000XM5              |

New products created via `POST` receive an auto-generated ID in the format `PROD-NNN` (or a UUID-based fallback if a collision occurs).

> ⚠️ Data is **volatile** — it resets every time the application restarts.

---

## 🌐 REST API Endpoints

Base URL: `http://localhost:8080/api/productos`

| Method   | Endpoint              | Description                   | Request Body             | Success Response        |
|----------|-----------------------|-------------------------------|--------------------------|-------------------------|
| `GET`    | `/api/productos`      | Retrieve all products         | —                        | `200 OK` + JSON array   |
| `GET`    | `/api/productos/{id}` | Retrieve a product by ID      | —                        | `200 OK` + JSON object  |
| `POST`   | `/api/productos`      | Create a new product          | `{ "descripcion": "…" }` | `201 Created` + JSON    |
| `PUT`    | `/api/productos/{id}` | Update an existing product    | `{ "descripcion": "…" }` | `200 OK` + JSON object  |
| `DELETE` | `/api/productos/{id}` | Delete a product by ID        | —                        | `204 No Content`        |

---

## ⚠️ Error Handling

All errors are handled globally by `GlobalExceptionHandler` and return a consistent JSON structure:

```json
{
  "timestamp": "2026-03-31T12:00:00.000",
  "status": 404,
  "error": "Not Found",
  "mensaje": "Producto no encontrado con id: PROD-999"
}
```

| Scenario                          | HTTP Status                  |
|-----------------------------------|------------------------------|
| Product ID not found              | `404 Not Found`              |
| Invalid request body (validation) | `400 Bad Request`            |
| Unexpected server error           | `500 Internal Server Error`  |

---

## 🚀 Getting Started

### Prerequisites

- Java 17+
- Maven 3.x

### Run the application

```bash
# Clone / navigate to project root
cd CapacitacionIA

# Build and run with Maven
mvn spring-boot:run
```

Or build the JAR and run it directly:

```bash
mvn clean package
java -jar target/capacitacion-ia-1.0.0.jar
```

The server starts on **port 8080** by default (`server.port=8080` in `application.properties`).

---

## 🧪 Example Requests (cURL)

### Get all products
```bash
curl -X GET http://localhost:8080/api/productos
```

### Get product by ID
```bash
curl -X GET http://localhost:8080/api/productos/PROD-001
```

### Create a new product
```bash
curl -X POST http://localhost:8080/api/productos \
  -H "Content-Type: application/json" \
  -d '{"descripcion": "Webcam Logitech C920"}'
```

### Update a product
```bash
curl -X PUT http://localhost:8080/api/productos/PROD-001 \
  -H "Content-Type: application/json" \
  -d '{"descripcion": "Laptop Dell XPS 15 (actualizada)"}'
```

### Delete a product
```bash
curl -X DELETE http://localhost:8080/api/productos/PROD-001
```

---

## 📄 License

This project is intended for educational and training purposes.

