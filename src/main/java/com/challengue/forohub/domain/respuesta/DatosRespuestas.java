package com.challengue.forohub.domain.respuesta;

import com.challengue.forohub.domain.topico.Topico;
import com.challengue.forohub.domain.usuario.DatosUsuario;

import java.time.LocalDateTime;

public record DatosRespuestas(
        String mensaje,
        Long topicoId,
        LocalDateTime fechaCreacion,
        DatosUsuario usuario,
        Boolean solucion
) {
    public DatosRespuestas(Respuesta respuesta){
        this(
                respuesta.getMensaje(),
                respuesta.getTopico().getId(),
                respuesta.getFechaCreacion(),
                new DatosUsuario(
                        respuesta.getUsuario().getNombre(),
                        respuesta.getUsuario().getCorreoElectronico()
                ),
                respuesta.getSolucion()
        );
    }
}
