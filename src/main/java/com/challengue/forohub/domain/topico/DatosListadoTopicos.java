package com.challengue.forohub.domain.topico;

import com.challengue.forohub.domain.curso.DatosCurso;
import com.challengue.forohub.domain.respuesta.DatosRespuestas;
import com.challengue.forohub.domain.usuario.DatosUsuario;

import java.time.LocalDateTime;
import java.util.List;

public record DatosListadoTopicos(
        Long id,
        String titulo,
        String mensaje,
        LocalDateTime fechaCreacion,
        DatosUsuario autor,
        DatosCurso curso,
        List<DatosRespuestas> respuestas
) {
    public DatosListadoTopicos(Topico topico){
        this(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getFechaCreacion(),
                new DatosUsuario(
                        topico.getAutor().getNombre(),
                        topico.getAutor().getCorreoElectronico()
                ),
                new DatosCurso(
                        topico.getCurso().getNombre(),
                        topico.getCurso().getCategoria()
                ),
                topico.getRespuestas().stream().map(DatosRespuestas::new).toList()
        );
    }
}
