# BikTech Banking Boilerplate

This project demonstrates a Spring Boot application with a modular architecture, JWT security (RBAC), and OpenAPI documentation.

## Features
- **Modular Design**: Core, Infrastructure, Module.
- **Security**: JWT Authentication with Role-Based Access Control (RBAC).
- **Documentation**: OpenAPI (Swagger UI) integrated.
- **Validation**: JSR 380 Bean Validation.
- **Logging**: SLF4J and Logback.
- **Database**: PostgreSQL integration.

## API Documentation
Swagger UI is available at:
`http://localhost:8080/swagger-ui.html`

## Application Request Flow

```text
User Request (Client)
     |
     v
+-----------------------------+
|   JwtAuthenticationFilter   |  <-- Intercepts request, validates JWT token
+-----------------------------+
     |
     v
+-----------------------------+
|      DispatcherServlet      |  <-- Routes request to appropriate Controller
+-----------------------------+
     |
     v
+-----------------------------+
|      Controller Layer       |  <-- Handles HTTP request, validates DTOs
+-----------------------------+
     |
     v
+-----------------------------+
|       Service Layer         |  <-- Business logic
+-----------------------------+
     |
     v
+-----------------------------+
|      Repository Layer       |  <-- Data access
+-----------------------------+
     |
     v
+-----------------------------+
|         Database            |  <-- PostgreSQL
+-----------------------------+
```