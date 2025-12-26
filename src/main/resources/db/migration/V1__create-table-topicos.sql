CREATE TABLE usuarios(
  id bigint NOT NULL AUTO_INCREMENT,
  nombre varchar(255) NOT NULL,
  email varchar(255) NOT NULL UNIQUE,
  password varchar(255) NOT NULL,
  PRIMARY KEY (id)
);


CREATE TABLE cursos(
    id bigint NOT NULL AUTO_INCREMENT,
    nombre VARCHAR(255) NOT NULL,
    categoria VARCHAR(255) NOT NULL,
   PRIMARY KEY(id)
);

CREATE TABLE topicos(
  id bigint NOT NULL AUTO_INCREMENT,
  titulo varchar(255) NOT NULL UNIQUE,
  mensaje varchar(255) NOT NULL UNIQUE,
  usuario_id bigint NOT NULL,
  curso_id bigint NOT NULL,
  estado tinyint NOT NULL,
  fecha datetime NOT NULL,
  CONSTRAINT fk_topico_usuario_id FOREIGN KEY(usuario_id) REFERENCES usuarios(id),
  CONSTRAINT fk_topico_curso_id FOREIGN KEY(curso_id) REFERENCES cursos(id),
  PRIMARY KEY (id)
);

CREATE TABLE respuestas(
    id bigint NOT NULL AUTO_INCREMENT,
    mensaje VARCHAR(255) NOT NULL,
    fecha_creacion datetime NOT NULL,
    solucion VARCHAR(255) NOT NULL,
    topico_id bigint NOT NULL,
    usuario_id bigint NOT NULL,
    CONSTRAINT fk_respuesta_topico_id FOREIGN KEY(topico_id) REFERENCES topicos(id),
    CONSTRAINT fk_respuesta_usuario_id FOREIGN KEY(usuario_id) REFERENCES usuarios(id),
    PRIMARY KEY(id)
);