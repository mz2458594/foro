package com.marcos.foro.models.respuesta;

import com.marcos.foro.models.topicos.Topico;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DatosListaRespuesta(
        Long id_respuesta,
        Long id_topico,
        String nombre_Usuario,

        String mensaje,

        LocalDateTime fechaCreacion,

        String solucion
) {

    public DatosListaRespuesta(Respuesta respuesta) {
        this(
                respuesta.getId(),
                respuesta.getTopico().getId(),
                respuesta.getUsuario().getNombre(),
                respuesta.getMensaje(),
                respuesta.getFechaCreacion(),
                respuesta.getSolucion()
        );
    }
}

