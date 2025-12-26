package com.marcos.foro.models.topicos;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.UniqueElements;

import java.time.LocalDateTime;

public record DatosRegistroTopico(
        @NotBlank String titulo,
        @NotBlank String mensaje,
        @NotNull @Future LocalDateTime fecha,
        @NotNull Long idUsuario,
        @NotNull Long idCurso
) {
}
