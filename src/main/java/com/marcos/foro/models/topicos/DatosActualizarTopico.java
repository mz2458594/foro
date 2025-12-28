package com.marcos.foro.models.topicos;

import jakarta.validation.constraints.NotNull;

public record DatosActualizarTopico(
        String titulo,
        String mensaje,
        Long id_autor,
        Long id_curso
) {
}
