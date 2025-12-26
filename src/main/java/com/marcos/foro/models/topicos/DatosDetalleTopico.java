package com.marcos.foro.models.topicos;

import java.time.LocalDateTime;

public record DatosDetalleTopico(
        String titulo,
        String mensaje,
        LocalDateTime fechaCreacion,
        Boolean estado,
        String autor,
        String curso
) {
    public DatosDetalleTopico(Topico topico) {
        this(
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getFecha(),
                topico.getEstado(),
                topico.getAutor().getNombre(),
                topico.getCurso().getNombre()
        );
    }
}
