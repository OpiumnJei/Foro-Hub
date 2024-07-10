
CREATE TABLE topicos (
    id BIGINT NOT NULL AUTO_INCREMENT,
    titulo_topico VARCHAR(200) NOT NULL UNIQUE,
    mensaje_topico VARCHAR(500) NOT NULL UNIQUE,
    curso_nombre VARCHAR(200) NOT NULL,
    fecha_creacion DATETIME NOT NULL,
    id_usuario BIGINT NOT NULL, -- Aquí se define la columna para el ID del autor
    PRIMARY KEY(id)
);