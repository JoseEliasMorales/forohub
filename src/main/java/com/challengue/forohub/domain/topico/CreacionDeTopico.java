package com.challengue.forohub.domain.topico;

import com.challengue.forohub.domain.curso.CursoRepository;
import com.challengue.forohub.domain.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CreacionDeTopico {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private TopicoRepository topicoRepository;

    public Topico crearTopico(DatosCreacionTopico datos){
        var usuarioEncontrado = usuarioRepository.findById(datos.usuarioId()).get();
        var cursoEncontrado = cursoRepository.findById(datos.cursoId()).get();

        if(usuarioEncontrado != null && cursoEncontrado != null){
            Topico topico = topicoRepository.save(new Topico(null, datos.titulo(), datos.mensaje(), LocalDateTime.now(), true, usuarioEncontrado, cursoEncontrado, null));
            return topico;
        }

        return null;
    }
}
