package com.marcos.foro.models.topicos;

import java.time.LocalDateTime;

public record DatosListaTopicos(
        Long id,
        String titulo,
        String mensaje,
        LocalDateTime fechaCreacion,
        Boolean estado,
        String autor,
        String curso
) {
    public DatosListaTopicos(Topico topico) {
        this(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getFecha(),
                topico.getEstado(),
                topico.getAutor().getNombre(),
                topico.getCurso().getNombre()
        );
    }
}
