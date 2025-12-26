package com.marcos.foro.repositories;

import com.marcos.foro.models.curso.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CursoRepository extends JpaRepository<Curso, Long> {
}
