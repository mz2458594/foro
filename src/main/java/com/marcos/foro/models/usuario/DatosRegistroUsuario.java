package com.marcos.foro.models.usuario;

import jakarta.validation.constraints.NotBlank;

public record DatosRegistroUsuario(
        @NotBlank String nombre,
        @NotBlank String correo,
        @NotBlank String contrasena
) {
}
