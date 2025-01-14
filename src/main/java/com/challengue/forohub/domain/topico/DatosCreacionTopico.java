package com.challengue.forohub.domain.topico;

import com.challengue.forohub.domain.curso.Curso;
import com.challengue.forohub.domain.usuario.Usuario;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DatosCreacionTopico(
        @NotBlank
        String titulo,
        @NotNull
        Boolean status,
        @NotBlank
        String mensaje,
        @NotNull
        LocalDateTime fechaCreacion,
        @NotNull
        Long cursoId,
        @NotNull
        Long usuarioId
) {
}
