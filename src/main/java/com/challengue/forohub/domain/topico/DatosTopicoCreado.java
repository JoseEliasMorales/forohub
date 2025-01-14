package com.challengue.forohub.domain.topico;

import com.challengue.forohub.domain.curso.DatosCurso;
import com.challengue.forohub.domain.usuario.DatosUsuario;

import java.time.LocalDateTime;

public record DatosTopicoCreado(
        Long id,
        String titulo,
        String mensaje,
        LocalDateTime fechaCreacion,
        DatosUsuario autor,
        DatosCurso curso
) {
}
