# Tarea CRUD - Spring Boot 3 con Java 21

Aplicación REST para gestionar tareas con operaciones CRUD (Create, Read, Update, Delete).

## Características

- ✅ Java 21 con Spring Boot 3
- ✅ REST API completa
- ✅ Base de datos H2 en memoria (desarrollo)
- ✅ Soporte para MySQL (producción)
- ✅ JPA/Hibernate
- ✅ Validación de datos
- ✅ Pruebas unitarias
- ✅ Lombok para reducir boilerplate
- ✅ CORS habilitado

## Requisitos

- Java 21 o superior
- Maven 3.8+
- IDE (IntelliJ IDEA, VS Code o Eclipse)

## Instalación

### 1. Clonar o descargar el proyecto

```bash
cd tarea-crud
```

### 2. Compilar el proyecto

```bash
mvn clean install
```

### 3. Ejecutar la aplicación

```bash
mvn spring-boot:run
```

O mediante el JAR:

```bash
mvn clean package
java -jar target/tarea-crud-1.0.0.jar
```

La aplicación estará disponible en: `http://localhost:8080`

## Endpoints de la API

### 1. Obtener todas las tareas

```http
GET /api/tareas
```

**Respuesta exitosa (200):**
```json
[
  {
    "id": 1,
    "fechaCreacion": "2024-04-15 10:30:45",
    "descripcion": "Implementar API REST"
  },
  {
    "id": 2,
    "fechaCreacion": "2024-04-15 11:20:30",
    "descripcion": "Crear pruebas unitarias"
  }
]
```

### 2. Obtener una tarea por ID

```http
GET /api/tareas/{id}
```

**Ejemplo:**
```http
GET /api/tareas/1
```

**Respuesta exitosa (200):**
```json
{
  "id": 1,
  "fechaCreacion": "2024-04-15 10:30:45",
  "descripcion": "Implementar API REST"
}
```

**Respuesta no encontrada (404):**
```json
{
  "mensaje": "Tarea no encontrada con ID: 1"
}
```

### 3. Crear una nueva tarea

```http
POST /api/tareas
Content-Type: application/json

{
  "descripcion": "Nueva tarea"
}
```

**Respuesta exitosa (201):**
```json
{
  "id": 3,
  "fechaCreacion": "2024-04-15 12:00:00",
  "descripcion": "Nueva tarea"
}
```

**Respuesta error (400):**
```json
{
  "mensaje": "La descripción de la tarea no puede estar vacía"
}
```

### 4. Actualizar una tarea

```http
PUT /api/tareas/{id}
Content-Type: application/json

{
  "descripcion": "Descripción actualizada"
}
```

**Ejemplo:**
```http
PUT /api/tareas/1
```

**Respuesta exitosa (200):**
```json
{
  "id": 1,
  "fechaCreacion": "2024-04-15 10:30:45",
  "descripcion": "Descripción actualizada"
}
```

### 5. Eliminar una tarea

```http
DELETE /api/tareas/{id}
```

**Ejemplo:**
```http
DELETE /api/tareas/1
```

**Respuesta exitosa (204):** Sin contenido

**Respuesta no encontrada (404):** Tarea no existe

## Estructura del Proyecto

```
tarea-crud/
├── pom.xml
├── README.md
├── src/
│   ├── main/
│   │   ├── java/com/example/tarea/
│   │   │   ├── TareaCrudApplication.java (Clase principal)
│   │   │   ├── controller/
│   │   │   │   └── TareaController.java
│   │   │   ├── service/
│   │   │   │   └── TareaService.java
│   │   │   ├── repository/
│   │   │   │   └── TareaRepository.java
│   │   │   ├── entity/
│   │   │   │   └── Tarea.java
│   │   │   └── dto/
│   │   │       └── TareaDTO.java
│   │   └── resources/
│   │       └── application.yml
│   └── test/
│       └── java/com/example/tarea/
│           └── service/
│               └── TareaServiceTest.java
```

## Configuración de Base de Datos

### Desarrollo (H2)

La configuración por defecto usa H2 en memoria. Para acceder a la consola:

```
http://localhost:8080/h2-console

JDBC URL: jdbc:h2:mem:tareadb
Usuario: sa
Contraseña: (dejar vacía)
```

### Producción (MySQL)

1. Crear la base de datos:

```sql
CREATE DATABASE tareadb;
```

2. Actualizar `application.yml` con credenciales MySQL:

```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/tareadb?useSSL=false&serverTimezone=UTC
    username: root
    password: tu_contraseña
```

3. Ejecutar con el perfil MySQL:

```bash
mvn spring-boot:run -Dspring-boot.run.arguments="--spring.profiles.active=mysql"
```

## Pruebas

### Ejecutar todas las pruebas

```bash
mvn test
```

### Ejecutar una prueba específica

```bash
mvn test -Dtest=TareaServiceTest
```

## Ejemplo con cURL

```bash
# Crear tarea
curl -X POST http://localhost:8080/api/tareas \
  -H "Content-Type: application/json" \
  -d '{"descripcion": "Mi primera tarea"}'

# Obtener todas las tareas
curl http://localhost:8080/api/tareas

# Obtener tarea por ID
curl http://localhost:8080/api/tareas/1

# Actualizar tarea
curl -X PUT http://localhost:8080/api/tareas/1 \
  -H "Content-Type: application/json" \
  -d '{"descripcion": "Tarea actualizada"}'

# Eliminar tarea
curl -X DELETE http://localhost:8080/api/tareas/1
```

## Ejemplo con Postman

1. Importar la URL base: `http://localhost:8080/api/tareas`
2. Crear requests para cada endpoint
3. En la pestaña "Body" seleccionar "raw" y "JSON"
4. Utilizar los ejemplos anteriores

## Dependencias Principales

- **Spring Boot 3.2.0**: Framework principal
- **Spring Data JPA**: Acceso a datos
- **Lombok**: Reducción de boilerplate
- **H2 Database**: BD de desarrollo
- **MySQL Connector**: BD de producción
- **JUnit 5 y Mockito**: Testing

## Notas de Desarrollo

- La fecha de creación se asigna automáticamente al momento de crear la tarea
- Solo se requiere la descripción para crear una tarea
- Los campos se validan en el servicio
- Todos los endpoints tienen manejo de excepciones

## Licencia

Este proyecto es de código abierto y puede ser usado libremente.

## Autor

Desarrollado con Spring Boot 3 y Java 21
