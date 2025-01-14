package com.challengue.forohub.domain.respuesta;

import com.challengue.forohub.domain.topico.TopicoRepository;
import com.challengue.forohub.domain.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ResponderTopico {

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private RespuestaRepository respuestaRepository;

    public void responder(DatosRespuestaCreada datos){
        var topicoAResponder = topicoRepository.findById(datos.topicoId());
        var datosUsuario = usuarioRepository.findById(datos.usuarioId());
        System.out.println(datosUsuario);
        if (topicoAResponder.isPresent() && datosUsuario != null){
            Respuesta respuesta = new Respuesta(null, datos.mensaje(), topicoAResponder.get(), LocalDateTime.now(),  datosUsuario.get(), false);
            respuestaRepository.save(respuesta);
        }
    }
}
