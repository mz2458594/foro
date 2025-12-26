package com.marcos.foro.repositories;

import com.marcos.foro.models.topicos.Topico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicoRepository extends JpaRepository<Topico, Long> {

}
