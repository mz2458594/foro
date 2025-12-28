package com.marcos.foro.models.respuesta;

import java.time.LocalDateTime;

public record DatosDetalleRespuesta(
        Long id_topico,
        String nombre_Usuario,

        String mensaje,

        LocalDateTime fechaCreacion,

        String solucion
) {

    public DatosDetalleRespuesta(Respuesta respuesta) {
        this(
                respuesta.getTopico().getId(),
                respuesta.getUsuario().getNombre(),
                respuesta.getMensaje(),
                respuesta.getFechaCreacion(),
                respuesta.getSolucion()
        );
    }
}
