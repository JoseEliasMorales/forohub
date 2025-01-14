# Forohub API


<div align="center">
   <img src="src/main/java/com/challengue/forohub/asset/logo-aluraespanhol.svg" alt="Logo Alura Español" width="100"/>
   <img src="src/main/java/com/challengue/forohub/asset/logoOne.webp" alt="Logo ONE" width="200"/>
</div>


Este proyecto es parte del challenge final del programa educativo **Alura Latam y Oracle**. Forohub es una API REST desarrollada en **Spring Boot** que proporciona una plataforma de foro donde los usuarios pueden crear, actualizar y eliminar tópicos, responder a ellos y gestionar usuarios. Además, incluye autenticación con JWT y documentación generada con Swagger.

---

## Tecnologías utilizadas
- **Java 17**
- **Spring Boot** (Data JPA, Security, Validation, Web)
- **JWT** para autenticación.
- **Swagger/OpenAPI** para documentación.
- **Flyway** para migraciones de base de datos.
- **MySQL** como base de datos principal.

---

## Dependencias principales
El proyecto utiliza las siguientes dependencias de Maven:
- **Spring Boot Starter Data JPA**
- **Spring Boot Starter Security**
- **Spring Boot Starter Validation**
- **Spring Boot Starter Web**
- **Flyway Core y Flyway MySQL**
- **Spring Boot DevTools**
- **MySQL Connector** y **MariaDB Java Client**
- **Lombok**
- **Java JWT**
- **Springdoc OpenAPI Starter WebMVC UI**

---

## Requisitos del sistema
- **Java 17**
- **Maven**
- **MySQL**

---

## Configuración del proyecto
1. Clona este repositorio:
   ```
   git clone https://github.com/JoseEliasMorales/forohub
   cd Forohub
   ```
2. Configura las variables de entorno para el archivo `application.properties`
   ```
   spring.datasource.url=jdbc:mysql://localhost:8080/forohub
   spring.datasource.username=tu_usuario
   spring.datasource.password=tu_contraseña
   spring.jpa.hibernate.ddl-auto=update
    ```
3. Ejecuta el proyecto:
    ```
   mvn spring-boot:run
    ```
4. Accede a la documentación de Swagger:
- **URL:** http://localhost:8080/swagger-ui.html

## Endpoints principales
### Usuarios
- `GET /usuarios` - Obtiene una lista de usuarios.
- `PUT /usuarios` - Actualiza un usuario.
- `DELETE /usuarios/{id}` - Elimina un usuario.
- `POST /register` - Registra un nuevo usuario.

### Tópicos
- `GET /topicos` - Obtiene una lista de tópicos.
- `POST /topicos` - Crea un nuevo tópico.
- `GET /topicos/{id}` - Obtiene detalles de un tópico específico.
- `PUT /topicos/{id}` - Actualiza un tópico.
- `DELETE /topicos/{id}` - Elimina un tópico.
- `POST /topicos/{id}` - Crea una respuesta para un tópico.

### Autenticación
- `POST /login` - Autentica a un usuario y devuelve un token JWT.

---

## Ejemplo de estructura del proyecto
```plaintext
src/
├── main/
│   ├── java/
│   │   └── com.challengue.forohub/
│   │       ├── controller/
│   │       ├── domain/
│   │       │   ├── curso/
│   │       │   ├── respuesta/
│   │       │   ├── topico/
│   │       │   └── usuario/
│   │       ├── infra/
│   │       │   ├── security/
│   │       │   └── springdoc/
│   │       └── ForohubApplication.java
│   └── resources/
│       ├── db.migration/ #Scripts de Flyway
│       └── application.properties
│       
└── test/
```

## Documentación de la API

La API está documentada utilizando Swagger. Puedes acceder a la documentación completa desde el siguiente enlace:  

[Documentación Swagger - Forohub](http://localhost:8080/swagger-ui/index.html)
