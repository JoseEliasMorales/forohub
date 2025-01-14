package com.challengue.forohub.domain.topico;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DatosActualizarTopico(
        @NotNull
        Long id,
        String titulo,
        String mensaje
) {
}
