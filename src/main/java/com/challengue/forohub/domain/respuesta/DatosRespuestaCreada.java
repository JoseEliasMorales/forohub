package com.challengue.forohub.domain.respuesta;

import com.challengue.forohub.domain.topico.Topico;
import com.challengue.forohub.domain.usuario.DatosUsuario;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DatosRespuestaCreada(
        @NotBlank
        String mensaje,
        @NotNull
        Long topicoId,
        @NotNull
        LocalDateTime fechaCreacion,
        @NotNull
        Long usuarioId
) {
}
