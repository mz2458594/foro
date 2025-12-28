package com.marcos.foro.repositories;

import com.marcos.foro.models.respuesta.Respuesta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RespuestaRepository extends JpaRepository<Respuesta, Long> {
}
