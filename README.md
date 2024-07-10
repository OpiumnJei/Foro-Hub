## Challenge alura - Foro Hub

Esta es una API REST que emula un foro, en donde todos los participantes de una plataforma pueden plantear sus preguntas sobre determinados temas.
La API permite a los usuarios crear, leer, actualizar y eliminar tópicos.

## Características

Busqueda, registro, lectura y eliminacion de topicos.

Estructura simplificada de entidades.

## Tecnologías Utilizadas

- Java 17
- Spring Boot 3.2
- JPA/Hibernate
- MySQL
- Maven
- Flyway
- Lombok
- Java JWT

## Endpoints
### Tópicos
- GET /topicos: Obtiene todos los tópicos.
- GET /topicos/{id}: Obtiene un tópico por ID.
- POST /topicos: Crea un nuevo tópico.
- PUT /topicos/{id}: Actualiza un tópico existente.
- DELETE /topicos/{id}: Elimina un tópico.