package com.marcos.foro.models.respuesta;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DatosRegistroRespuesta(
        @NotNull Long id_topico,
        @NotNull Long id_usuario,

        @NotBlank String mensaje,

        @NotNull @Future LocalDateTime fechaCreacion,

        @NotBlank String solucion
) {
}
